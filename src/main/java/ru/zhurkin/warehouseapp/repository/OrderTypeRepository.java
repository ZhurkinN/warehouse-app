package ru.zhurkin.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.order.OrderType;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {
}
