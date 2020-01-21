/**
 * @author Khiem Dinh Phi 
 * 		e-mail: khiem.phi@stonybrook.edu 
 * 		Stony Brook University
 * 		ID: 111667279
 */
public class EndOfListException extends RuntimeException {
	
	/**
     * Default constructor of the exception.
     */
    public EndOfListException() { super();}

    /**
     * The constructor of the constructor that takes in a parameter
     * for the message.
     *
     * @param message
     *      The error message displayed.
     *      
     */
    public EndOfListException(String message) {
        super(message);
}

}
