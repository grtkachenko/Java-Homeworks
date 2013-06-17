package ru.ifmo.ctddev.tkachenko.task2;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixRuntimeException extends RuntimeException {

    public MatrixRuntimeException(String message) {
        super(message);
    }

    public MatrixRuntimeException(Throwable cause) {
        super(cause);
    }

    public MatrixRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
