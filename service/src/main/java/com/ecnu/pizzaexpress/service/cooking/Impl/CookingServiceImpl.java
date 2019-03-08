package com.ecnu.pizzaexpress.service.cooking.Impl;

import com.ecnu.pizzaexpress.service.cooking.ICookingService;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CookingServiceImpl implements ICookingService {

  @Autowired
  private IDishesService iDishesService;

  @Override
  public int calculateCookingTime(List dishesList) {
    return 0;
  }
}
