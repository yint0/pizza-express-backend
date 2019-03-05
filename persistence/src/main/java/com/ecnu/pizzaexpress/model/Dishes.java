package com.ecnu.pizzaexpress.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Dishes {

  private int id;

  private String name;

  private String description;

  private BigDecimal costPrice;

  private BigDecimal salePrice;

  private int status;

}