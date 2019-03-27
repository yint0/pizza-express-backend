package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.OrderDishes;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDishesMapper {

  int deleteByPrimaryKey(@Param("orderId") Integer orderId, @Param("dishesId") Integer dishesId);

  int deleteByOrderId(@Param("orderId") Integer orderId);

  int insert(OrderDishes record);

  OrderDishes selectByPrimaryKey(@Param("orderId") Integer orderId,
      @Param("dishesId") Integer dishesId);

  List<OrderDishes> selectAll();

  int updateByPrimaryKey(OrderDishes record);
}