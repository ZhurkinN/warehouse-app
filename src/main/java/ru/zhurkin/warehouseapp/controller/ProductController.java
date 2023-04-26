package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.warehouseapp.controller.generic.GenericController;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.service.ProductService;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.dto.ProviderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProductMapper;
import ru.zhurkin.warehouseapp.support.mapper.ProviderMapper;

import java.util.List;

@Tag(name = "Products",
        description = "Controller for products")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends GenericController<ProductBodyDTO, Product> {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProviderMapper providerMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             ProviderMapper providerMapper) {
        super(productService, productMapper);
        this.productService = productService;
        this.productMapper = productMapper;
        this.providerMapper = providerMapper;
    }

    @GetMapping("/providers/{id}")
    @Operation(method = "getProductsProviders",
            description = "Get all product's providers")
    public ResponseEntity<List<ProviderBodyDTO>> getProductsProviders(@PathVariable Long id) {

        List<Provider> providers = productService.getProvidersById(id);
        List<ProviderBodyDTO> responseDtos = providerMapper.toDtos(providers);
        return ResponseEntity.ok(responseDtos);
    }
}
