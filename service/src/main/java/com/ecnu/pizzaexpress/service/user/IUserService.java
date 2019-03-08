package com.ecnu.pizzaexpress.service.user;

public interface IUserService {
    int checkUser(int userId);
    int modifyUserInfo(int userId,String mPassword);
    int getUserInfo(int userId,String password);
}
