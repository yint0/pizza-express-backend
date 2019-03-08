package com.ecnu.pizzaexpress.service.factory.Impl;

import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import com.ecnu.pizzaexpress.service.reserve.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;

public class FactoryServiceImpl implements IFactoryService {

  @Autowired
  private IReserveService iReserveService;

  @Override
  public int createFactory(int factoryId, String factoryAddress) {
    return 0;
  }

  @Override
  public int modifyFactoryInfo(int factoryId, String factoryAddress, String factoryStatus) {
    return 0;
  }

  @Override
  public int getFactoryInfo(int factoryId, String factoryAddress, String factoryStatus) {
    return 0;
  }

  @Override
  public int modifyFactorySta(int factoryId, String factoryStatus) {
    return 0;
  }

  @Override
  public int getFactoryList() {
    return 0;
  }
}
