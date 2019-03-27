package com.ecnu.pizzaexpress.controller.inventory;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.exception.ResourceNotFoundException;
import com.ecnu.pizzaexpress.model.Factory;
import com.ecnu.pizzaexpress.model.Inventory;
import com.ecnu.pizzaexpress.model.Material;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import com.ecnu.pizzaexpress.service.inventory.IInventoryService;
import com.ecnu.pizzaexpress.service.material.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/inventory")
public class InventoryController extends BaseController {

  @Autowired
  private IInventoryService inventoryService;

  @Autowired
  private IMaterialService materialService;

  @Autowired
  private IFactoryService factoryService;

  private void check(Inventory inventory) {
    Material material = materialService.findById(inventory.getMaterialId());
    if (material == null) {
      throw new ResourceNotFoundException();
    }
    Factory factory = factoryService.findById(inventory.getFactoryId());
    if (factory == null) {
      throw new ResourceNotFoundException();
    }
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authentication(Role.Admin)
  public boolean addInventory(@RequestBody Inventory inventory) {
    check(inventory);
    return inventoryService.addInventory(inventory);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT)
  @Authentication(Role.Admin)
  public boolean updateInventory(@RequestBody Inventory inventory) {
    check(inventory);
    return inventoryService.updateInventory(inventory);
  }
}
