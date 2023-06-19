package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.warehouseapp.controller.generic.GenericController;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderMapper;

@Tag(name = "Orders",
        description = "Controller for orders")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends GenericController<OrderBodyDTO, Order> {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper) {
        super(orderService, orderMapper);
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

}
