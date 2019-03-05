package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Material;
import java.util.List;

public interface MaterialMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Material record);

  Material selectByPrimaryKey(Integer id);

  List<Material> selectAll();

  int updateByPrimaryKey(Material record);
}