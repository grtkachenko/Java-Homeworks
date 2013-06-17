package ru.ifmo.ctddev.tkachenko.task6;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:24 PM
 */

/**
 * Custom exception class that corresponds to runtime errors with matrix
 *
 * @see RuntimeException
 */
public class MatrixRuntimeException extends RuntimeException {

    /**
     * Constructor from message of exception
     *
     * @param message an exception message
     */
    public MatrixRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor from cause of exception
     *
     * @param cause an exception cause
     */
    public MatrixRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor from message and cause of exception
     *
     * @param message an exception message
     * @param cause an exception cause
     */
    public MatrixRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
