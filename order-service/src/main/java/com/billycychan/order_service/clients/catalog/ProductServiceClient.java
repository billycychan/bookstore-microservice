package com.billycychan.order_service.clients.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Slf4j
@Component
public class ProductServiceClient {
    private final RestClient restClient;

    ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);
        try {
            var product =
                    restClient
                            .get()
                            .uri("/api/products/{code}", code)
                            .retrieve()
                            .body(Product.class);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            log.error("Failed to fetch product for code: {}", code, e);
            return Optional.empty();
        }
    }
}