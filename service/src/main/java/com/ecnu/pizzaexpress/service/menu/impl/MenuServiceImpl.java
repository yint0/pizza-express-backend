package com.ecnu.pizzaexpress.service.menu.impl;

import com.ecnu.pizzaexpress.model.Dishes;
import com.ecnu.pizzaexpress.model.DishesWithCount;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.inventory.IInventoryService;
import com.ecnu.pizzaexpress.service.menu.IMenuService;
import com.ecnu.pizzaexpress.service.user.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService {

  @Autowired
  private IDishesService dishesService;

  @Autowired
  private IInventoryService inventoryService;

  @Autowired
  private IUserService userService;

  @Autowired
  private IDeliverService deliverService;

  @Override
  public void refreshMenu() {

  }

  @Override
  public List<Dishes> getMenu() {

    return dishesService.findByRequest(null);
  }

  @Override
  public int checkInventory(List<DishesWithCount> dishesWithCounts) {
    return 0;
  }
}
