package com.ecnu.pizzaexpress.model;

import com.ecnu.pizzaexpress.constants.DeliverClerkStatus;
import lombok.Data;

@Data
public class DeliverClerk {

  private int id;

  private String name;

  private String telephone;

  private int factoryId;

  private DeliverClerkStatus status;
}