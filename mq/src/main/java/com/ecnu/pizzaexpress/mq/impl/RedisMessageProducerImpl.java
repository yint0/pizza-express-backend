package com.ecnu.pizzaexpress.mq.impl;


import com.ecnu.pizzaexpress.mq.MessageProducer;

import java.io.Serializable;
import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageProducerImpl implements MessageProducer {

  private RedisTemplate<String, Object> redisTemplate;

  public RedisMessageProducerImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public boolean send(String topic, byte[] data) {
    redisTemplate.convertAndSend(topic, data);
    return true;
  }

  @Override
  public boolean send(String topic, Serializable data) {
    redisTemplate.convertAndSend(topic, data);
    return true;
  }

  @Override
  public boolean send(String topic, Map<String, Object> data) {
    redisTemplate.convertAndSend(topic, data);
    return true;
  }
}
