package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;
import ru.zhurkin.warehouseapp.model.order.OrderType;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.order.OrderProductsRepository;
import ru.zhurkin.warehouseapp.repository.order.OrderRepository;
import ru.zhurkin.warehouseapp.repository.order.OrderTypeRepository;
import ru.zhurkin.warehouseapp.repository.order.StatusTypeRepository;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.service.generic.GenericService;
import ru.zhurkin.warehouseapp.support.exception.IllegalRequestParameterException;
import ru.zhurkin.warehouseapp.support.exception.RolePermissionsException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static ru.zhurkin.warehouseapp.model.enums.RoleEnum.ASSISTANT;
import static ru.zhurkin.warehouseapp.model.enums.RoleEnum.SALES_MANAGER;
import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.*;

@Service
@RequiredArgsConstructor
public class OrderService extends GenericService<Order> {

    private final OrderRepository orderRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductsRepository orderProductsRepository;

    @Override
    @Transactional
    public Order add(Order order) {

        userRepository.findById(order.getManager().getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        orderTypeRepository.findById(order.getOrderType().getId())
                .orElseThrow(() -> new NotFoundException(ORDER_TYPE_NOT_FOUND));
        statusTypeRepository.findById(order.getStatusType().getId())
                .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND));
        order.setOrderDetails(new HashSet<>());
        order.setOrderProducts(new ArrayList<>());

        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order update(Order order) {

        orderRepository.findById(order.getId())
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        return this.add(order);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        orderRepository.delete(order);
    }

    @Override
    public boolean softDelete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        boolean isDeleted = false;
        if (orderRepository.canSoftDeleteOrder(id) == 1) {
            order.setIsDeleted(true);
            order.setDeletedBy("ADMIN");
            order.setDeletedWhen(LocalDateTime.now());
            orderRepository.save(order);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public void restore(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        if (order.getIsDeleted()) {
            order.setIsDeleted(false);
            order.setDeletedBy(null);
            order.setDeletedWhen(null);
        }
        orderRepository.save(order);
    }

    public List<Order> getAssistantsOrders(Long id) {

        User assistant = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return orderRepository.findAllByAssistant(assistant);
    }

    @Transactional
    public Order proveOrder(Long assistantId,
                            Long orderId,
                            Boolean approved) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        if (!Objects.equals(order.getAssistant().getId(), assistantId)) {
            throw new RolePermissionsException(WRONG_ROLE_PERMISSIONS);
        }
        if (!order.getIsApproved()) {
            order.setIsApproved(approved);
            if (!approved) {
                order.setStatusType(statusTypeRepository.findById(4L)
                        .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND)));
            }
        }
        return orderRepository.save(order);
    }

    public Order createNewOrder(Long assistantId,
                                Long managerId,
                                Long orderTypeId,
                                String description,
                                String contactNumber) {
        User assistant = userRepository.findById(assistantId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        OrderType orderType = orderTypeRepository.findById(orderTypeId)
                .orElseThrow(() -> new NotFoundException(ORDER_TYPE_NOT_FOUND));
        if (!manager.getRole().getRoleName().equals(SALES_MANAGER.getRoleName())
                || !assistant.getRole().getRoleName().equals(ASSISTANT.getRoleName())) {
            throw new RolePermissionsException(WRONG_ROLE_PERMISSIONS);
        }
        Order order = new Order()
                .setAssistant(assistant)
                .setManager(manager)
                .setOrderType(orderType)
                .setDescription(description)
                .setStatusType(statusTypeRepository.findById(1L)
                        .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND)))
                .setContactNumber(contactNumber);
        return orderRepository.save(order);
    }

    public List<Order> getManagersOrders(Long id) {

        User manager = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return orderRepository.findAllByManager(manager);
    }

    @Transactional
    public Order addProduct(Long orderId,
                            Long productId,
                            Integer quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        if (quantity > product.getQuantityLeft()) {
            throw new IllegalRequestParameterException(NOT_ENOUGH_PRODUCTS);
        }
        OrderProducts orderProducts = new OrderProducts()
                .setOrder(order)
                .setProduct(product)
                .setQuantity(quantity);
        orderProductsRepository.save(orderProducts);
        return order;
    }

    public void deleteProduct(Long orderProductsId) {
        OrderProducts orderProducts = orderProductsRepository.findById(orderProductsId)
                .orElseThrow(() -> new NotFoundException(ORDER_PRODUCTS_NOT_FOUND));
        orderProductsRepository.delete(orderProducts);
    }

    public Page<Order> getAssistantOrManagerOrders(Long id,
                                                   Pageable pageable) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Page<Order> orders;
        if (user.getRole().getRoleName().equals(ASSISTANT.getRoleName())) {
            orders = orderRepository.findAllByAssistant(user, pageable);
        } else if (user.getRole().getRoleName().equals(SALES_MANAGER.getRoleName())) {
            orders = orderRepository.findAllByManager(user, pageable);
        } else {
            throw new RolePermissionsException(WRONG_ROLE_PERMISSIONS);
        }
        return orders;
    }
}
