package com.kbe.gateway.rabbitmq;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"rabbitmq","work-queues"})
@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public RabbitmqReceiver receiver1() {
            return new RabbitmqReceiver(1);
        }

        @Bean
        public RabbitmqReceiver receiver2() {
            return new RabbitmqReceiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public RabbitmqSender sender() {
        return new RabbitmqSender();
    }
}
