package com.ecnu.pizzaexpress.service.order;

import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.model.Order;
import com.ecnu.pizzaexpress.request.SearchOrderRequest;
import java.util.List;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
public interface IOrderService {

  OrderBo createOrder(OrderBo orderBo);

  OrderBo updateOrder(OrderBo orderBo);

  OrderBo updateDeliverInfo(OrderBo orderBo);

  OrderBo findById(int id);

  List<Order> searchOrders(SearchOrderRequest request);

  boolean updateOrderStatus(int id, OrderStatus status);
}
