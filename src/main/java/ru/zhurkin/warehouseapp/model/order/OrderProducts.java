package ru.zhurkin.warehouseapp.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import ru.zhurkin.warehouseapp.model.product.Product;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_products")
@Check(constraints = "quantity > 0")
public class OrderProducts {

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
    @JoinColumn(name = "product_id",
            nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
