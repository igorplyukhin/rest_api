package com.igor.rest_api.services;

import com.igor.rest_api.models.Product;
import com.igor.rest_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final int MAX_RECORDS_TO_SEND = 1000;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Long createProduct(String sku, String name, String type, Double price) {
        var product = new Product(sku, name, type, price);
        productRepository.save(product);
        return product.getId();
    }

    @Transactional
    public void deleteProductByIdOrSku(String idOrSku) {
        try {
            var id = Long.parseLong(idOrSku);
            productRepository.deleteById(id);
        } catch (NumberFormatException e) {
            var count = productRepository.deleteBySku(idOrSku);
            if (count == 0)
                throw new EmptyResultDataAccessException(1);
        }
    }

    @Transactional
    public Product getProductByIdOrSku(String idOrSku) {
        Product product;
        try {
            var id = Long.parseLong(idOrSku);
            product = productRepository.getById(id);
        } catch (NumberFormatException e) {
            product = productRepository.getBySku(idOrSku);
        }
        if (product == null)
            throw new EmptyResultDataAccessException(1);
        return product;
    }

    @Transactional
    public void updateProductByIdOrSku(String idOrSku, String sku, String name, String type, Double price) {
        var product = getProductByIdOrSku(idOrSku);
        product.setSku(sku);
        product.setName(name);
        product.setType(type);
        product.setPrice(price);
        productRepository.save(product);
    }

    public List<Product> getAllProducts(int offset, int limit) {
        if (limit > MAX_RECORDS_TO_SEND)
            throw new IllegalArgumentException();
        return productRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }
}
