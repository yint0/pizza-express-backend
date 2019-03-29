package com.ecnu.pizzaexpress.mq;

public interface MessageConsumer {

  void onReceive(String topic, byte[] rawMessage);

}
