package ru.zhurkin.warehouseapp.model.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Provider extends GenericModel {

    @Column(nullable = false)
    private String name;
    private String address;
    private String telephoneNumber;
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_providers",
            joinColumns = @JoinColumn(
                    name = "provider_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )
    )
    private List<Product> products = new ArrayList<>();
}
