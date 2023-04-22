package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.repository.product.ProviderRepository;
import ru.zhurkin.warehouseapp.service.generic.GenericService;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PRODUCT_NOT_FOUND;
import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PROVIDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProviderService extends GenericService<Provider> {

    private final ProviderRepository providerRepository;
    private final ProductRepository productRepository;


    @Override
    public Provider add(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Provider getById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PROVIDER_NOT_FOUND));
    }

    @Override
    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider update(Provider provider) {
        providerRepository.findById(provider.getId())
                .orElseThrow(() -> new NotFoundException(PROVIDER_NOT_FOUND));
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PROVIDER_NOT_FOUND));
        providerRepository.delete(provider);
    }

    @Transactional
    public Provider addProduct(Long providerId,
                               Long productId) {

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException(PROVIDER_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        provider.getProducts().add(product);
        product.getProviders().add(provider);
        productRepository.save(product);
        return providerRepository.save(provider);
    }
}
