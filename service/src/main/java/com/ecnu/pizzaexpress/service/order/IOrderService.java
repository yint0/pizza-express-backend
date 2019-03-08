package com.ecnu.pizzaexpress.service.order;

import java.util.List;

public interface IOrderService {

  int createOrder(int userId, String userAddress, List dishesList);

  int modifyOrderSta(int orderId, String orderSta);

  int adGetOrderList();

  int userGetOrderList(int userId);

  int getOrderInfo(int orderId);

  int modifyOrderInfo(int orderId, List dishesList);

  int payOrder(int orderId, int userId);

  int cancelPaidOrder(int orderId );

  int cancelUnpaidOrder(int orderId );
}
