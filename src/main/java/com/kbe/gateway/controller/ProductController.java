package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.RabbitmqSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hardwarecomponents")
    public ResponseEntity<String> getAllProducts() {
        System.out.println("products requested");
        //new RabbitmqSender().send("products");
        String json = "{\"id\":0,\"name\":\"wunder\",\"hardware\":[{\"id\":0,\"type\":\"gpu\",\"name\":\"rx 6800\",\"description\":\"fast\",\"price\":222.42,\"stock\":5}";
        return ResponseEntity.ok(json);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        //ResponseEntity<String> response = ResponseEntity.ok("Hello Anonymous");
        //System.out.println("response.toString(): "+response);
        System.out.println("getAnonymous triggered");
        return ResponseEntity.ok("Hello Anonymous");
        //return response;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @RequestMapping(value = "/all-user", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok("Hello All User");
    }
}
