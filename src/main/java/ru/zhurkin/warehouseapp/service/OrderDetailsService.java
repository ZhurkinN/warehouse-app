package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;
import ru.zhurkin.warehouseapp.model.user.Role;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.order.OrderDetailsRepository;
import ru.zhurkin.warehouseapp.repository.order.OrderRepository;
import ru.zhurkin.warehouseapp.repository.order.StatusTypeRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.support.exception.RolePermissionsException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static ru.zhurkin.warehouseapp.model.enums.RoleEnum.COLLECTOR;
import static ru.zhurkin.warehouseapp.model.enums.RoleEnum.LOADER;
import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.*;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getAll() {
        return orderDetailsRepository.findAll();
    }

    public Page<OrderDetails> getAll(Pageable pageable) {
        return orderDetailsRepository.findAll(pageable);
    }

    public void delete(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_DETAILS_NOT_FOUND));
        orderDetailsRepository.delete(orderDetails);
    }

    public List<Order> getAvailableWorkersOrders(Long userId) {

        Role usersRole = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND))
                .getRole();
        List<Order> orders;
        if (usersRole.getRoleName().equals(LOADER.getRoleName())) {
            orders = orderRepository.findAvailableLoadersOrders();
        } else if (usersRole.getRoleName().equals(COLLECTOR.getRoleName())) {
            orders = orderRepository.findAvailableCollectorsOrders();
        } else {
            throw new RolePermissionsException(WRONG_ROLE_PERMISSIONS);
        }

        return orders;
    }

    @Transactional
    public OrderDetails startOrder(Long userId, Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        User worker = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        double totalPrice = 0d;
        for (OrderProducts orderProducts : order.getOrderProducts()) {
            totalPrice += orderProducts.getQuantity() * orderProducts.getProduct().getPrice();
        }
        OrderDetails orderDetails = new OrderDetails()
                .setOrder(order)
                .setWorker(worker)
                .setTotalPrice(totalPrice)
                .setStartDate(LocalDateTime.now());

        order.setStatusType(statusTypeRepository.findById(2L)
                .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND)));
        order.getOrderDetails().add(orderDetails);
        orderDetailsRepository.save(orderDetails);
        orderRepository.save(order);

        return orderDetails;
    }

    @Transactional
    public OrderDetails finishOrder(Long workerId,
                                    Long orderDetailsId) {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        if (!Objects.equals(orderDetails.getWorker().getId(), workerId)) {
            throw new RolePermissionsException(WRONG_ROLE_PERMISSIONS);
        }
        Order order = orderDetails.getOrder();
        order.setStatusType(statusTypeRepository.findById(3L)
                .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND)));
        orderDetails.setCloseDate(LocalDateTime.now());
        orderRepository.save(order);
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> getWorkersOrders(Long id) {

        User worker = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return orderDetailsRepository.findAllByWorker(worker);
    }
}
