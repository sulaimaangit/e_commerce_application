package com.example.sales_service.service.salesserviceimpl;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.sales_service.dto.SalesRequest;
import com.example.sales_service.entity.Sales;
import com.example.sales_service.entity.SalesItem;
import com.example.sales_service.repository.SalesRepository;
import com.example.sales_service.service.SalesService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SalesServiceImpl implements SalesService {
    private final SalesRepository repository;
    @Autowired
    public SalesServiceImpl(SalesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Sales> findAll() {
        return repository.findAll();
    }

    @Override
    public Sales findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found"));
    }

    @Override
    @Transactional
    public Sales create(SalesRequest request) {
        Sales sale = new Sales();
        sale.setCustomerId(request.customerId());
        sale.setStatus("COMPLETED");
        BigDecimal total = BigDecimal.ZERO;

        for (SalesRequest.Item input : request.items()) {
            SalesItem item = new SalesItem();
            item.setProductId(input.productId());
            item.setProductName(input.productName());
            item.setQuantity(input.quantity());
            item.setUnitPrice(input.unitPrice());
            sale.addItem(item);
            total = total.add(input.unitPrice().multiply(BigDecimal.valueOf(input.quantity())));
        }

        sale.setTotalAmount(total);
        return repository.save(sale);
    }
}
