package com.lezg.kafka.demo.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author shanqiu
 * Created on 2018/3/29
 */
public class KafkaConsumerService implements MessageListener<String, String> {

    public void onMessage(ConsumerRecord<String, String> data) {
        System.out.println("consumer:"+data.toString());
    }
}
