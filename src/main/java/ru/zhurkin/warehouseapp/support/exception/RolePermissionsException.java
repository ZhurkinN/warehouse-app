package ru.zhurkin.warehouseapp.support.exception;

public class RolePermissionsException extends RuntimeException {

    public RolePermissionsException() {
        super();
    }

    public RolePermissionsException(String message) {
        super(message);
    }
}
