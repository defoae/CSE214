/*
 * Thrown when get or remove is called for a name that is not in the manager.
 * public PersonDoesNotExistException(String message)
 * Parameters: message – description of the error
 * Returns: none (constructor)
 */
public class PersonDoesNotExistException extends Exception {
    public PersonDoesNotExistException(String message){
        super(message);
    }
}
