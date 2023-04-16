package ru.zhurkin.warehouseapp.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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
public class Order extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id",
            nullable = false)
    private User manager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_type_id",
            nullable = false)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_type_id")
    private StatusType statusType;

    private String description;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isApproved;

    @OneToMany(mappedBy = "order")
    private List<OrderProducts> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails = new HashSet<>();

}
