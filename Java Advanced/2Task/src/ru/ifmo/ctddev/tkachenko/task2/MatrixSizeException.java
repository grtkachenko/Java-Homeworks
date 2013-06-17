package ru.ifmo.ctddev.tkachenko.task2;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixSizeException extends MatrixRuntimeException {
    public MatrixSizeException() {
        super("Wrong number of elements in matrix");
    }

    public MatrixSizeException(String message) {
        super(message);
    }

    public MatrixSizeException(Throwable cause) {
        super(cause);
    }

    public MatrixSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
