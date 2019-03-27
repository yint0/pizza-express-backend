package com.ecnu.pizzaexpress.model;

import com.ecnu.pizzaexpress.constants.FactoryStatus;
import java.time.LocalTime;
import java.util.Date;
import lombok.Data;

@Data
public class Factory {

  private int id;

  private String name;

  private String headPortrait;

  private String telephone;

  private LocalTime openTime;

  private LocalTime closeTime;

  private int maxQuantity;

  private FactoryStatus status;

  private String address;
}