package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.service.generic.GenericService;
import ru.zhurkin.warehouseapp.support.exception.IllegalRequestParameterException;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PRODUCT_NOT_FOUND;
import static ru.zhurkin.warehouseapp.support.helper.RequestParametersValidator.validateProduct;

@Service
@RequiredArgsConstructor
public class ProductService extends GenericService<Product> {

    private final ProductRepository productRepository;

    @Override
    public Product add(Product product) {
        if (!validateProduct(product.getPrice(), product.getQuantityLeft())) {
            throw new IllegalRequestParameterException();
        }
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }
}