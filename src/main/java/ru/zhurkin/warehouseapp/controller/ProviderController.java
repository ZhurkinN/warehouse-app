package ru.zhurkin.warehouseapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhurkin.warehouseapp.controller.generic.GenericController;
import ru.zhurkin.warehouseapp.controller.model.AddProductToProviderDTO;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.service.ProviderService;
import ru.zhurkin.warehouseapp.support.dto.ProviderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.ProviderMapper;

@Tag(name = "Providers",
        description = "Controller for providers")
@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController extends GenericController<ProviderBodyDTO, Provider> {

    private final ProviderService providerService;
    private final ProviderMapper providerMapper;

    public ProviderController(ProviderService providerService,
                              ProviderMapper providerMapper) {
        super(providerService, providerMapper);
        this.providerService = providerService;
        this.providerMapper = providerMapper;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProviderBodyDTO> addProvidersProduct(
            @RequestBody AddProductToProviderDTO requestDto) {

        Provider provider = providerService.addProduct(requestDto.providerId(), requestDto.productId());
        ProviderBodyDTO responseDto = providerMapper.toDto(provider);
        return ResponseEntity.ok(responseDto);
    }
}
