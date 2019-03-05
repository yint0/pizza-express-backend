package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.Admin;
import java.util.List;

public interface AdminMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(Admin record);

  Admin selectByPrimaryKey(Integer id);

  List<Admin> selectAll();

  int updateByPrimaryKey(Admin record);
}