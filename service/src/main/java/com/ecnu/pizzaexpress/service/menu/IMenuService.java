package com.ecnu.pizzaexpress.service.menu;

import com.ecnu.pizzaexpress.model.Dishes;
import com.ecnu.pizzaexpress.model.DishesWithCount;
import java.util.List;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
public interface IMenuService {

  void refreshMenu();

  List<Dishes> getMenu();

  int checkInventory(List<DishesWithCount> dishesWithCounts);
}
