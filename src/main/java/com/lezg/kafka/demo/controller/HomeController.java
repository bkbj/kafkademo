package com.lezg.kafka.demo.controller;

import com.lezg.kafka.demo.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shanqiu
 */
@RestController
public class HomeController {


    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/api/hello")
    public String getHello() {
        String topic = "shanqiu";
        String value = "Hello Kafka";
        String ifPartition = "1";
        String role = "test";
        kafkaProducerService.sndMesForTemplate(topic, value, ifPartition, 3, role);
        return "Hello World";
    }
}
