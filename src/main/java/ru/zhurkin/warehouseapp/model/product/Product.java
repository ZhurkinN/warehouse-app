package ru.zhurkin.warehouseapp.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.zhurkin.warehouseapp.model.action.Action;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Product extends GenericModel {

    private String title;
    private String category;
    private String description;
    private Integer quantityLeft;
    private String measureUnit;
    private Double price;

    @MappedCollection(idColumn = "product_id")
    private Set<Action> actions = new HashSet<>();
}
