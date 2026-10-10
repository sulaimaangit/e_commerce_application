package com.example.product_service.service;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);
}
