package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.Currency;
import com.kbe.gateway.rabbitmq.HardwareSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class HardwareController {

    @Autowired
    HardwareSender hardwareSender;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/hardwarecomponents", method = RequestMethod.GET)
    public ResponseEntity<String> getHardwareComponents(@RequestParam(required = false) String currencyParam) {
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

        Object response = hardwareSender.sendGetHardware(currency);

        if(response != null) {
            return ResponseEntity.ok(response.toString());
        } else {
            return ResponseEntity.ok("error");
        }

    }
/*
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/hardwarecomponents", method = RequestMethod.GET)
    public ResponseEntity<String> getHardwareComponents() {
        return getHardwareComponents("EUR");
    }*/

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        //ResponseEntity<String> response = ResponseEntity.ok("Hello Anonymous");
        //System.out.println("response.toString(): "+response);
        Date date = new Date();
        String dateString = (1900+date.getYear())+"-"+ (date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds()+"  ";
        System.out.println(dateString+"getAnonymous triggered");
        return ResponseEntity.ok("Hello Anonymous");
        //return response;
    }
}
