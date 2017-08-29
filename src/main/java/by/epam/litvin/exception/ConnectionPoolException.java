package by.epam.litvin.exception;

public class ConnectionPoolException extends Exception {
    /**
     * Default constructor.
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Constructor with message parameter.
     *
     * @param message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause parameters.
     *
     * @param message
     * @param cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause parameter.
     *
     * @param cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
