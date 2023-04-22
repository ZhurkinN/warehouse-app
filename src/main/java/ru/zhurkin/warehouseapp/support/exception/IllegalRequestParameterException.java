package ru.zhurkin.warehouseapp.support.exception;

public class IllegalRequestParameterException extends IllegalArgumentException {

    public IllegalRequestParameterException() {
    }

    public IllegalRequestParameterException(String s) {
        super(s);
    }
}
