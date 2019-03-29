package com.ecnu.pizzaexpress.mq;

import java.io.Serializable;
import java.util.Map;

public interface MessageProducer {

  boolean send(String topic, byte[] data);

  boolean send(String topic, Serializable data);

  boolean send(String topic, Map<String, Object> data);

}
