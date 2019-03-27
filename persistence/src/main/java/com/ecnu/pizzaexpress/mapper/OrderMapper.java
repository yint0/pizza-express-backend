package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Order;
import java.util.List;

public interface OrderMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Order record);

  Order selectByPrimaryKey(Integer id);

  List<Order> selectAll();

  int updateByPrimaryKey(Order record);

  int updateOrderStatus(Order order);
}