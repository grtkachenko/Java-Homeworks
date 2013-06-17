package ru.ifmo.ctddev.tkachenko.task6;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:27 PM
 */

/**
 * Custom exception class that corresponds to actions with wrong indexes in matrix
 *
 * @see RuntimeException
 */
public class MatrixOutOfBoundException extends MatrixRuntimeException {
    /**
     * Default constructor
     */
    public MatrixOutOfBoundException() {
        super("Action with wrong indexes");
    }

    /**
     * Constructor from message of exception
     *
     * @param message an exception message
     */
    public MatrixOutOfBoundException(String message) {
        super(message);
    }

    /**
     * Constructor from cause of exception
     *
     * @param cause an exception cause
     */
    public MatrixOutOfBoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor from message and cause of exception
     *
     * @param message an exception message
     * @param cause   an exception cause
     */
    public MatrixOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
