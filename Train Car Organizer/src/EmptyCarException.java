/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 */
public class EmptyCarException extends RuntimeException {


	/**
     * Default constructor of the exception.
     */
    public EmptyCarException() { 
    	super();
    }

    /**
     * The constructor of the constructor that takes in a parameter
     * for the message.
     *
     * @param message
     *       The error message displayed.
     *      
     */
    public EmptyCarException(String message) {
        super(message);
}
}
