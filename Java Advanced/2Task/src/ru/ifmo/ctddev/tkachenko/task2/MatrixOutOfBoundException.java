package ru.ifmo.ctddev.tkachenko.task2;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/17/13
 * Time: 9:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixOutOfBoundException extends MatrixRuntimeException {
    public MatrixOutOfBoundException() {
        super("Action with wrong indexes");
    }

    public MatrixOutOfBoundException(String message) {
        super(message);
    }

    public MatrixOutOfBoundException(Throwable cause) {
        super(cause);
    }

    public MatrixOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
