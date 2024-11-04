package com.billycychan.order_service.web.controllers;

import com.billycychan.order_service.domain.OrderService;
import com.billycychan.order_service.domain.SecurityService;
import com.billycychan.order_service.domain.models.CreateOrderRequest;
import com.billycychan.order_service.domain.models.CreateOrderResponse;
import com.billycychan.order_service.domain.models.OrderSummary;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String userName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", userName);
        return orderService.createOrder(userName, request);
    }

    @GetMapping
    List<OrderSummary> getOrders() {
        String userName = securityService.getLoginUserName();
        log.info("Featching orders for user: {}", userName);
        return orderService.findOrders(userName);
    }
}
