package ru.zhurkin.warehouseapp.support.dto.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericDTO {

    protected Long id;
    protected String createdBy = "Nikita Zhurkin";
    protected LocalDateTime createdWhen = LocalDateTime.now();
}
