package ru.ifmo.ctddev.tkachenko.task2;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gtkachenko
 * Date: 28.02.13
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */
public class WrongMatrixFormatException extends IOException {

    public WrongMatrixFormatException() {
        super();
    }

    public WrongMatrixFormatException(String message) {
        super(message);
    }

    public WrongMatrixFormatException(Throwable cause) {
        super(cause);
    }

    public WrongMatrixFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
