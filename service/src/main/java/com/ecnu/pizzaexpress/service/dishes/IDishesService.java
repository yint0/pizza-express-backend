package com.ecnu.pizzaexpress.service.dishes;

import com.ecnu.pizzaexpress.model.Dishes;
import java.util.List;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
public interface IDishesService {

  DishesWithContent createDishes(DishesWithContent dishesWithContent);

  DishesWithContent findByIdWithContent(int id);

  boolean updateDishes(DishesWithContent dishesWithContent);

  List<Dishes> findByIds(List<Integer> ids);
}
