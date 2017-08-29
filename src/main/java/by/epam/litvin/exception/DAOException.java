package by.epam.litvin.exception;

public class DAOException extends Exception {
    /**
     * Default constructor.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructor with message parameter.
     *
     * @param message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause parameters.
     *
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause parameter.
     *
     * @param cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
