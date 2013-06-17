package ru.ifmo.ctddev.tkachenko.task2;

/**
 * Created with IntelliJ IDEA.
 * User: Grigory
 * Date: 2/21/13
 * Time: 2:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixArithmeticException extends MatrixRuntimeException {
    public MatrixArithmeticException() {
        super("Wrong matrix arithmetic actions");
    }

    public MatrixArithmeticException(String message) {
        super(message);
    }

    public MatrixArithmeticException(Throwable cause) {
        super(cause);
    }

    public MatrixArithmeticException(String message, Throwable cause) {
        super(message, cause);
    }
}
