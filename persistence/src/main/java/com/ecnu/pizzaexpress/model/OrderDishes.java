package com.ecnu.pizzaexpress.model;

import lombok.Data;

@Data
public class OrderDishes {

  private int orderId;

  private int dishesId;

  private int count;
}