package com.lezg.kafka.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author shanqiu
 * Created on 2018/3/29
 */
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafka发送消息模板
     *
     * @param topic        主题
     * @param value        messageValue
     * @param ifPartition  是否使用分区 0是\1不是
     * @param partitionNum 分区数 如果是否使用分区为0,分区数必须大于0
     * @param role         角色:bbc app erp...
     */
    public Map<String, Object> sndMesForTemplate(String topic, String value, String ifPartition,
                                                 Integer partitionNum, String role) {
        String key = role + "-" + value.hashCode();
        String valueString = value;
        if (ifPartition.equals("0")) {
            //表示使用分区
            int partitionIndex = getPartitionIndex(key, partitionNum);
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, partitionIndex, key, valueString);
            Map<String, Object> res = checkProRecord(result);
            return res;
        } else {
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, valueString);
            Map<String, Object> res = checkProRecord(result);
            return res;
        }
    }

    /**
     * 根据key值获取分区索引
     *
     * @param key
     * @param partitionNum
     * @return
     */
    private int getPartitionIndex(String key, int partitionNum) {
        if (key == null) {
            Random random = new Random();
            return random.nextInt(partitionNum);
        } else {
            int result = Math.abs(key.hashCode()) % partitionNum;
            return result;
        }
    }

    /**
     * 检查发送返回结果record
     *
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Map<String, Object> checkProRecord(ListenableFuture<SendResult<String, String>> res) {
        Map<String, Object> m = new HashMap<String, Object>();
        return m;
    }
}
