package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Inventory;
import com.ecnu.pizzaexpress.request.AddInventoryRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InventoryMapper {

  int deleteByPrimaryKey(@Param("materialId") Integer materialId,
      @Param("factoryId") Integer factoryId);

  int insert(Inventory record);

  Inventory selectByPrimaryKey(@Param("materialId") Integer materialId,
      @Param("factoryId") Integer factoryId);

  List<Inventory> selectAll();

  int updateByPrimaryKey(Inventory record);

  int addInventory(AddInventoryRequest request);
}