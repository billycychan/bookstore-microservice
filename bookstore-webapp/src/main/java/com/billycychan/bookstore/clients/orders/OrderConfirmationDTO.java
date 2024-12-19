package com.billycychan.bookstore.clients.orders;

public record OrderConfirmationDTO(String orderNumber, OrderStatus status) {}
