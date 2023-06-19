package ru.zhurkin.warehouseapp.model.enums;

public enum RoleEnum {

    LOADER("Loader"),
    SALES_MANAGER("Sales manager"),
    MODERATOR("Moderator"),
    COLLECTOR("Collector"),
    ASSISTANT("Assistant");

    private String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
