package com.lezg.kafka.demo.services;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author shanqiu
 * Created on 2018/3/28
 */
public class KafkaProducerListener implements ProducerListener {

    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        System.out.println("kafka发送消息数据成功");
        System.out.println("topic:" + topic);
        System.out.println("partition:" + partition);
        System.out.println("key:" + key);
        System.out.println("value:" + value);
        System.out.println("recordMetadata:" + recordMetadata);
    }

    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        System.out.println("kafka发送消息数据失败");
        exception.printStackTrace();
    }

    public boolean isInterestedInSuccess() {
        System.out.println("kafka监听器启动");
        return true;
    }
}
