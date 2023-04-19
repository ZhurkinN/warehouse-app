package ru.zhurkin.warehouseapp.support.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDetailsBodyDTO {

    private Long id;
    private Long orderId;
    private Long workerId;
    private LocalDateTime startDate;
    private LocalDateTime closeDate;
    private Double totalValue;
}
