package com.example.product_service.service.serviceImpl;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public Product create(ProductRequest request) {
        Product product = new Product();
        apply(product, request);
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        Product product = findById(id);
        apply(product, request);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    private void apply(Product product, ProductRequest request) {
        product.setName(request.name().trim());
        product.setDescription(request.description().trim());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setCategory(request.category().trim());
    }
}
