package com.ecnu.pizzaexpress.mq.configuration;

import com.ecnu.pizzaexpress.mq.MessageBroker;
import com.ecnu.pizzaexpress.mq.MessageProducer;
import com.ecnu.pizzaexpress.mq.impl.RedisMessageBrokerImpl;
import com.ecnu.pizzaexpress.mq.impl.RedisMessageProducerImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class MQConfig {

  @Bean
  GenericJackson2JsonRedisSerializer serializer() {
    GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new GenericJackson2JsonRedisSerializer();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    return jackson2JsonRedisSerializer;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(serializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public MessageProducer messageProducer(RedisTemplate<String, Object> redisTemplate) {
    return new RedisMessageProducerImpl(redisTemplate);
  }

  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    return container;
  }

  @Bean
  MessageBroker messageBroker(RedisMessageListenerContainer container) {
    return new RedisMessageBrokerImpl(container);
  }
}
