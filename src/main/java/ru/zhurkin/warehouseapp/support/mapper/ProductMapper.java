package ru.zhurkin.warehouseapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.repository.order.OrderProductsRepository;
import ru.zhurkin.warehouseapp.repository.product.ProviderRepository;
import ru.zhurkin.warehouseapp.support.dto.ProductBodyDTO;
import ru.zhurkin.warehouseapp.support.mapper.generic.GenericMapper;

import java.util.ArrayList;
import java.util.Objects;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.ORDER_PRODUCTS_NOT_FOUND;
import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PROVIDER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ProductMapper extends GenericMapper<Product, ProductBodyDTO> {

    private final ProviderRepository providerRepository;
    private final OrderProductsRepository orderProductsRepository;

    @Override
    public Product toEntity(ProductBodyDTO dto) {
        Product product = new Product()
                .setTitle(dto.getTitle())
                .setCategory(dto.getCategory())
                .setDescription(dto.getDescription())
                .setQuantityLeft(dto.getQuantityLeft())
                .setMeasureUnit(dto.getMeasureUnit())
                .setPrice(dto.getPrice())
                .setWarehousePosition(dto.getWarehousePosition());
        product.setProviders(new ArrayList<>());
        if (!Objects.isNull(dto.getProviderIds())) {
            product.setProviders(dto.getProviderIds()
                    .stream()
                    .map(providerRepository::findById)
                    .map(e -> e.orElseThrow(() -> new NotFoundException(PROVIDER_NOT_FOUND)))
                    .toList());
        }
        product.setOrderProducts(new ArrayList<>());
        if (!Objects.isNull(dto.getOrderProductsIds())) {
            product.setOrderProducts(dto.getOrderProductsIds()
                    .stream()
                    .map(orderProductsRepository::findById)
                    .map(e -> e.orElseThrow(() -> new NotFoundException(ORDER_PRODUCTS_NOT_FOUND)))
                    .toList());
        }

        return setGenericFields(dto, product);
    }

    @Override
    public ProductBodyDTO toDto(Product product) {
        return new ProductBodyDTO(product.getId(),
                product.getCreatedBy(),
                product.getCreatedWhen(),
                product.getIsDeleted(),
                product.getDeletedBy(),
                product.getDeletedWhen(),
                product.getTitle(),
                product.getCategory(),
                product.getDescription(),
                product.getQuantityLeft(),
                product.getMeasureUnit(),
                product.getPrice(),
                product.getWarehousePosition(),
                product.getProviders()
                        .stream()
                        .map(Provider::getId)
                        .toList(),
                product.getOrderProducts()
                        .stream()
                        .map(OrderProducts::getId)
                        .toList());
    }
}
