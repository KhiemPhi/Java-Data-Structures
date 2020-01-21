/**
 * @author Khiem Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 
 *     This class is used to create different Course objects using its constructors 
 *     as well as modifying those Course objects using the available getters and setters.
 *     
 *     Course objects are used in the Planner class, and exist as 
 *     elements in the arrays associated to Planner objects.
 *
 */
public class Course implements Cloneable {

	private String courseName;
	private String department;
	private int code;
	private byte section;
	private String instructor;

	/**
	 * Construct an empty Course object
	 * 
	 */
	public Course() {

	}

	/**
	 * Construct a Course object according the various parameters set by the
	 * Constructor.
	 * 
	 * @param courseName : Name of the Course object
	 * @param department : Department associated with the Course object
	 * @param code       : Code associated with the Course object
	 * @param section: Section associated with the Course object
	 * @param instructor: Instructor of the Course object
	 */
	public Course(String courseName, String department, int code, byte section, String instructor) {

		this.courseName = courseName;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;

		if (code <= 0) {
			throw new IllegalArgumentException("A wrong value for the course code was entered");
		} else if (section <= 0) {
			throw new IllegalArgumentException("A wrong value for the course section was entered");
		} else {

		}

	}

	/**
	 * Getter method for the @param courseName
	 * 
	 * @return the method returns the @param courseName of a specific Course Object
	 */
	public String getCourseName() {

		return courseName;
	}

	/**
	 * Setter method for the @param courseName
	 * 
	 * @param courseName: the value that @param this.CourseName would be set to.
	 */
	public void setCourseName(String courseName) {

		this.courseName = courseName;
	}

	/**
	 * Getter method for the @param department
	 * 
	 * @return the method returns the @param department of a specific Course Object
	 */
	public String getDeparment() {

		return department;
	}

	/**
	 * Setter method for the @param department
	 * 
	 * @param department the value that @param this.department would be set to.
	 */
	public void setDepartment(String department) {

		this.department = department;
	}

	/**
	 * Getter method for the @param code
	 * 
	 * @return the method returns the @param code of a specific Course Object
	 */
	public int getCode() {

		return code;
	}

	/**
	 * Setter method for the @param code
	 * 
	 * @param code the value that @param this.code would be set to.
	 */
	public void setCode(int code) {

		this.code = code;
	}

	/**
	 * Getter method for the @param section
	 * 
	 * @return the method returns the @param section of a specific Course Object
	 */
	public byte getSection() {

		return section;
	}

	/**
	 * Setter method for the @param section
	 * 
	 * @param section the value that @param this.section would be set to.
	 */
	public void setSection(byte section) {

		this.section = section;
	}

	/**
	 * Getter method for the @param instructor
	 * 
	 * @return the method returns the @param instructor of a specific Course Object
	 */
	public String getInstructor() {

		return instructor;
	}

	/**
	 * Setter method for the @param instructor
	 * 
	 * @param instructor the value that @param this.instructor would be set to.
	 */
	public void setInstructor(String instructor) {

		this.instructor = instructor;
	}

	 /**
     * Generates a copy of a specific Course object
     * 
     * Preconditions: This Planner object has been instantiated.
     *
     * @return
     *      Returns a deep copy of a specific Course object. Subsequent changes
     *      to the copy will not affect the original, nor vice versa. 
     *      The return value must be type-cast to a Course object
     *      before it can be used as the method only returns an Object object.
     */
	public Object clone() {

		try {
			return (Course) super.clone();
		} catch (CloneNotSupportedException e) {
			return new Course(this.getCourseName(), this.getDeparment(), this.getCode(), this.getSection(),
					this.getInstructor());

		}

	}

	 /**
     * Compares this.Course  to another specified Course object to check 
     * equality.
     *
     * @param obj
     *      An object that this.Course is compared to.
     *
     * @return
     *      If true, this indicates that this.Course refers to the same Course object 
     *      as the Object obj (same courseName, department, code, section and instructor values). 
     *      Otherwise, the return value is false.
     *           
     */
	public boolean equals(Object obj) {

		Course compare = (Course) obj;

		if (!(obj instanceof Course)) {
			return false;
		} else if (this.getCourseName().equals(compare.getCourseName())
				&& this.getDeparment().equals(compare.getDeparment()) 
				&& this.getCode() == compare.getCode()
				&& this.getSection() == compare.getSection() 
				&& this.getInstructor().equals(compare.getInstructor())) { //compare all the variables of both objects
			return true;

		} else {
			return false;
		}

	}

}
