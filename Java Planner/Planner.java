/**
 * @author Khiem Phi 
 * e-mail: khiem.phi@stonybrook.edu
 * Stony Brook University
 *
 *         The Planner class implements an abstract data type for a list of
 *         courses supporting some common operations on such lists.
 *
 */
public class Planner implements Cloneable {

	protected final int MAX_COURSES = 50;
	protected Course[] courses;
	private int size = 0;

	/**
	 * Constructs an instance of the Planner class with no Course objects in it.
	 * 
	 * 
	 * 
	 * The blank Constructor for planner creates an array of Course object, named
	 * courses and set the size of the array to be equal to the final value
	 * MAX_COURSES.
	 * 
	 * 
	 * Postcondition: This planner has been initialized to an empty list of Courses.
	 * 
	 */
	public Planner() {

		courses = new Course[MAX_COURSES];
	}

	/**
	 * Determines the numbers of Course objects currently in the planner
	 * 
	 * Preconditions: A Planner object has been instantiated.
	 * 
	 * 
	 * @return The number of Courses in this Planner
	 */
	public int size() {

		return size;
	}

	/**
	 * Add a Course object to an instance of the Planner class at a specific
	 * position in the Planner instance.
	 * 
	 * To add a Course object to the Planner class, every element at a position
	 * equal or greater than the specified position in the courses array of the
	 * Planner class is shifted backwards by 1 and the empty position space is then
	 * filled with the designated Course object. The method displays a message at
	 * the end to show successful implementation.
	 * 
	 * 
	 * Preconditions: This Course object has been instantiated and between 1 and
	 * size + 1. The number of Course objects in this Planner is less than
	 * MAX_COURSES.
	 * 
	 * Postconditions: The new Course is now listed in the correct preference on the
	 * list. All Courses that were originally greater than or equal to position are
	 * moved back one position. (e.g. If there are 5 Courses in a Planner,
	 * positioned 1-5, and you insert a Course in position 4, the new Course would
	 * be placed in position 4, the Course that was in position 4 will be moved to
	 * position 5, and the Course that was in position 5 will be moved to position
	 * 6.)
	 * 
	 * @param newCourse: the new course to add to the list
	 * @param position: the position (preference) of this course on the list
	 * @throws IllegalArgumentException: This exception is thrown when @param
	 *                                   position is not within the range of 0 to
	 *                                   MAX_Values
	 * @throws FullPlannerException:     This exception is thrown when there is no
	 *                                   more space in the Planner to hold Course
	 *                                   objects.
	 */
	public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException {

		try {
			if (position < 0 || position > this.size() + 1) // Checks if the
				// entry position is valid.
				throw new IllegalArgumentException("Error: This position doesn't exist ");
		} catch (IllegalArgumentException e) {
			return;
		}

		try {
			if (this.size() + 1 > this.MAX_COURSES - 1) // Checks if the Menu is able
				// to hold more items or not.
				throw new FullPlannerException("Error: Planner is currently full. One item must be removed");
		} catch (FullPlannerException e) {
			return;
		}

		for (int i = MAX_COURSES - 1; i != 0; i--) {

			if (i >= position)
				courses[i] = courses[i - 1];

		}

		courses[position] = newCourse;
		size++; // increment size as new course is added.
		/*
		 * This if - else statement is used to add the number 0 before the @param
		 * section of the specific Course to form a string that is "0" + @param section
		 * If the @param section is less than 10, then the number 0 is added.
		 * Otherwise @param section remains the same.
		 */
		if (newCourse.getSection() < 10) {
			System.out.println(newCourse.getDeparment() + newCourse.getCode() + "." + "0" + newCourse.getSection()
					+ " successfully added to planner");
		} else {
			System.out.println(newCourse.getDeparment() + +newCourse.getCode() + "." + newCourse.getSection()
					+ " sucessfully added to planner");

		}
	}

	/**
	 * Add a course to the end of the list of a Planner object.
	 * 
	 * Re-uses the addCourse(Course newCourse, int position) described above, except
	 * the position is defaulted to the end of the list.
	 * 
	 * @param newCourse: the new Course object to add to the list
	 */
	public void addCourse(Course newCourse) {

		addCourse(newCourse, size);

	}

	/**
	 * Remove a Course object in the Planner.
	 * 
	 * To remove a Course object to the Planner class, every element at a position
	 * equal or greater than the specified position in the courses array of the
	 * Planner class is shifted backwards by 1 and the empty position space is then
	 * filled with the a null Course object. The method displays a message at the
	 * end to show successful implementation.
	 * 
	 * Preconditions: This Planner has been instantiated and position is between 1
	 * and size
	 * 
	 * 
	 * Postconditions: The Course at the desired position has been removed. All
	 * Courses that were originally greater than or equal to position are moved
	 * backward one position. (e.g. If there are 5 Courses in a Planner, positioned
	 * 1-5, and you remove the Course in position 4, the item that was in position 5
	 * will be moved to position 4.)
	 * 
	 * @param position: the position in the Planner where the Course will be removed
	 *        from.
	 * @throws IllegalArgumentException: This exception is thrown when @param
	 *                                   position is not within the range of 0 to
	 *                                   MAX_Values
	 */
	public void removeCourse(int position) throws IllegalArgumentException {

		String display = "";
		/*
		 * This if - else statement is used to add the number 0 before the @param
		 * section of the specific Course to form a string that is "0" + @param section
		 * If the @param section is less than 10, then the number 0 is added.
		 * Otherwise @param section remains the same.
		 */
		if (this.courses[position].getSection() < 10) {
			display = this.courses[position].getDeparment() + this.courses[position].getCode() + "." + "0"
					+ this.courses[position].getSection() + " successfully removed from planner";
		} else {
			display = this.courses[position].getDeparment() + this.courses[position].getCode() + "."
					+ this.courses[position].getSection() + " successfully removed from planner";
		}

		try {
			if (position < 0 || position > this.size() + 1) // Checks if the
				// entry position is valid.
				throw new IllegalArgumentException("Error: This position doesn't exist ");
		} catch (IllegalArgumentException e) {
			return;
		}

		courses[position] = null;
		size--;
		for (int i = MAX_COURSES - 1; i != 0; i--) {

			if (i >= position)
				courses[i] = courses[i - 1];

		}

		System.out.println(display);

	}

	/**
	 * Retrieve a Course at a position.
	 * 
	 * 
	 * Preconditions: The Planner object has been instantiated and position is
	 * between 1 and size
	 * 
	 * @param position: position of the Course to retrieve.
	 * @return: The Course at the specified position in this Planner
	 * @throws IllegalArgumentException: This exception is thrown when @param
	 *                                   position is not within the range of 0 to
	 *                                   MAX_Values
	 */
	public Course getCourse(int position) throws IllegalArgumentException {
		try {
			if (position < 0 || position > this.size() + 1) // Checks if the
				// entry position is valid.
				throw new IllegalArgumentException("Error: This position doesn't exist ");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: Unable to find the " + "menu item since the given position does not fall "
					+ "in the valid range.");
			return null;
		}
		return courses[position];
	}

	/**
	 * Prints all Courses that are within the specified department
	 * 
	 * 
	 * To print all the Courses that are within the specified department, an
	 * algorithm similar to the toString() method is employed but only the courses
	 * of the specified department is printed.
	 * 
	 * Preconditions: This Planner object has been instantiated.
	 * 
	 * Postconditions: Displays a neatly formatted table of each course filtered
	 * from the Planner. Keep the preference numbers the same.
	 * 
	 * @param planner: the list of courses to search in
	 * @param department: the 3 letter department code for a Course
	 */
	public static void filter(Planner planner, String department) {

		String courseName = "No. Course Name";
		String departmentFormat = "Department";
		String firstPart = String.format("%-29s%-8s", courseName, departmentFormat);
		String secondPart = " Code Section Instructor";
		String dashedLine = ("-------------------------------------------------------------------------------");
		String allCourses = "\n";
		String sectionModify = "";
		int counter = 1; // the value of the Planner position isn't equal to i, which is the index of the
							// array courses. Therefore, counter is instantiated
		String fullFormat = firstPart + secondPart + "\n" + dashedLine;
		System.out.print(fullFormat);
		for (int i = 0; i < planner.MAX_COURSES - 1; i++) {

			if (!(planner.courses[i] == null) && planner.courses[i].getDeparment().equals(department)) {

				if (planner.courses[i].getSection() < 10) {

					sectionModify = "0" + planner.courses[i].getSection();

				} else {

					sectionModify = "" + planner.courses[i].getSection();

				}

				allCourses = allCourses + "  " + (counter) + " " + planner.courses[i].getCourseName() + "\t" + "     "
						+ planner.courses[i].getDeparment() + "\t" + planner.courses[i].getCode() + "\t "
						+ sectionModify + "  " + planner.courses[i].getInstructor() + "\n";

				counter++;

			} else if (!(planner.courses[i] == null)) {
				counter++;
			}

		}
		System.out.println(allCourses);

	}

	/**
	 * Checks whether a certain Course is already in the list.
	 * 
	 * To check whether a certain Course is already in the list, the entire array is
	 * looped through and the certain Course is compared with every other course in
	 * the array to see if there exists the same exact copy there. If yes, true is
	 * returned. Otherwise false is returned.
	 * 
	 * Preconditions: This Planner and Course has both been instantiated.
	 * 
	 * @param course: the Course we are looking for
	 * @return: True if the Planner contains this Course, false otherwise.
	 */
	public boolean exists(Course course) {

		for (int i = 0; i < size - 1; i++) {

			if (!(courses[i].equals(course))) {

				return false;
			}

		}
		return true;

	}

	/**
	 * Creates a copy of this Planner. Subsequent changes to the copy will not
	 * affect the original and vice versa.
	 * 
	 * Preconditions: This Planner object has been instantiated.
	 * 
	 * @return: Returns a deep copy of a specific Course object. Subsequent changes
	 *          to the copy will not affect the original, nor vice versa. The return
	 *          value must be type-cast to a Course object before it can be used as
	 *          the method only returns an Object object.
	 * 
	 */
	public Object clone() {

		try {
			return (Planner) super.clone();
		} catch (CloneNotSupportedException e) {
			return new Planner();
		}
	}

	/**
	 * Prints a neatly formatted table of each item in the list with its position
	 * number as shown in the sample output.
	 * 
	 * To print all Courses, the String generated by the toString() method is
	 * printed out.
	 * 
	 * Preconditions: This Planner has been instantiated.
	 * 
	 * Postconditions: Displays a neatly formatted table of each course from the
	 * Planner.
	 * 
	 * 
	 */
	public void printAllCourses() {

		System.out.println(this.toString());

	}

	/**
	 * Gets the String representation of this Planner object, which is a neatly
	 * formatted table of each Course in the Planner on its own line with its
	 * position number as shown in the sample output.
	 * 
	 * @return: The String representation of this Planner object.
	 * 
	 */
	public String toString() {

		String courseName = "No. Course Name";
		String department = "Department";
		String firstPart = String.format("%-29s%-8s", courseName, department);
		String secondPart = " Code Section Instructor";
		String dashedLine = ("-------------------------------------------------------------------------------");
		String allCourses = "\n";
		String sectionModify = "";
		int counter = 1; // the value of the Planner position isn't equal to i, which is the index of the
		// array courses. Therefore, counter is instantiated

		for (int i = 0; i < MAX_COURSES - 1; i++) {

			if (!(courses[i] == null)) {

				if (courses[i].getSection() < 10) {

					sectionModify = "0" + courses[i].getSection();

				} else {

					sectionModify = "" + courses[i].getSection();

				}

				allCourses = allCourses + "  " + (counter) + " " + courses[i].getCourseName() + "\t" + "     "
						+ courses[i].getDeparment() + "\t" + courses[i].getCode() + "\t " + sectionModify + "  "
						+ courses[i].getInstructor() + "\n";

				counter++;

			}

		}
		return firstPart + secondPart + "\n" + dashedLine + allCourses;

	}
}
