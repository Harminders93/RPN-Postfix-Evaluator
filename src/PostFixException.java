/**
 * 
 * @author HPS251 - Credit to Professor Klukowska
 * PostFixException class which extends Exception
 *
 */
public class PostFixException extends Exception {

	//Constructor, calls on it's parents constructor
	public PostFixException() {
		super();
	}

	//Prints out the message given.
	public PostFixException(String message) {
		super(message);
	}
}