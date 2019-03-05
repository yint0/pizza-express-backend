package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.MaterialLog;
import java.util.List;

public interface MaterialLogMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(MaterialLog record);

  MaterialLog selectByPrimaryKey(Integer id);

  List<MaterialLog> selectAll();

  int updateByPrimaryKey(MaterialLog record);
}