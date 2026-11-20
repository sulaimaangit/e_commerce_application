package com.example.sales_service.controller;

import com.example.sales_service.dto.SalesRequest;
import com.example.sales_service.entity.Sales;
import com.example.sales_service.service.SalesService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public List<Sales> findAll() {
        return salesService.findAll();
    }

    @GetMapping("/{id}")
    public Sales findById(@PathVariable Long id) {
        return salesService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sales create(@Valid @RequestBody SalesRequest request) {
        return salesService.create(request);
    }
}
