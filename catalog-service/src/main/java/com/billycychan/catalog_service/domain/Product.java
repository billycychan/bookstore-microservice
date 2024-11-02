package com.billycychan.catalog_service.domain;

public record Product(
        String code,
        String name,
        String description,
        String imageUrl,
        java.math.@jakarta.validation.constraints.NotNull(message = "Product price is required") @jakarta.validation.constraints.DecimalMin("0.1") BigDecimal
                price) {}
