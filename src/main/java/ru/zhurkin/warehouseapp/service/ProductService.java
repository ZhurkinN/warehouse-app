package ru.zhurkin.warehouseapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.zhurkin.warehouseapp.model.order.Order;
import ru.zhurkin.warehouseapp.model.order.OrderDetails;
import ru.zhurkin.warehouseapp.model.order.OrderProducts;
import ru.zhurkin.warehouseapp.model.product.Product;
import ru.zhurkin.warehouseapp.model.product.Provider;
import ru.zhurkin.warehouseapp.repository.order.OrderDetailsRepository;
import ru.zhurkin.warehouseapp.repository.product.ProductRepository;
import ru.zhurkin.warehouseapp.service.generic.GenericService;
import ru.zhurkin.warehouseapp.support.exception.IllegalRequestParameterException;

import java.util.List;

import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.ORDER_DETAILS_NOT_FOUND;
import static ru.zhurkin.warehouseapp.support.constant.ResponseMessagesKeeper.PRODUCT_NOT_FOUND;
import static ru.zhurkin.warehouseapp.support.helper.RequestParametersValidator.validateProduct;

@Service
@RequiredArgsConstructor
public class ProductService extends GenericService<Product> {

    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;

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

    public void setQuantityChanges(Long orderDetailsId) {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new NotFoundException(ORDER_DETAILS_NOT_FOUND));
        Order order = orderDetails.getOrder();
        List<OrderProducts> orderProducts = order.getOrderProducts();
        for (OrderProducts orderProduct : orderProducts) {
            Product product = orderProduct.getProduct();
            Integer quantity = product.getQuantityLeft();
            quantity -= orderProduct.getQuantity();
            product.setQuantityLeft(quantity);
            productRepository.save(product);
        }
    }

    public List<Provider> getProvidersById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
        return product.getProviders();
    }
}
