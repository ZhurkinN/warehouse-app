package ru.zhurkin.warehouseapp.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zhurkin.warehouseapp.service.OrderDetailsService;

@Controller
@RequestMapping("/order-details")
@RequiredArgsConstructor
public class MVCOrderDetailsController {

    private final OrderDetailsService orderDetailsService;
}
