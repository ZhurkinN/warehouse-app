package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.model.AddProductToOrderDTO;
import ru.zhurkin.warehouseapp.controller.model.CreateNewOrderDTO;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderMapper;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PRODUCT_WAS_DELETED;

@Tag(name = "Sales manager's actions",
        description = "Controller for all sales manager's actions")
@RestController
@RequestMapping("/api/v1/managers")
@RequiredArgsConstructor
public class SalesManagerActionsController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/create")
    @Operation(method = "createNewOrder",
            description = "Create new order")
    public ResponseEntity<OrderBodyDTO> createNewOrder(
            @RequestBody CreateNewOrderDTO requestDto) {
        Order order = orderService.createNewOrder(requestDto.assistantId(),
                requestDto.managerId(),
                requestDto.orderTypeId(),
                requestDto.description());
        OrderBodyDTO responseDto = orderMapper.toDto(order);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @Operation(method = "getManagersOrders",
            description = "Get all sale manager's created orders")
    public ResponseEntity<List<OrderBodyDTO>> getManagersOrders(@PathVariable Long id) {

        List<Order> orders = orderService.getManagersOrders(id);
        List<OrderBodyDTO> orderDtos = orderMapper.toDtos(orders);
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping("/add-product")
    @Operation(method = "addProduct",
            description = "Add product to the order")
    public ResponseEntity<OrderBodyDTO> addProduct(@RequestBody AddProductToOrderDTO requestDto) {

        Order order = orderService.addProduct(requestDto.orderId(),
                requestDto.productId(),
                requestDto.quantity());
        OrderBodyDTO responseDto = orderMapper.toDto(order);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/deleteProduct/{orderProductsId}")
    @Operation(method = "deleteProduct",
            description = "Delete product from the order")
    public ResponseEntity<String> deleteProduct(@PathVariable Long orderProductsId) {
        orderService.deleteProduct(orderProductsId);
        return ResponseEntity.ok(PRODUCT_WAS_DELETED);
    }

}
