package com.ecnu.pizzaexpress.service.dishes;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IDishesService {

  int createDishes(String dishes, double price, List materialList, String dishesSta,
      double cookedTime);

  int modifyDishesInfo(String dishes, double price, List materialList, String dishesSta,
      double cookedTime);

  int getDishesInfo(String dishes);

  int modifyDishesSta(String dishes, String dishesSta);

  int dishesCookedTime(String dishes);
}
