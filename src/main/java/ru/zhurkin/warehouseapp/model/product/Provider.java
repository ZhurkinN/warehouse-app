package ru.zhurkin.warehouseapp.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Provider extends GenericModel {

    private String name;
    private String address;
    private String telephoneNumber;
    private String email;

    @MappedCollection(idColumn = "provider_id")
    private List<ProductRef> products = new ArrayList<>();
}
