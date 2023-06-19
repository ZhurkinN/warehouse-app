package ru.zhurkin.warehouseapp.controller.model;

public record AddProductToProviderDTO(
        Long productId,
        Long providerId
) {
}
