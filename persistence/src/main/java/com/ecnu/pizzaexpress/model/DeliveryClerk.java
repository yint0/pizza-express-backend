package com.ecnu.pizzaexpress.model;

public class DeliveryClerk {

  private Integer id;

  private String name;

  private String telephone;

  private Integer factoryId;

  private Byte status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  public Integer getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(Integer factoryId) {
    this.factoryId = factoryId;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }
}