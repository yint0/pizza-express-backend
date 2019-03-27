package com.ecnu.pizzaexpress.constants;


import com.ecnu.pizzaexpress.global.IntValueEnum;

public enum OrderStatus implements IntValueEnum {
  NEW(10, "新建"),
  UNPAID(20, "待支付"),
  PAID(30, "已支付"),
  PENDING(40, "制作中"),
  DELIVERING(50, "配送中"),
  FINISH(60, "已完成"),
  CANCEL(90, "已取消")
  ;


  OrderStatus(int value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public int getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }

  public static OrderStatus valueOf(int value) {
    for (OrderStatus status : OrderStatus.values()) {
      if (status.value == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("invalid status value:" + value);
  }

  private final int value;
  private final String desc;
}
