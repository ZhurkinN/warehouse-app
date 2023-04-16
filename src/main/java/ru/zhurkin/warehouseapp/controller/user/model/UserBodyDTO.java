package ru.zhurkin.warehouseapp.controller.user.model;

public record UserBodyDTO(
        Long id,
        String login,
        String password,
        String firstName,
        String middleName,
        String lastName,
        Long roleId
) {
}
