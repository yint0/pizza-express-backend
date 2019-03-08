package com.ecnu.pizzaexpress.service.deliver.Impl;

import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliverServiceImpl implements IDeliverService {

  @Autowired
  private IFactoryService iFactoryService;


  @Override
  public int chooseFactoryList(String factoryAddress, String userAddress) {
    return 0;
  }

  @Override
  public int calculateTimeSpend(String factoryAddress, String userAddress) {
    return 0;
  }

  @Override
  public int checkDeliverStaff(int factoryId) {
    return 0;
  }
}
