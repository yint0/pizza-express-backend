package com.ecnu.pizzaexpress.service.user.Impl;

import com.ecnu.pizzaexpress.mapper.UserMapper;
import com.ecnu.pizzaexpress.model.User;
import com.ecnu.pizzaexpress.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  UserMapper userMapper;

 /* @Override
  public int login(int userId, String password) {
    User user = userMapper.selectByPrimaryKey(userId);
    if (user == null) {
      return -1;
    }

    if(user.getPassword().equals("password"))

  {
    return userId;
  } else

  {
    return 0;
  }
}*/


  @Override
  public int checkUser(int userId) {
    return 0;
  }

  @Override
  public int modifyUserInfo(int userId, String mPassword) {
    return 0;
  }

  @Override
  public int getUserInfo(int userId, String password) {
    return 0;
  }
}
