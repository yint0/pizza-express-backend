package com.ecnu.pizzaexpress.constants;


import com.ecnu.pizzaexpress.global.IntValueEnum;

public enum DishesStatus implements IntValueEnum {
  ONLINE(0, "上线"),
  OFFLINE(1, "停用");


  DishesStatus(int value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public int getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }

  public static DishesStatus valueOf(int value) {
    for (DishesStatus status : DishesStatus.values()) {
      if (status.value == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("invalid status value:" + value);
  }

  private final int value;
  private final String desc;
}
