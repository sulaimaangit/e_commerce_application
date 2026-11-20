package com.example.sales_service.service;

import com.example.sales_service.dto.SalesRequest;
import com.example.sales_service.entity.Sales;
import java.util.List;

public interface SalesService {
    List<Sales> findAll();
    Sales findById(Long id);
    Sales create(SalesRequest request);
}
