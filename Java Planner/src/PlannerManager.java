import java.util.Scanner;
import java.util.*;

/**
 * @author Khiem Phi 
 * e-mail: khiem.phi@stonybrook.edu 
 * Stony Brook University
 * 
 *         The PlannerManager class serves as the user-interface for the methods
 *         in the Planner class that is used to manipulate Course objects within
 *         an array.
 *
 *         The PlannerManager gives the user the options to enter different keys
 *         in order to create their own Planner class with Course objects to
 *         invoke the different methods that helps manages Course objects in the
 *         Planner class. This is done through a while loop in the main method
 *         of the PlannerManager class.
 */
public class PlannerManager {

	/**
	 * The main method of PlannerManager is used to create and manipulate a user
	 * created Planner object.
	 * 
	 * The method allows the user to enter different keys in order to create a
	 * manipulate a custom Planner object. The capitalization of the different keys
	 * is handled through if-statements. The user's input is processed within a
	 * while loop, where entering the key "Q" or "q" ends the while loop and
	 * terminates the program.
	 */
	public static void main(String[] args) {

		Planner userPlanner = new Planner();
		Planner userPlannerBackUp = new Planner();
		Scanner input = new Scanner(System.in);
		String userInput = "";

		while (true) {

			System.out.println("(A) Add Course");
			System.out.println("(G) Get Course");
			System.out.println("(R) Remove Course");
			System.out.println("(P) Print Courses in Planner");
			System.out.println("(F) Filter by Department Code");
			System.out.println("(L) Look for Course");
			System.out.println("(S) Size");
			System.out.println("(B) Backup");
			System.out.println("(PB) Print Courses in Backup");
			System.out.println("(RB) Revert to Backup");
			System.out.println("(Q) Quit");
			System.out.println("Enter a selection:");
			userInput = input.nextLine();

			/*
			 * A - Add Course <courseName> <department> <code> <section> <instructor> to a
			 * certain <position>. Add a new course to the list
			 */
			if (userInput.equals("a") || userInput.equals("A")) {
				System.out.print("Enter course name:" + "\n");
				String courseName = input.nextLine();
				System.out.print("Enter department:" + "\n");
				String department = input.nextLine();
				System.out.print("Enter course code:" + "\n");
				int courseCode = 0;
				// check if courseCode is obtaining the correct type of value from Scanner
				try {
					courseCode = input.nextInt();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				System.out.print("Enter course section:" + "\n");
				// check if courseSection is obtaining the correct type of value from Scanner
				byte courseSection = 0;
				try {
					courseSection = (byte) input.nextInt();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				input.nextLine(); // clearing the input stream
				System.out.print("Enter instructor:" + "\n");
				String courseInstructor = input.nextLine();
				System.out.print("Enter position:" + "\n");
				int position = input.nextInt();
				input.nextLine(); // clearing the input stream
				System.out.print(""); // clearing the input stream
				Course newCourse = new Course(courseName, department, courseCode, courseSection, courseInstructor);

				/*
				 * position has to be decremented to accurately reflect the position within the
				 * array courses in userPlanner
				 */

				userPlanner.addCourse(newCourse, position - 1);

				/*
				 * G- GetCourse at <position>. Display information of a Course object at given
				 * point.
				 */
			} else if (userInput.equals("G") || userInput.equals("g")) {
				Planner oneOnlyCourse = new Planner(); //create a Planner with only one course for display purposes.
				System.out.println("Enter position:");
				int position = 0;
				// check if position is obtaining the correct type of value from Scanner
				try {
					position = input.nextInt();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				input.nextLine(); // clearing the input stream
				System.out.print(""); // clearing the input stream

				/*
				 * position has to be decremented to accurately reflect the position within the
				 * array courses in userPlanner
				 */
				

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
				for (int i = 0; i < userPlanner.MAX_COURSES - 1; i++) {

					if (!(userPlanner.courses[i] == null) && i == position -1) {

						if (userPlanner.courses[i].getSection() < 10) {

							sectionModify = "0" + userPlanner.courses[i].getSection();

						} else {

							sectionModify = "" + userPlanner.courses[i].getSection();

						}

						allCourses = allCourses + "  " + (counter) + " " + userPlanner.courses[i].getCourseName() + "\t" + "     "
								+  userPlanner.courses[i].getDeparment() + "\t" +  userPlanner.courses[i].getCode() + "\t "
								+ sectionModify + "  " + userPlanner.courses[i].getInstructor() + "\n";

						counter++;

					} else if (!( userPlanner.courses[i] == null)) {
						counter++;
					}

				}
				System.out.println(allCourses);

				/*
				 * R- RemoveCourse at <position>. Remove a Course object at given point.
				 */

			} else if (userInput.equals("R") || userInput.equals("r")) {

				System.out.println("Enter position");
				int position = 0;
				// check if position is obtaining the correct type of value from Scanner
				try {
					position = input.nextInt();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print(""); // clear input stream
					continue; // skip iteration of while loop with exception
				}
				input.nextLine();
				System.out.print("");

				/*
				 * position has to be decremented to accurately reflect the position within the
				 * array courses in userPlanner
				 */
				userPlanner.removeCourse(position - 1);

				/*
				 * P - Print Courses in Planner. Display all Courses in the list.
				 */

			} else if (userInput.equals("P") || userInput.equals("p")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				userPlanner.printAllCourses();

				/*
				 * F - Filter By Department Code <code>. Display Courses in the list with the
				 * specified department code.
				 */

			} else if (userInput.equals("F") || userInput.equals("f")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				System.out.println("Enter department code:");
				String code = input.nextLine();
				Planner.filter(userPlanner, code);

				/*
				 * L - Look for Course <courseName> <department> <code> <section> <instructor>.
				 * Determines whether the course with given attributes is in the list.
				 */

			} else if (userInput.equals("L") || userInput.equals("l")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				System.out.print("Enter course name:" + "\n");
				String courseName = input.nextLine();
				System.out.print("Enter department:" + "\n");
				String department = input.nextLine();
				System.out.print("Enter course code:" + "\n");
				int courseCode = 0;
				// check if courseCode is obtaining the correct type of value from Scanner
				try {
					courseCode = input.nextInt();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				System.out.print("Enter course section:" + "\n");
				byte courseSection = 0;
				try {
					courseSection = (byte) input.nextInt();
				} catch (InputMismatchException e) {
					input.nextLine(); // clear input stream
					System.out.print(""); // clear input stream
					continue; // skip iteration with error
				}
				input.nextLine(); // clear input stream
				System.out.print("Enter instructor:" + "\n");
				String courseInstructor = input.nextLine();
				int position;
				Course lookUpCourse = new Course(courseName, department, courseCode, courseSection, courseInstructor);
				if (userPlanner.exists(lookUpCourse)) {
					for (int i = 0; i < userPlanner.size(); i++) {

						if (userPlanner.courses[i].equals(lookUpCourse)) {
							position = i;

							/*
							 * This if - else statement is used to add the number 0 before the @param
							 * section of the specific Course to form a string that is "0" + @param section
							 * If the @param section is less than 10, then the number 0 is added.
							 * Otherwise @param section remains the same.
							 */

							if (position < 10)
								System.out.println(lookUpCourse.getDeparment() + " " + lookUpCourse.getCode() + "."
										+ "0" + lookUpCourse.getSection() + " is found in the planner at postion "
										+ (position + 1));
							else
								System.out.println(lookUpCourse.getDeparment() + " " + lookUpCourse.getCode() + "."
										+ lookUpCourse.getSection() + " is found in the planner at position "
										+ (position + 1));

						}

					}

				} else {
					if (lookUpCourse.getSection() < 10)
						System.out.println(lookUpCourse.getDeparment() + " " + lookUpCourse.getCode() + "." + "0"
								+ lookUpCourse.getSection() + " is not found in the planner");
					else
						System.out.println(lookUpCourse.getDeparment() + " " + lookUpCourse.getCode() + "."
								+ lookUpCourse.getSection() + " is not found in the planner");
				}

				/*
				 * S - Size Determines the number of courses in the list.
				 */

			} else if (userInput.equals("S") || userInput.equals("s")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				System.out.println("There are " + userPlanner.size() + " courses in the planner");

				/*
				 * B - Backup Creates a copy of the given Planner. Changes to the copy will not
				 * affect the original and vice versa.
				 */

			} else if (userInput.equals("B") || userInput.equals("b")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				userPlannerBackUp = (Planner) userPlanner.clone();
				System.out.println("Created a backup of the current player");

				/*
				 * PB - Print Courses in Backup. Display all the courses from the backed-up
				 * list.
				 */

			} else if (userInput.equals("PB") || userInput.equals("pb")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				System.out.println("Planner (Backup):");
				userPlannerBackUp.printAllCourses();

				/*
				 * RB - Revert to Backup. Revert the current Planner to the backed up copy.
				 * list.
				 */

			} else if (userInput.equals("RB") || userInput.equals("rb")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				userPlanner = (Planner) userPlannerBackUp.clone();
				System.out.println("Planner succesfully reverted to the backup copy");

				/*
				 * Q - Quit. Terminates the program.
				 */

			} else if (userInput.equals("Q") || userInput.equals("q")) {
				input.nextLine(); // clear input stream
				System.out.print(""); // clear input stream
				System.out.println("Program terminating successfully...");
				break; // end the program

			} else {
				System.out.println("Wrong input! Please provide another input");

			}

		}

		input.close();
	}

}
