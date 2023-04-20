package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.model.FinishOrderDTO;
import ru.zhurkin.warehouseapp.controller.model.StartOrderDTO;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.dto.OrderDetailsBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderDetailsMapper;
import ru.zhurkin.warehouseapp.support.mapper.OrderMapper;

import java.util.List;

@Tag(name = "Worker's actions",
        description = "Controller for all worker's actions (loader and collector)")
@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
public class WorkerActionsController {

    private final OrderService orderService;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderMapper orderMapper;

    @PostMapping("/start")
    @Operation(method = "startWorkingOrder",
            description = "Start working on the order")
    public ResponseEntity<OrderDetailsBodyDTO> startWorkingOrder(@RequestBody StartOrderDTO requestDto) {

        OrderDetails orderDetails =
                orderService.startOrder(requestDto.workerId(), requestDto.orderId());
        return ResponseEntity.ok(orderDetailsMapper.toDto(orderDetails));
    }

    @PostMapping("/finish")
    @Operation(method = "finishWorkingOrder",
            description = "Finish working on the order after performing")
    public ResponseEntity<OrderDetailsBodyDTO> stopWorkingOrder(@RequestBody FinishOrderDTO requestDto) {

        OrderDetails orderDetails =
                orderService.finishOrder(requestDto.workerId(), requestDto.orderDetailsId());
        return ResponseEntity.ok(orderDetailsMapper.toDto(orderDetails));
    }

    @GetMapping("/all/{id}")
    @Operation(method = "getAllWorkersOrders",
            description = "Get all worker's orders")
    public ResponseEntity<List<OrderDetailsBodyDTO>> getAllWorkersOrders(@PathVariable Long id) {

        List<OrderDetails> orders = orderService.getWorkersOrders(id);
        return ResponseEntity.ok(orderDetailsMapper.toDtos(orders));
    }

    @GetMapping("/available/{userId}")
    @Operation(method = "getAvailableWorkingOrders",
            description = "Get available for working orders")
    public ResponseEntity<List<OrderBodyDTO>> getAvailableWorkingOrders(@PathVariable Long userId) {

        List<Order> orders = orderService.getAvailableOrders(userId);
        List<OrderBodyDTO> orderDtos = orderMapper.toDtos(orders);
        return ResponseEntity.ok(orderDtos);
    }
}
