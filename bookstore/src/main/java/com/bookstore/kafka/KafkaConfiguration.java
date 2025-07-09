package com.bookstore.kafka;


import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfiguration {

    @KafkaListener(topics = "bill-price-topic", groupId = "group-1")
    public void updateBill(String value) {
        System.out.println("Received: " + value);
    }

}
