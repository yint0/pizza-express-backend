package com.ecnu.pizzaexpress.mq;

public interface MessageBroker {

  void register(String topicPattern, MessageConsumer consumer);

}
