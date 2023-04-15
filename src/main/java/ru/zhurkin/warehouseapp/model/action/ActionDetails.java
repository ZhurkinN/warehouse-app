package ru.zhurkin.warehouseapp.model.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ActionDetails {

    private LocalDateTime closeDate;
    private Double totalPrice;

}
