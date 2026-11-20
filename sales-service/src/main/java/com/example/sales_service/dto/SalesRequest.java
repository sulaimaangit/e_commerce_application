package com.example.sales_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record SalesRequest(@NotNull @Positive Long customerId, @NotEmpty List<@Valid Item> items) {
    public record Item(@NotNull @Positive Long productId,
                       @NotNull @Size(min = 1, max = 150) String productName,
                       @NotNull @Positive Integer quantity,
                       @NotNull @Positive BigDecimal unitPrice) {}
}
