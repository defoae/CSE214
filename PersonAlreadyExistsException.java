/*
 * Thrown when adding a person whose name already exists in the manager.
 * public PersonAlreadyExistsException(String message)
 * Parameters: message – description of the error
 * Returns: none (constructor)
 */
public class PersonAlreadyExistsException extends Exception {
    public PersonAlreadyExistsException(String message){
        super(message);
    }
}
