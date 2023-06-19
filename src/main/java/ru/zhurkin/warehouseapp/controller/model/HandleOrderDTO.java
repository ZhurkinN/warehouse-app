package ru.zhurkin.warehouseapp.controller.model;

public record HandleOrderDTO(
        Long assistantId,
        Long orderId,
        Boolean isApproved
) {
}
