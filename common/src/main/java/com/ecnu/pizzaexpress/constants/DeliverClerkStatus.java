package com.ecnu.pizzaexpress.constants;


import com.ecnu.pizzaexpress.global.IntValueEnum;

public enum DeliverClerkStatus implements IntValueEnum {
  IDLE(0, "空闲"),
  DELIVERING(1, "配送中"),
  OFF(1, "休息");


  DeliverClerkStatus(int value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public int getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }

  public static DeliverClerkStatus valueOf(int value) {
    for (DeliverClerkStatus status : DeliverClerkStatus.values()) {
      if (status.value == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("invalid status value:" + value);
  }

  private final int value;
  private final String desc;
}
