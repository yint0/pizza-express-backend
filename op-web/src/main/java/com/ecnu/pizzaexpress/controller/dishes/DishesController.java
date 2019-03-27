package com.ecnu.pizzaexpress.controller.dishes;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.service.dishes.DishesWithContent;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/dishes")
public class DishesController extends BaseController {

  @Autowired
  private IDishesService dishesService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authentication(Role.Admin)
  public DishesWithContent createDishes(@RequestBody DishesWithContent dishesWithContent) {
    dishesService.createDishes(dishesWithContent);
    return dishesWithContent;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @Authentication({Role.Admin, Role.User})
  public DishesWithContent getUserById(@PathVariable("id") int id) {
    DishesWithContent dishesWithContent = dishesService.findByIdWithContent(id);
    if (getToken().isUser()) {
      dishesWithContent.setCostPrice(BigDecimal.ZERO);
    }
    return dishesWithContent;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @Authentication(Role.Admin)
  public DishesWithContent updateDishes(@PathVariable("id") int id,
      @RequestBody DishesWithContent dishesWithContent) {
    dishesWithContent.setId(id);
    dishesService.updateDishes(dishesWithContent);
    return dishesWithContent;
  }

}
