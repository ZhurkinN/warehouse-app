package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.model.HandleOrderDTO;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderMapper;

import java.util.List;

@Tag(name = "Assistant's actions",
        description = "Controller for all assistant's actions")
@RestController
@RequestMapping("/api/v1/assistants")
@RequiredArgsConstructor
public class AssistantActionsController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{id}")
    @Operation(method = "getAllAssistantsOrders",
            description = "Get all assistant's orders")
    public ResponseEntity<List<OrderBodyDTO>> getAssistantsOrders(@PathVariable Long id) {

        List<Order> orders = orderService.getAssistantsOrders(id);
        List<OrderBodyDTO> orderDtos = orderMapper.toDtos(orders);
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping("/handle")
    @Operation(method = "handleOrder",
            description = "Approve or decline order")
    public ResponseEntity<OrderBodyDTO> handleOrder(@RequestBody HandleOrderDTO requestDto) {

        Order order = orderService.proveOrder(requestDto.assistantId(),
                requestDto.orderId(),
                requestDto.isApproved());
        OrderBodyDTO orderDto = orderMapper.toDto(order);
        return ResponseEntity.ok(orderDto);
    }
}
