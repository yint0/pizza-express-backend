package com.ecnu.pizzaexpress.service.order;

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

  List<Order> searchOrders(SearchOrderRequest request);
}
