package ru.zhurkin.warehouseapp.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.model.generic.GenericModel;
import ru.zhurkin.warehouseapp.model.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@Accessors(chain = true)
public class Order extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "manager_id",
            nullable = false)
    private User manager;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "assistant_id",
            nullable = false)
    private User assistant;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "order_type_id",
            nullable = false)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    @JoinColumn(name = "status_type_id",
            columnDefinition = "bigint default 1")
    private StatusType statusType;

    private String description;

    @Column(columnDefinition = "bool default '0'")
    private Boolean isApproved = false;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.REMOVE)
    private List<OrderProducts> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.REMOVE)
    private Set<OrderDetails> orderDetails = new HashSet<>();

}
