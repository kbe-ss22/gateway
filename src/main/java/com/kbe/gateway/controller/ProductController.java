package com.kbe.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbe.gateway.rabbitmq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductSender productSender;
    @Autowired
    ControllerUtil controllerUtil;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<String> getProducts(@RequestParam(required = false) String currencyParam) {
        Currency currency = controllerUtil.getCurrencyFromParaM(currencyParam);
        Object response = productSender.sendGetProducts(currency);
        return controllerUtil.handleResponse(response);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/create", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(@RequestBody String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductInfo prodInfo = objectMapper.readValue(payload, ProductInfo.class);
            productSender.sendCreateProduct(prodInfo.getName(), prodInfo.getHardwareIDs());
            return ResponseEntity.ok("Product has been send to Product Service for Creation.");
        } catch (JsonMappingException e) {
            System.out.println(e.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok("Something went wrong");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/update", method = RequestMethod.PUT)
    public void updateProduct(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int[] hardwareIDs
    ) {
        productSender.sendUpdateProduct(id,name, hardwareIDs);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/delete", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam int id ) {
        productSender.sendDeleteProduct(id);
    }
}
