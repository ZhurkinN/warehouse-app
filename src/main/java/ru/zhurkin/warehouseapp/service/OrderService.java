package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.repository.OrderRepository;
import ru.zhurkin.warehouseapp.repository.OrderTypeRepository;
import ru.zhurkin.warehouseapp.repository.StatusTypeRepository;
import ru.zhurkin.warehouseapp.repository.UserRepository;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.*;

@Service
@RequiredArgsConstructor
public class OrderService extends GenericService<Order> {

    private final OrderRepository orderRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final UserRepository userRepository;

    @Override
    public Order add(Order order) {

        userRepository.findById(order.getManager().getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        orderTypeRepository.findById(order.getOrderType().getId())
                .orElseThrow(() -> new NotFoundException(ORDER_TYPE_NOT_FOUND));
        statusTypeRepository.findById(order.getStatusType().getId())
                .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND));

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
    public Order update(Order order) {

        orderRepository.findById(order.getId())
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        return this.add(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND));
        orderRepository.deleteById(id);
    }
}
