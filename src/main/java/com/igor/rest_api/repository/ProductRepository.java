package com.igor.rest_api.repository;

import com.igor.rest_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    long deleteBySku(String SKU);
    Product getById(Long id);
    Product getBySku(String sku);
}
