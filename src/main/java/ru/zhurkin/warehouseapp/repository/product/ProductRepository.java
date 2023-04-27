package ru.zhurkin.warehouseapp.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "select if (count(*) = 0, true, false)\n" +
            "from product\n" +
            "join order_products op on product.id = op.product_id\n" +
            "join orders o on o.id = op.order_id\n" +
            "where product.id = ?1 and o.status_type_id != 3")
    long canSoftDeleteProduct(Long id);
}
