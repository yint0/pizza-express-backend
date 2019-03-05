package com.ecnu.pizzaexpress.model;

import java.util.Date;

public class OrderLog {

  private Integer id;

  private Integer orderId;

  private String operatorType;

  private Integer operatorId;

  private String contentFrom;

  private String contentTo;

  private Date operateTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public String getOperatorType() {
    return operatorType;
  }

  public void setOperatorType(String operatorType) {
    this.operatorType = operatorType == null ? null : operatorType.trim();
  }

  public Integer getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Integer operatorId) {
    this.operatorId = operatorId;
  }

  public String getContentFrom() {
    return contentFrom;
  }

  public void setContentFrom(String contentFrom) {
    this.contentFrom = contentFrom == null ? null : contentFrom.trim();
  }

  public String getContentTo() {
    return contentTo;
  }

  public void setContentTo(String contentTo) {
    this.contentTo = contentTo == null ? null : contentTo.trim();
  }

  public Date getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(Date operateTime) {
    this.operateTime = operateTime;
  }
}