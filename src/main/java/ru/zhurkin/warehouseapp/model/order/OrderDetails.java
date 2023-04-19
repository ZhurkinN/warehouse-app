package ru.zhurkin.warehouseapp.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.zhurkin.warehouseapp.model.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_details")
@Accessors(chain = true)
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",
            nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id",
            nullable = false)
    private User worker;

    @Column(columnDefinition = "timestamp default now()")
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(columnDefinition = "timestamp default null")
    private LocalDateTime closeDate;

    @Column(nullable = false,
            columnDefinition = "bigint default 0")
    private Double totalPrice;
}
