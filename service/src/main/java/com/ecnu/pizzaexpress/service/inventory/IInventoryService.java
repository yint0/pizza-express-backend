package com.ecnu.pizzaexpress.service.inventory;

import com.ecnu.pizzaexpress.model.Inventory;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
public interface IInventoryService {

  boolean addInventory(Inventory inventory);

  boolean deductInventory(Inventory inventory);

  boolean updateInventory(Inventory inventory);
}
