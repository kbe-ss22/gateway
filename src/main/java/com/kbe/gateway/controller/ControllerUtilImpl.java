package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.Currency;
import org.springframework.http.ResponseEntity;

public class ControllerUtilImpl implements ControllerUtil {

    @Override
    public Currency getCurrencyFromParaM(String currencyParam) {
        Currency currency = Currency.EUR;
        if(currencyParam != null) {
            currency = Currency.valueOf(currencyParam.toUpperCase());
        }
        return currency;
    }

    @Override
    public ResponseEntity<String> handleResponse(Object response) {
        if(response != null) {
            return ResponseEntity.ok(response.toString());
        }
        return ResponseEntity.ok("error");
    }
}
