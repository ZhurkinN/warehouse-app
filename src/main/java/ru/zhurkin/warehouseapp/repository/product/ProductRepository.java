package ru.zhurkin.warehouseapp.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
