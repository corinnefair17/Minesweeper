/**
 * 
 * This should be an exception that contains a message saying what the error
 * was.
 * 
 * @author <student>
 * @version 0
 *
 */
public class MineFieldException extends RuntimeException {

    /**
     * Construct exception given message
     * 
     * @param msg
     *            - string saying what went wrong
     */
    public MineFieldException(String msg) {
        super(msg);
    }
}
