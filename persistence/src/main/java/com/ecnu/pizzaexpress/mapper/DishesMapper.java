package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Dishes;
import java.util.List;

public interface DishesMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Dishes record);

  Dishes selectByPrimaryKey(Integer id);

  List<Dishes> selectAll();

  int updateByPrimaryKey(Dishes record);
}