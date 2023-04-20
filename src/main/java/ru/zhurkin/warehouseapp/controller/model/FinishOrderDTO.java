package ru.zhurkin.warehouseapp.controller.model;

public record FinishOrderDTO(
        Long workerId,
        Long orderDetailsId
) {
}
