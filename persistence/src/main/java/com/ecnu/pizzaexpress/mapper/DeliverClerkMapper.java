package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.DeliverClerk;
import java.util.List;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface DeliverClerkMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(DeliverClerk record);

  DeliverClerk selectByPrimaryKey(Integer id);

  List<DeliverClerk> selectAll();

  int updateByPrimaryKey(DeliverClerk record);

  int updateStatus(DeliverClerk record);

  @Select({"SELECT *",
      "FROM deliver_clerk",
      "WHERE factory_id = #{factoryId}"})
  @ResultMap("BaseResultMap")
  List<DeliverClerk> findByFactoryId(int factoryId);
}