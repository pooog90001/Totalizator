package by.epam.litvin.exception;

public class ReceiverException extends Exception {
    /**
     * Default constructor.
     */
    public ReceiverException() {
        super();
    }

    /**
     * Constructor with message parameter.
     *
     * @param message
     */
    public ReceiverException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause parameters.
     *
     * @param message
     * @param cause
     */
    public ReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause parameter.
     *
     * @param cause
     */
    public ReceiverException(Throwable cause) {
        super(cause);
    }
}
