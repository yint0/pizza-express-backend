package com.ecnu.pizzaexpress.service.dishes.Impl;

import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DishesServiceImpl implements IDishesService {


  @Override
  public int createDishes(String dishes, double price, List materialList, String dishesSta,
      double cookedTime) {
    return 0;
  }

  @Override
  public int modifyDishesInfo(String dishes, double price, List materialList, String dishesSta,
      double cookedTime) {
    return 0;
  }

  @Override
  public int getDishesInfo(String dishes) {
    return 0;
  }

  @Override
  public int modifyDishesSta(String dishes, String dishesSta) {
    return 0;
  }

  @Override
  public int dishesCookedTime(String dishes) {
    return 0;
  }
}
