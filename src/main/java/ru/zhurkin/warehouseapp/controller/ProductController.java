package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.warehouseapp.controller.generic.GenericController;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.service.ProductService;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProductMapper;

@Tag(name = "Products",
        description = "Controller for products")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends GenericController<ProductBodyDTO, Product> {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        super(productService, productMapper);
        this.productService = productService;
        this.productMapper = productMapper;
    }
}
