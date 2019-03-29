package com.ecnu.pizzaexpress.service.message;

import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.model.DeliverClerk;

/**
 * Created by yerunjie on 2019-03-28
 *
 * @author yerunjie
 */
public interface IMessageService {

  boolean notifyDeliverClerk(int orderId, DeliverClerk deliverClerk);

  boolean notifyOrderStatusChange(int orderId, OrderStatus to);
}
