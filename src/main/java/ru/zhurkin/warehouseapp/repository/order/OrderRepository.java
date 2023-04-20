package ru.zhurkin.warehouseapp.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.user.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true,
            value = "select orders.*" +
                    "from orders " +
                    "where is_approved = true and status_type_id = 1 and order_type_id = 1")
    List<Order> findAvailableLoadersOrders();

    @Query(nativeQuery = true,
            value = "select orders.*" +
                    "from orders " +
                    "where is_approved = true and status_type_id = 1 and order_type_id = 2")
    List<Order> findAvailableCollectorsOrders();

    List<Order> findAllByAssistant(User assistant);
}
