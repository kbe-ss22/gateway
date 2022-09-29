package com.kbe.gateway.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String GETINFORMATIONEXCHANGE = "get-information-exchange";
    public static final String CRUDPRODUCTEXCHANGE = "crud-product-exchange";

    public static final String GETHARDWAREQUEUE = "get-hardware-queue";
    public static final String GETPRODUCTSQUEUE = "get-products-queue";
    public static final String CREATEPRODUCTQUEUE = "create-product-queue";
    public static final String UPDATEPRODUCTQUEUE = "update-product-queue";
    public static final String DELETEPRODUCTQUEUE = "delete-product-queue";

    public static final String GETHARDWAREROUTINGKEY = "get.hardware";
    public static final String GETPRODUCTSROUTINGKEY = "get.products";
    public static final String CREATEPRODUCTROUTINGKEY = "product.create";
    public static final String UPDATEPRODUCTROUTINGKEY = "product.update";
    public static final String DELETEPRODUCTROUTINGKEY = "product.delete";

    @Bean
    public TopicExchange getInformationExchange() {
        return new TopicExchange(GETINFORMATIONEXCHANGE);
    }
    @Bean
    public TopicExchange crudProductExchange() {
        return new TopicExchange(CRUDPRODUCTEXCHANGE);
    }

    @Bean
    public Queue getHardwareQueue() {
        return new Queue(GETHARDWAREQUEUE, false);
    }
    @Bean
    public Queue getProductsQueue() {
        return new Queue(GETPRODUCTSQUEUE, false);
    }
    @Bean
    public Queue createProductQueue() {
        return new Queue(CREATEPRODUCTQUEUE, false);
    }
    @Bean
    public Queue updateProductQueue() {
        return new Queue(UPDATEPRODUCTQUEUE, false);
    }
    @Bean
    public Queue deleteProductQueue() {
        return new Queue(DELETEPRODUCTQUEUE, false);
    }

    @Bean
    public Binding hardwareBinding() {
        return BindingBuilder.bind(getHardwareQueue()).to(getInformationExchange()).with(GETHARDWAREROUTINGKEY);
    }

    @Bean
    public Binding productsBinding() {
        return BindingBuilder.bind(getProductsQueue()).to(getInformationExchange()).with(GETPRODUCTSROUTINGKEY);
    }

    @Bean
    public Binding createBinding() {
        return BindingBuilder.bind(createProductQueue()).to(crudProductExchange()).with(CREATEPRODUCTROUTINGKEY);
    }

    @Bean
    public Binding updateBinding() {
        return BindingBuilder.bind(updateProductQueue()).to(crudProductExchange()).with(UPDATEPRODUCTROUTINGKEY);
    }

    @Bean
    public Binding deleteBinding() {
        return BindingBuilder.bind(deleteProductQueue()).to(crudProductExchange()).with(DELETEPRODUCTROUTINGKEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter()); //template.setMessageConverter(messageConverter());
        return template;
    }
}
