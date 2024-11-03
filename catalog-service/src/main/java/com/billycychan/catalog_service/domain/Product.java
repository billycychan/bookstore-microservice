package com.billycychan.catalog_service.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record Product(
        String code,
        String name,
        String description,
        String imageUrl,
        @NotNull(message = "Product price is required") @DecimalMin("0.1") BigDecimal price) {}
