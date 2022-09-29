package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.Currency;
import org.springframework.http.ResponseEntity;

public interface ControllerUtil {
    Currency getCurrencyFromParaM(String currencyParam);
    ResponseEntity<String> handleResponse(Object object);
}
