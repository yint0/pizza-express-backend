package com.ecnu.pizzaexpress.service.order.Impl;

import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import com.ecnu.pizzaexpress.service.menu.IMenuService;
import com.ecnu.pizzaexpress.service.order.IOrderService;
import com.ecnu.pizzaexpress.service.user.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements IOrderService {

  @Autowired
  private IDishesService iDishesService;
  private IMenuService iMenuService;
  private IDeliverService iDeliverService;
  private IUserService iUserService;

  @Override
  public int createOrder(int userId, String userAddress, List dishesList) {
    return 0;
  }

  @Override
  public int modifyOrderSta(int orderId, String orderSta) {
    return 0;
  }

  @Override
  public int adGetOrderList() {
    return 0;
  }

  @Override
  public int userGetOrderList(int userId) {
    return 0;
  }

  @Override
  public int getOrderInfo(int orderId) {
    return 0;
  }

  @Override
  public int modifyOrderInfo(int orderId, List dishesList) {
    return 0;
  }

  @Override
  public int payOrder(int orderId, int userId) {
    return 0;
  }

  @Override
  public int cancelPaidOrder(int orderId) {
    return 0;
  }

  @Override
  public int cancelUnpaidOrder(int orderId) {
    return 0;
  }
}
