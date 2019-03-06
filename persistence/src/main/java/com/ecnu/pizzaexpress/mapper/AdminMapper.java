package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Admin;
import java.util.List;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Admin record);

  Admin findById(Integer id);

  List<Admin> selectAll();

  int updateByPrimaryKey(Admin record);

  @Select({"SELECT *",
      "FROM admin",
      "WHERE account = #{account}"})
  @ResultMap("BaseResultMap")
  Admin findByAccount(String account);
}