package ru.zhurkin.warehouseapp.model.generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class GenericModel {

    @Id
    protected Long id;
}
