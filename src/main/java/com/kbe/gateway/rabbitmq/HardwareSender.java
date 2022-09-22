package com.kbe.gateway.rabbitmq;

import com.kbe.gateway.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class HardwareSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Object sendGetHardware(Currency currency){
        return rabbitTemplate.convertSendAndReceive(
                RabbitConfig.GETINFORMATIONEXCHANGE,
                RabbitConfig.GETHARDWAREROUTINGKEY, currency);
    }
}
