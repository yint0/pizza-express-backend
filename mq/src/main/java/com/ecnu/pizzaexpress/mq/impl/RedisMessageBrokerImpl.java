package com.ecnu.pizzaexpress.mq.impl;

import com.ecnu.pizzaexpress.mq.MessageBroker;
import com.ecnu.pizzaexpress.mq.MessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

public class RedisMessageBrokerImpl implements MessageBroker {

  private final static Logger logger = LoggerFactory.getLogger(RedisMessageBrokerImpl.class);

  private RedisMessageListenerContainer container;

  public RedisMessageBrokerImpl(RedisMessageListenerContainer container) {
    this.container = container;
  }

  @Override
  public void register(String topicPattern, MessageConsumer consumer) {
    logger.info("Class:{} register topic:{}", consumer.getClass().getSimpleName(), topicPattern);
    container.addMessageListener(((message, pattern) ->
            consumer.onReceive(new String(message.getChannel()), message.getBody())),
        new PatternTopic(topicPattern));
  }
}
