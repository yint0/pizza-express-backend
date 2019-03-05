package com.ecnu.pizzaexpress.model;

public class DishesContent {

  private Integer dishesId;

  private Integer materialId;

  private Double useCount;

  public Integer getDishesId() {
    return dishesId;
  }

  public void setDishesId(Integer dishesId) {
    this.dishesId = dishesId;
  }

  public Integer getMaterialId() {
    return materialId;
  }

  public void setMaterialId(Integer materialId) {
    this.materialId = materialId;
  }

  public Double getUseCount() {
    return useCount;
  }

  public void setUseCount(Double useCount) {
    this.useCount = useCount;
  }
}