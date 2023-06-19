package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zhurkin.warehouseapp.controller.generic.GenericController;
import ru.zhurkin.warehouseapp.controller.model.AddProductToProviderDTO;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.service.ProviderService;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.dto.ProviderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProductMapper;
import ru.zhurkin.warehouseapp.support.mapper.ProviderMapper;

import java.util.List;

@Tag(name = "Providers",
        description = "Controller for providers")
@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController extends GenericController<ProviderBodyDTO, Provider> {

    private final ProviderService providerService;
    private final ProviderMapper providerMapper;
    private final ProductMapper productMapper;

    public ProviderController(ProviderService providerService,
                              ProviderMapper providerMapper,
                              ProductMapper productMapper) {
        super(providerService, providerMapper);
        this.providerService = providerService;
        this.providerMapper = providerMapper;
        this.productMapper = productMapper;
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProviderBodyDTO> addProvidersProduct(
            @RequestBody AddProductToProviderDTO requestDto) {

        Provider provider = providerService.addProduct(requestDto.providerId(), requestDto.productId());
        ProviderBodyDTO responseDto = providerMapper.toDto(provider);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/products/{id}")
    @Operation(method = "getProvidersProducts",
            description = "Get all provider's products")
    public ResponseEntity<List<ProductBodyDTO>> getProvidersProducts(@PathVariable Long id) {

        List<Product> products = providerService.getProductsById(id);
        List<ProductBodyDTO> responseDtos = productMapper.toDtos(products);
        return ResponseEntity.ok(responseDtos);
    }
}
