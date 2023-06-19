package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.model.AddProductToOrderDTO;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;
import ru.zhurkin.warehouseapp.model.user.User;
import ru.zhurkin.warehouseapp.repository.order.OrderProductsRepository;
import ru.zhurkin.warehouseapp.repository.order.OrderTypeRepository;
import ru.zhurkin.warehouseapp.repository.order.StatusTypeRepository;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.service.OrderDetailsService;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.service.ProductService;
import ru.zhurkin.warehouseapp.service.UserService;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderMapper;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class MVCOrderController {

    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final ProductRepository productRepository;
    private final OrderProductsRepository orderProductsRepository;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        Page<Order> orderPage = orderService.getAll(pageRequest);
        Page<OrderBodyDTO> orderDtoPage = new PageImpl<>(orderMapper.toDtos(orderPage.getContent()), pageRequest, orderPage.getTotalElements());
        model.addAttribute("orders", orderDtoPage);
        model.addAttribute("userRepository", userRepository);
        model.addAttribute("typeRepository", orderTypeRepository);
        model.addAttribute("statusRepository", statusTypeRepository);
        return "/orders/viewOrders";
    }

    @GetMapping("/history/{id}")
    public String getHistory(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "5") int pageSize,
                             @PathVariable Long id,
                             Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdWhen"));
        Page<Order> orderPage = orderService.getAssistantOrManagerOrders(id, pageRequest);
        Page<OrderBodyDTO> orderDtoPage = new PageImpl<>(orderMapper.toDtos(orderPage.getContent()), pageRequest, orderPage.getTotalElements());
        model.addAttribute("orders", orderDtoPage);
        model.addAttribute("userRepository", userRepository);
        model.addAttribute("typeRepository", orderTypeRepository);
        model.addAttribute("statusRepository", statusTypeRepository);
        return "/orders/viewOrders";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        List<String> assistantLogins = userService.getAllAssistants()
                .stream()
                .map(User::getLogin)
                .toList();
        model.addAttribute("assistants", assistantLogins);
        return "orders/addOrder";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute("orderForm") OrderBodyDTO dto,
                           @RequestParam(value = "login", required = false) String login) {
        User assistant = userRepository.findUserByLogin(login);
        dto.setAssistantId(assistant.getId());
        dto.setStatusTypeId(1L);
        dto.setIsApproved(false);
        orderService.add(orderMapper.toEntity(dto));
        return "redirect:/orders";
    }

    @GetMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id,
                              Model model) {
        model.addAttribute("order", orderMapper.toDto(orderService.getById(id)));
        return "orders/updateOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute("orderForm") OrderBodyDTO dto) {
        orderService.update(orderMapper.toEntity(dto));
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.softDelete(id);
        return "redirect:/orders";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        orderService.restore(id);
        return "redirect:/orders";
    }

    @GetMapping("/add-product/{id}")
    public String addProduct(@PathVariable Long id,
                             Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("orderId", id);
        return "orders/addProduct";
    }

    @PostMapping("/add-product")
    public String addBook(@ModelAttribute("orderProductForm") AddProductToOrderDTO addProductDto) {
        orderService.addProduct(addProductDto.orderId(),
                addProductDto.productId(),
                addProductDto.quantity());
        return "redirect:/orders";
    }

    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        orderService.proveOrder(id, true);
        return "redirect:/orders";
    }

    @GetMapping("/decline/{id}")
    public String decline(@PathVariable Long id) {
        orderService.proveOrder(id, false);
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id,
                           Model model) {
        model.addAttribute("order", orderMapper.toDto(orderService.getById(id)));
        model.addAttribute("userRepository", userRepository);
        model.addAttribute("typeRepository", orderTypeRepository);
        model.addAttribute("statusRepository", statusTypeRepository);
        model.addAttribute("productRepository", productRepository);
        model.addAttribute("orderProductsRepository", orderProductsRepository);
        model.addAttribute("products", productRepository.findAllById(orderProductsRepository.findAllById(orderMapper.toDto(orderService.getById(id)).getOrderProductIds()).stream().map(OrderProducts::getOrder).map(Order::getId).toList()));
        return "/orders/viewOrder";
    }

    @GetMapping("/start/{orderId}")
    public String start(@PathVariable Long orderId,
                        @RequestParam(value = "userId", required = false) Long userId) {
        orderDetailsService.startOrder(userId, orderId);
        return "redirect:/orders";
    }

    @GetMapping("/available/{id}")
    public String getAvailable(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "5") int pageSize,
                               Model model,
                               @PathVariable Long id) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Order> orderPage = orderService.getAvailableOrders(id, pageRequest);
        Page<OrderBodyDTO> orderDtoPage = new PageImpl<>(orderMapper.toDtos(orderPage.getContent()), pageRequest, orderPage.getTotalElements());
        model.addAttribute("orders", orderDtoPage);
        model.addAttribute("userRepository", userRepository);
        model.addAttribute("typeRepository", orderTypeRepository);
        model.addAttribute("statusRepository", statusTypeRepository);
        return "/orders/viewOrders";
    }

}
