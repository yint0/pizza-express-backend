package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.DishesContent;
import com.ecnu.pizzaexpress.model.MaterialWithCount;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface DishesContentMapper {

  int deleteByPrimaryKey(@Param("dishesId") Integer dishesId,
      @Param("materialId") Integer materialId);

  @Delete({"DELETE",
      "FROM dishes_content",
      "WHERE dishes_id = #{dishesId}"})
  int deleteByDishesId(int dishesId);

  int insert(DishesContent record);

  DishesContent selectByPrimaryKey(@Param("dishesId") Integer dishesId,
      @Param("materialId") Integer materialId);

  List<DishesContent> selectAll();

  List<MaterialWithCount> findByDishesId(@Param("dishesId") Integer dishesId);

  int updateByPrimaryKey(DishesContent record);
}