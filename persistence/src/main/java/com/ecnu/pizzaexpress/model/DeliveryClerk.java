package com.ecnu.pizzaexpress.model;

import lombok.Data;

@Data
public class DeliveryClerk {

  private int id;

  private String name;

  private String telephone;

  private int factoryId;

  private int status;
}