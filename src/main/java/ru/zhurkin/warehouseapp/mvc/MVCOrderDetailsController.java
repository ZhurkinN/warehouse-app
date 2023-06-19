package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.service.OrderDetailsService;
import ru.zhurkin.warehouseapp.support.dto.OrderDetailsBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.OrderDetailsMapper;

@Controller
@RequestMapping("/order-details")
@RequiredArgsConstructor
public class MVCOrderDetailsController {

    private final OrderDetailsService orderDetailsService;
    private final OrderDetailsMapper orderDetailsMapper;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<OrderDetails> orderDetailsPage = orderDetailsService.getAll(pageRequest);
        Page<OrderDetailsBodyDTO> orderDetailsDtoPage = new PageImpl<>(orderDetailsMapper.toDtos(orderDetailsPage.getContent()), pageRequest, orderDetailsPage.getTotalElements());
        model.addAttribute("detailedOrders", orderDetailsDtoPage);
        return "/details/viewOrderDetails";
    }
}
