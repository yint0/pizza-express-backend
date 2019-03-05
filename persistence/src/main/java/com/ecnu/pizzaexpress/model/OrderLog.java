package com.ecnu.pizzaexpress.model;

import java.util.Date;
import lombok.Data;

@Data
public class OrderLog {

  private int id;

  private int orderId;

  private String operatorType;

  private int operatorId;

  private String contentFrom;

  private String contentTo;

  private Date operateTime;

}