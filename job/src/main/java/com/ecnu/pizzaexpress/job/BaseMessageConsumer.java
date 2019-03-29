package com.ecnu.pizzaexpress.job;

import com.ecnu.pizzaexpress.mq.MessageBroker;
import com.ecnu.pizzaexpress.mq.MessageConsumer;
import com.ecnu.pizzaexpress.service.base.TokenService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * Created by yerunjie on 2018/11/22
 *
 * @author yerunjie
 */
public abstract class BaseMessageConsumer<T> implements MessageConsumer {

  private static final Logger logger = LoggerFactory.getLogger(BaseMessageConsumer.class);

  @Autowired
  protected GenericJackson2JsonRedisSerializer redisSerializer;

  @Autowired
  protected MessageBroker messageBroker;

  @Autowired
  private TokenService tokenService;

  @PostConstruct
  public void init() {
    List<String> topics = getTopics();
    for (String s : topics) {
      messageBroker.register(s, this);
    }
  }

  protected abstract List<String> getTopics();

  @Override
  public void onReceive(String topic, byte[] rawMessage) {
    try {
      T data = (T) redisSerializer.deserialize(rawMessage);
      logger.info("class: {} receive topic: {} with data: {}", getClass().getSimpleName(), topic,
          data.toString());
      //tokenService.setToken(Token.getSystemUser());
      onReceive(topic, data);
    } catch (Exception e) {
      logger.error(
          "class: " + getClass().getSimpleName() + " receive topic: " + topic + " with error", e);
    }
  }


  public abstract void onReceive(String topic, T data);
}
