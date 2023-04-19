package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.model.StartOrderDTO;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.support.dto.OrderDetailsBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderDetailsMapper;

import java.util.List;

@Tag(name = "Order details",
        description = "Controller for worker's orders")
@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderService orderService;
    private final OrderDetailsMapper orderDetailsMapper;

    @PostMapping("/start")
    @Operation(method = "startWorkingOrder",
            description = "Start working on the order")
    public ResponseEntity<OrderDetailsBodyDTO> startWorkingOrder(@RequestBody StartOrderDTO requestDto) {

        OrderDetails orderDetails =
                orderService.startOrder(requestDto.userId(), requestDto.orderId());
        return ResponseEntity.ok(orderDetailsMapper.toDto(orderDetails));
    }

    @PostMapping("/finish/{orderId}")
    @Operation(method = "finishWorkingOrder",
            description = "Finish working on the order after performing")
    public ResponseEntity<OrderDetailsBodyDTO> stopWorkingOrder(@PathVariable Long orderDetailsId) {

        OrderDetails orderDetails =
                orderService.finishOrder(orderDetailsId);
        return ResponseEntity.ok(orderDetailsMapper.toDto(orderDetails));
    }

    @GetMapping("/all/{id}")
    @Operation(method = "getAllWorkersOrders",
            description = "Get all worker's orders")
    public ResponseEntity<List<OrderDetailsBodyDTO>> getAllWorkersOrders(@PathVariable Long id) {

        List<OrderDetails> orders = orderService.getWorkersOrders(id);
        return ResponseEntity.ok(orderDetailsMapper.toDtos(orders));
    }
}
