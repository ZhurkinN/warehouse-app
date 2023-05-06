package ru.zhurkin.warehouseapp.controller.model;

public record CreateNewOrderDTO(
        Long managerId,
        Long assistantId,
        Long orderTypeId,
        String description,
        String contactNumber
) {
}
