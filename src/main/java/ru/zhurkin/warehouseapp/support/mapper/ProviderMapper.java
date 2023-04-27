package ru.zhurkin.warehouseapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.support.dto.ProviderBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.generic.GenericMapper;

import java.util.ArrayList;
import java.util.Objects;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PROVIDER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ProviderMapper extends GenericMapper<Provider, ProviderBodyDTO> {

    private final ProductRepository productRepository;

    @Override
    public Provider toEntity(ProviderBodyDTO dto) {
        Provider provider = new Provider()
                .setAddress(dto.getAddress())
                .setEmail(dto.getEmail())
                .setName(dto.getName())
                .setTelephoneNumber(dto.getTelephoneNumber());
        provider.setProducts(new ArrayList<>());
        if (!Objects.isNull(dto.getProductIds())) {
            provider.setProducts(dto.getProductIds()
                    .stream()
                    .map(productRepository::findById)
                    .map(e -> e.orElseThrow(() -> new NotFoundException(PROVIDER_NOT_FOUND)))
                    .toList());
        }

        return setGenericFields(dto, provider);
    }

    @Override
    public ProviderBodyDTO toDto(Provider provider) {
        return new ProviderBodyDTO(provider.getId(),
                provider.getCreatedBy(),
                provider.getCreatedWhen(),
                provider.getIsDeleted(),
                provider.getDeletedBy(),
                provider.getDeletedWhen(),
                provider.getName(),
                provider.getAddress(),
                provider.getTelephoneNumber(),
                provider.getEmail(),
                provider.getProducts()
                        .stream()
                        .map(Product::getId)
                        .toList());
    }
}
