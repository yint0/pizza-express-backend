package com.ecnu.pizzaexpress.service.inventory.impl;

import com.ecnu.pizzaexpress.mapper.InventoryMapper;
import com.ecnu.pizzaexpress.model.Inventory;
import com.ecnu.pizzaexpress.request.AddInventoryRequest;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import com.ecnu.pizzaexpress.service.inventory.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@Service
public class InventoryServiceImpl extends BaseServiceImpl implements IInventoryService {

  @Autowired
  private InventoryMapper inventoryMapper;

  @Autowired
  private IFactoryService factoryService;

  @Override
  @Transactional
  public boolean addInventory(Inventory inventory) {
    Inventory old = inventoryMapper
        .selectByPrimaryKey(inventory.getMaterialId(), inventory.getFactoryId());
    if (old == null) {
      inventoryMapper.insert(inventory);
      return true;
    }
    AddInventoryRequest request = new AddInventoryRequest();
    request.setMaterialId(inventory.getMaterialId());
    request.setCount(old.getCount());
    request.setFactoryId(inventory.getFactoryId());
    request.setAddCount(inventory.getCount());
    inventoryMapper.addInventory(request);
    return true;
  }

  @Override
  @Transactional
  public boolean deductInventory(Inventory inventory) {
    Inventory old = inventoryMapper
        .selectByPrimaryKey(inventory.getMaterialId(), inventory.getFactoryId());
    if (old == null) {
      //throw new RuntimeException();
      return false;
    }
    if (old.getCount() < inventory.getCount()) {
      return false;
    }
    AddInventoryRequest request = new AddInventoryRequest();
    request.setMaterialId(inventory.getMaterialId());
    request.setCount(old.getCount());
    request.setFactoryId(inventory.getFactoryId());
    request.setAddCount(-inventory.getCount());
    inventoryMapper.addInventory(request);
    return true;
  }

  @Override
  @Transactional
  public boolean updateInventory(Inventory inventory) {
    inventoryMapper.deleteByPrimaryKey(inventory.getMaterialId(), inventory.getFactoryId());
    inventoryMapper.insert(inventory);
    return true;
  }
}
