package ru.zhurkin.warehouseapp.model.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Check;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Check(constraints = "price > 0 and quantity_left >= 0")
public class Product extends GenericModel {

    @Column(nullable = false)
    private String title;
    private String category;
    private String description;

    @Column(nullable = false)
    private Integer quantityLeft;
    private String measureUnit;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String warehousePosition;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_providers",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "provider_id",
                    referencedColumnName = "id"
            )
    )
    private List<Provider> providers = new ArrayList<>();

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.REMOVE)
    private List<OrderProducts> orderProducts = new ArrayList<>();

}
