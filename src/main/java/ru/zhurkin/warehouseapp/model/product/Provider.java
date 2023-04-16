package ru.zhurkin.warehouseapp.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Provider extends GenericModel {

    @Column(nullable = false)
    private String name;
    private String address;
    private String telephoneNumber;
    private String email;

    @ManyToMany(mappedBy = "providers")
    private List<Product> products;
}
