package ru.zhurkin.warehouseapp.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    List<Order> findAllByManager(User manager);

    @Query(nativeQuery = true, value = "select if (count(*) = 0, true, false)\n" +
            "from orders\n" +
            "where orders.id = ?1 and status_type_id != 3")
    long canSoftDeleteOrder(Long orderId);

    Page<Order> findAllByAssistant(User assistant,
                                   Pageable pageable);

    Page<Order> findAllByManager(User manager,
                                 Pageable pageable);

    @Query(nativeQuery = true,
            value = "select orders.*" +
                    "from orders " +
                    "where is_approved = true and status_type_id = 1 and order_type_id = 1")
    Page<Order> findAvailableLoadersOrders(PageRequest pageRequest);

    @Query(nativeQuery = true,
            value = "select orders.*" +
                    "from orders " +
                    "where is_approved = true and status_type_id = 1 and order_type_id = 2")
    Page<Order> findAvailableCollectorsOrders(Pageable pageable);
}
