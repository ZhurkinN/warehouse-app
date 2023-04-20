package ru.zhurkin.warehouseapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;
import ru.zhurkin.warehouseapp.repository.order.OrderDetailsRepository;
import ru.zhurkin.warehouseapp.repository.order.OrderProductsRepository;
import ru.zhurkin.warehouseapp.repository.order.OrderTypeRepository;
import ru.zhurkin.warehouseapp.repository.order.StatusTypeRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.generic.GenericMapper;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.*;

@Component
@RequiredArgsConstructor
public class OrderMapper extends GenericMapper<Order, OrderBodyDTO> {

    private final OrderTypeRepository orderTypeRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderProductsRepository orderProductsRepository;

    @Override
    public Order toEntity(OrderBodyDTO dto) {

        Order order = new Order()
                .setDescription(dto.getDescription())
                .setIsApproved(dto.getIsApproved())
                .setOrderType(orderTypeRepository
                        .findById(dto.getOrderTypeId())
                        .orElseThrow(() -> new NotFoundException(ORDER_TYPE_NOT_FOUND)))
                .setStatusType(statusTypeRepository
                        .findById(dto.getStatusTypeId())
                        .orElseThrow(() -> new NotFoundException(STATUS_TYPE_NOT_FOUND)))
                .setManager(userRepository
                        .findById(dto.getManagerId())
                        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND)))
                .setAssistant(userRepository
                        .findById(dto.getAssistantId())
                        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND)))
                .setOrderProducts(dto.getOrderProductIds()
                        .stream()
                        .map(orderProductsRepository::findById)
                        .map(Optional::orElseThrow)
                        .collect(Collectors.toList()));
        if (Objects.isNull(dto.getOrderDetailIds()) || dto.getOrderDetailIds().isEmpty()) {
            order.setOrderDetails(new HashSet<>());
        } else {
            order.setOrderDetails(dto.getOrderDetailIds()
                    .stream()
                    .map(orderDetailsRepository::findById)
                    .map(Optional::orElseThrow)
                    .collect(Collectors.toSet()));
        }
        order.setId(dto.getId());
        order.setCreatedBy(dto.getCreatedBy());
        order.setCreatedWhen(dto.getCreatedWhen());

        return order;
    }

    @Override
    public OrderBodyDTO toDto(Order order) {
        Set<OrderDetails> orderDetails = order.getOrderDetails().isEmpty()
                ? new HashSet<>() : order.getOrderDetails();
        return new OrderBodyDTO(
                order.getId(),
                order.getCreatedBy(),
                order.getCreatedWhen(),
                order.getManager().getId(),
                order.getAssistant().getId(),
                order.getOrderType().getId(),
                order.getStatusType().getId(),
                order.getDescription(),
                order.getIsApproved(),
                order.getOrderProducts()
                        .stream()
                        .map(OrderProducts::getId)
                        .collect(Collectors.toList()),
                orderDetails.stream()
                        .map(OrderDetails::getId)
                        .collect(Collectors.toSet()));
    }

}
