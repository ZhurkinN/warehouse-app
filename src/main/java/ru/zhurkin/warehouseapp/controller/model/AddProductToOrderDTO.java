package ru.zhurkin.warehouseapp.controller.model;

public record AddProductToOrderDTO(
        Long productId,
        Long orderId,
        Integer quantity
) {
}
