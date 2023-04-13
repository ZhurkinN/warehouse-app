package ru.zhurkin.warehouseapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    private Long id;
    private String roleName;
}
