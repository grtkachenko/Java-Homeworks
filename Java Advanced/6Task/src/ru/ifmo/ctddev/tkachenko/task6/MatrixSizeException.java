package ru.ifmo.ctddev.tkachenko.task6;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:26 PM
 */

/**
 * Custom exception class that corresponds to errors with creating a matrix with wrong size
 *
 * @see RuntimeException
 */
public class MatrixSizeException extends MatrixRuntimeException {

    /**
     * Default constructor
     */
    public MatrixSizeException() {
        super("Wrong number of elements in matrix");
    }

    /**
     * Constructor from message of exception
     *
     * @param message an exception message
     */
    public MatrixSizeException(String message) {
        super(message);
    }

    /**
     * Constructor from cause of exception
     *
     * @param cause an exception cause
     */
    public MatrixSizeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor from message and cause of exception
     *
     * @param message an exception message
     * @param cause   an exception cause
     */
    public MatrixSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
