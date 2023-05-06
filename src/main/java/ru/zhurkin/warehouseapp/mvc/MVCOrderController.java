package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.repository.order.OrderTypeRepository;
import ru.zhurkin.warehouseapp.repository.order.StatusTypeRepository;
import ru.zhurkin.warehouseapp.repository.user.UserRepository;
import ru.zhurkin.warehouseapp.service.OrderService;
import ru.zhurkin.warehouseapp.support.dto.OrderBodyDTO;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderMapper;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class MVCOrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final OrderTypeRepository orderTypeRepository;
    private final StatusTypeRepository statusTypeRepository;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "createdWhen"));
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
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "createdWhen"));
        Page<Order> orderPage = orderService.getAssistantOrManagerOrders(id, pageRequest);
        Page<OrderBodyDTO> orderDtoPage = new PageImpl<>(orderMapper.toDtos(orderPage.getContent()), pageRequest, orderPage.getTotalElements());
        model.addAttribute("orders", orderDtoPage);
        model.addAttribute("userRepository", userRepository);
        model.addAttribute("typeRepository", orderTypeRepository);
        model.addAttribute("statusRepository", statusTypeRepository);
        return "/orders/viewOrders";
    }
}
