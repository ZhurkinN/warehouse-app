package ru.zhurkin.warehouseapp.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(nativeQuery = true,
            value = """
                    select distinct p.*
                    from product p
                             left join product_providers pp on p.id = pp.product_id
                             join provider pr on pr.id = pp.provider_id
                    where p.title like ?1\s
                      and pr.name like ?2\s
                      and p.is_deleted = false
                         """)
    Page<Product> searchProducts(String productTitle,
                                 String providerName,
                                 Pageable pageable);
}
