package com.ecnu.pizzaexpress.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DishesWithCount extends Dishes {

  private int count;

}