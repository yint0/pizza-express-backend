package com.ecnu.pizzaexpress.model;

import com.ecnu.pizzaexpress.constants.OrderStatus;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Order {

  private int id;

  private String uuid;

  private int userId;

  private OrderStatus status;

  private BigDecimal costPrice;

  private BigDecimal salePrice;

  private int factoryId;

  private int deliverClerkId;

  private String remark;

  private Date createTime;

  private String address;

  private Date deliverTime;

}