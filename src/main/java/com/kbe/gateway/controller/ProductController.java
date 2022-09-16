package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.RabbitmqSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    String getAllProducts() {
        System.out.println("products requested");
        new RabbitmqSender().send("products");
        return "all";
    }
}
