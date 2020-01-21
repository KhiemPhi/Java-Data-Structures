 /** The class EmptyStackException is an exception that is thrown when there is no element in 
  * BlockStack to be popped
 *
 * @author Khiem Phi
 * 	e-mail: khiem.phi@stonybrook.edu
 * 	Stony Brook University ID : 111667279	
 */
public class EmtpyStackException extends RuntimeException {

	/**
	 * Construct an <code>EmptyStackException</code> according to the parent class
	 * <code>RuntimeException</code>
	 *
	 */
	public EmtpyStackException() {

		super();

	}
	
	/**
	 * Construct an <code>EmptyStackException</code> according to the parent class
	 * <code>RuntimeExpcetion</code>
	 * 
	 * This Constructor creates an exception that also displays an error message
	 * specified by the variable message in the constructor
	 * 
	 * @param message : The error message that would be shown when the exception is
	 *                thrown.
	 */
	public EmtpyStackException(String message) {

		super(message);

	}

	

	

}
