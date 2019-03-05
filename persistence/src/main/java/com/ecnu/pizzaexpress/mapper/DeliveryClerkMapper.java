package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.DeliveryClerk;
import java.util.List;

public interface DeliveryClerkMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(DeliveryClerk record);

  DeliveryClerk selectByPrimaryKey(Integer id);

  List<DeliveryClerk> selectAll();

  int updateByPrimaryKey(DeliveryClerk record);
}