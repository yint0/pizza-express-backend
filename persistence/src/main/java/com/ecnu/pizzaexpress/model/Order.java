package com.ecnu.pizzaexpress.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Order {

  private int id;

  private String uuid;

  private Integer userId;

  private int status;

  private BigDecimal costPrice;

  private BigDecimal salePrice;

  private int factoryId;

  private int deliveryClerkId;

  private String remark;

  private Date createTime;

  private String address;

  private Date deliveryTime;

}