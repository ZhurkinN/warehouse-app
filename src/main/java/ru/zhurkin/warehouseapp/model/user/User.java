package ru.zhurkin.warehouseapp.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class User extends GenericModel {

    @Column(nullable = false,
            unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;
    private String middleName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",
            nullable = false)
    private Role role;

    @OneToMany(mappedBy = "manager")
    private Set<Order> managerOrders = new HashSet<>();

    @OneToMany(mappedBy = "worker")
    private Set<OrderDetails> workerOrders = new HashSet<>();

}
