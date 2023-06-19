package ru.zhurkin.warehouseapp.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.product.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Page<Provider> findAllByNameContainsIgnoreCaseAndIsDeletedFalse(String name,
                                                                    Pageable pageable);
}
