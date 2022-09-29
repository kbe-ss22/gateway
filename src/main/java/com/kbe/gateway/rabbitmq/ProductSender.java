package com.kbe.gateway.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbe.gateway.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Object sendGetProducts(Currency currency){
        var received = rabbitTemplate.convertSendAndReceive(
                RabbitConfig.GETINFORMATIONEXCHANGE,
                RabbitConfig.GETPRODUCTSROUTINGKEY, currency);
        return received;
    }

    public void sendCreateProduct(String name, int[] hardwareIds ) {
        APICrudRequest request = new APICrudRequest(-1,name,hardwareIds);

        String stringRequest = null;
        try {
            stringRequest = new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: "+e.toString());
        }

        System.out.println(stringRequest);

        rabbitTemplate.convertAndSend(
                RabbitConfig.CRUDPRODUCTEXCHANGE,
                RabbitConfig.CREATEPRODUCTROUTINGKEY,
                request);
    }

    public void sendUpdateProduct(int id, String name, int[] hardwareIds) {
        APICrudRequest request = new APICrudRequest(id,name,hardwareIds);
        rabbitTemplate.convertAndSend(
                RabbitConfig.CRUDPRODUCTEXCHANGE,
                RabbitConfig.UPDATEPRODUCTROUTINGKEY,
                request);
    }

    public void sendDeleteProduct(int id) {
        APICrudRequest request = new APICrudRequest(id,"",null);
        rabbitTemplate.convertAndSend(
                RabbitConfig.CRUDPRODUCTEXCHANGE,
                RabbitConfig.DELETEPRODUCTROUTINGKEY,
                request);
    }
}
