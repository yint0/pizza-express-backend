package com.ecnu.pizzaexpress.model;

import lombok.Data;

@Data
public class Inventory {

  private int materialId;

  private int factoryId;

  private double count;

}