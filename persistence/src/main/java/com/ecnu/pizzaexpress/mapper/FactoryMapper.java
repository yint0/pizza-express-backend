package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Factory;
import java.util.List;

public interface FactoryMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Factory record);

  Factory selectByPrimaryKey(Integer id);

  List<Factory> selectAll();

  int updateByPrimaryKey(Factory record);
}