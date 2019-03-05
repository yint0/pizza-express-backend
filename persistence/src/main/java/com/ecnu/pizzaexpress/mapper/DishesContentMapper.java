package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.DishesContent;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DishesContentMapper {

  int deleteByPrimaryKey(@Param("dishesId") Integer dishesId,
      @Param("materialId") Integer materialId);

  int insert(DishesContent record);

  DishesContent selectByPrimaryKey(@Param("dishesId") Integer dishesId,
      @Param("materialId") Integer materialId);

  List<DishesContent> selectAll();

  int updateByPrimaryKey(DishesContent record);
}