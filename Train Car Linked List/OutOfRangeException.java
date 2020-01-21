/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 */
public class OutOfRangeException extends RuntimeException {

	/**
     * Default constructor of the exception.
     */
    public OutOfRangeException() { 
    	super();
    }

    /**
     * The constructor of the constructor that takes in a parameter
     * for the message.
     *
     * @param message
     *      The error message displayed.
     *     
     */
    public OutOfRangeException(String message) {
        super(message);
}

}
