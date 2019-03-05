package com.ecnu.pizzaexpress.model;

public class OrderDishes {

  private Integer orderId;

  private Integer dishesId;

  private Integer count;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getDishesId() {
    return dishesId;
  }

  public void setDishesId(Integer dishesId) {
    this.dishesId = dishesId;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}