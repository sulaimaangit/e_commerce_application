package com.example.sales_service.repository;

import com.example.sales_service.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {}
