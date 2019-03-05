package com.ecnu.pizzaexpress.model;

import java.util.Date;
import lombok.Data;

@Data
public class Factory {

  private int id;

  private String name;

  private String headPortrait;

  private String telephone;

  private Date openTime;

  private Date closeTime;

  private int maxQuantity;

  private int status;

  private String address;
}