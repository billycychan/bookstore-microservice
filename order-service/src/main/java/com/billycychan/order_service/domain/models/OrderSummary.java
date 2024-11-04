package com.billycychan.order_service.domain.models;

public record OrderSummary(
        String orderNumber,
        OrderStatus orderStatus
) {}
