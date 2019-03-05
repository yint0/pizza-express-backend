package com.ecnu.pizzaexpress.mapper;

import com.ecnu.pizzaexpress.model.User;
import java.util.List;

public interface UserMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(User record);

  User selectByPrimaryKey(Integer id);

  List<User> selectAll();

  int updateByPrimaryKey(User record);
}