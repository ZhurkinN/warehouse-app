package ru.zhurkin.warehouseapp.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "product_providers")
@Getter
@Setter
@NoArgsConstructor
public class ProductRef {

    private Long productId;
}
