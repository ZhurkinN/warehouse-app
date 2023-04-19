package ru.zhurkin.warehouseapp.support.mapper;

import org.springframework.stereotype.Component;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.support.dto.OrderDetailsBodyDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailsMapper {

    public OrderDetailsBodyDTO toDto(OrderDetails orderDetails) {

        return new OrderDetailsBodyDTO()
                .setId(orderDetails.getId())
                .setStartDate(orderDetails.getStartDate())
                .setCloseDate(orderDetails.getCloseDate())
                .setTotalValue(orderDetails.getTotalPrice())
                .setOrderId(orderDetails.getOrder().getId())
                .setWorkerId(orderDetails.getWorker().getId());
    }

    public List<OrderDetailsBodyDTO> toDtos(List<OrderDetails> orderDetails) {

        return orderDetails.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
