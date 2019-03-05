package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.OrderLog;
import java.util.List;

public interface OrderLogMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(OrderLog record);

  OrderLog selectByPrimaryKey(Integer id);

  List<OrderLog> selectAll();

  int updateByPrimaryKey(OrderLog record);
}