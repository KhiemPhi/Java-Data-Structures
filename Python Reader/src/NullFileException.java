 /** The class NullFileException is an exception that is thrown when a file's name
 * is null.
 *
 * @author Khiem Phi
 * 	e-mail: khiem.phi@stonybrook.edu
 * 	Stony Brook University ID : 111667279	
 */
public class NullFileException extends RuntimeException {

	/**
	 * Construct an <code>NullFileException</code> according to the parent class
	 * <code>RuntimeException</code>
	 *
	 */
	public NullFileException() {

		super();

	}
	
	/**
	 * Construct an <code>NullFileException</code> according to the parent class
	 * <code>RuntimeExpcetion</code>
	 * 
	 * This Constructor creates an exception that also displays an error message
	 * specified by the variable message in the constructor
	 * 
	 * @param message : The error message that would be shown when the exception is
	 *                thrown.
	 */
	public NullFileException(String message) {

		super(message);

	}

	

	

}
