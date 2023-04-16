package ru.zhurkin.warehouseapp.support.exception.model;

import java.time.LocalDateTime;

public record ErrorDTO(
        String path,
        String message,
        int statusCode,
        LocalDateTime time
) {
}
