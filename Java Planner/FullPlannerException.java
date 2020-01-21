/**
 * @author Khiem Phi
 * 		e-mail: khiem.phi@stonybrook.edu
 * 		Stony Brook University		
 *
 */
public class FullPlannerException extends RuntimeException {
	
	/**
	 * Construct a FullPlannerException according to the parent class RuntimeException
	 *
	 */
	public FullPlannerException() {
		
		super();
		
	}
	
	/**
	 * Construct a FullPlannerException according to the parent class RuntimeExpcetion 
	 * 
	 * This Constructor creates an exception that also displays an error message 
	 * 		specified by the variable message in the constructor
	 * 
	 * @param message : The error message that would be shown when the exception is thrown.
	 */
	public FullPlannerException(String message) {
		
		super(message);
	}


	
}

