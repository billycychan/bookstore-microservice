package com.billycychan.bookstore.webapp.web.cotrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
class ProductController {

    @GetMapping
    String index() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    String productPage() {
        return "products";
    }
}