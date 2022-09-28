package com.kbe.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbe.gateway.rabbitmq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ProductController {

    @Autowired
    ProductSender productSender;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<String> getProducts(@RequestParam(required = false) String currencyParam) {
        Date date = new Date();
        String dateString = (1900+date.getYear())+"-"+ (date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds()+"  ";
        System.out.println(dateString+"getHardwareComponents triggered");
        //String json = "{\"id\":0,\"name\":\"wunder\",\"hardware\":[{\"id\":0,\"type\":\"gpu\",\"name\":\"rx 6800\",\"description\":\"fast\",\"price\":222.42,\"stock\":5}";

        Currency currency;
        if(currencyParam == null) {
            currency = Currency.EUR;
        } else {
            currency =  Currency.valueOf(currencyParam);
        }

        Object response = productSender.sendGetProducts(currency);

        if(response != null) {
            return ResponseEntity.ok(response.toString());
        } else {
            return ResponseEntity.ok("error");
        }
    }
/*
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<String> getProducts() {
        return getProducts("EUR");
    }*/

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/create", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(
            @RequestBody String payload
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProductPostEntity res = objectMapper.readValue(payload, ProductPostEntity.class);
            String name = res.getName();
            int[] hardwareIDs = res.getHardwareIDs();
            productSender.sendCreateProduct(name, hardwareIDs);
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
    public void deleteProduct(
            @RequestParam int id
    ) {
        productSender.sendDeleteProduct(id);
    }


}
