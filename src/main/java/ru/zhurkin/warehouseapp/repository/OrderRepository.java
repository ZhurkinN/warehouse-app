package ru.zhurkin.warehouseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
