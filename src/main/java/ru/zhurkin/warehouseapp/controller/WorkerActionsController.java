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
import ru.zhurkin.warehouseapp.service.OrderDetailsService;
import ru.zhurkin.warehouseapp.service.ProductService;
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

    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderMapper orderMapper;

    @PostMapping("/start")
    @Operation(method = "startWorkingOrder",
            description = "Start working on the order")
    public ResponseEntity<OrderDetailsBodyDTO> startWorkingOrder(@RequestBody StartOrderDTO requestDto) {

        OrderDetails orderDetails =
                orderDetailsService.startOrder(requestDto.workerId(), requestDto.orderId());
        return ResponseEntity.ok(orderDetailsMapper.toDto(orderDetails));
    }

    @PostMapping("/finish")
    @Operation(method = "finishWorkingOrder",
            description = "Finish working on the order after performing")
    public ResponseEntity<OrderDetailsBodyDTO> stopWorkingOrder(@RequestBody FinishOrderDTO requestDto) {

        OrderDetails orderDetails = orderDetailsService.finishOrder(requestDto.workerId(),
                requestDto.orderDetailsId());
        productService.setQuantityChanges(requestDto.orderDetailsId());
        return ResponseEntity.ok(orderDetailsMapper.toDto(orderDetails));
    }

    @GetMapping("/{id}")
    @Operation(method = "getAllWorkersOrders",
            description = "Get all worker's orders")
    public ResponseEntity<List<OrderDetailsBodyDTO>> getWorkersOrders(@PathVariable Long id) {

        List<OrderDetails> orders = orderDetailsService.getWorkersOrders(id);
        List<OrderDetailsBodyDTO> orderDtos = orderDetailsMapper.toDtos(orders);
        return ResponseEntity.ok(orderDtos);
    }

    @GetMapping("/available/{userId}")
    @Operation(method = "getAvailableWorkingOrders",
            description = "Get available for working orders")
    public ResponseEntity<List<OrderBodyDTO>> getAvailableWorkingOrders(@PathVariable Long userId) {

        List<Order> orders = orderDetailsService.getAvailableWorkersOrders(userId);
        List<OrderBodyDTO> orderDtos = orderMapper.toDtos(orders);
        return ResponseEntity.ok(orderDtos);
    }
}
