package ru.ifmo.ctddev.tkachenko.task6;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/21/13
 * Time: 2:16 AM
 */

/**
 * Custom exception class that corresponds to arithmetic exceptions in matrix
 *
 * @see RuntimeException
 */
public class MatrixArithmeticException extends MatrixRuntimeException {
    /**
     * Default constructor
     */
    public MatrixArithmeticException() {
        super("Wrong matrix arithmetic actions");
    }

    /**
     * Constructor from message of exception
     *
     * @param message an exception message
     */
    public MatrixArithmeticException(String message) {
        super(message);
    }

    /**
     * Constructor from cause of exception
     *
     * @param cause an exception cause
     */
    public MatrixArithmeticException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor from message and cause of exception
     *
     * @param message an exception message
     * @param cause   an exception cause
     */
    public MatrixArithmeticException(String message, Throwable cause) {
        super(message, cause);
    }
}
