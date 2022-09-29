package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.Currency;
import com.kbe.gateway.rabbitmq.HardwareSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HardwareController {

    @Autowired
    HardwareSender hardwareSender;
    @Autowired
    ControllerUtil controllerUtil;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/hardwarecomponents", method = RequestMethod.GET)
    public ResponseEntity<String> getHardwareComponents(@RequestParam(required = false) String currencyParam) {
        Currency currency = controllerUtil.getCurrencyFromParaM(currencyParam);
        Object response = hardwareSender.sendGetHardware(currency);
        return controllerUtil.handleResponse(response);
    }
}
