package com.ecnu.pizzaexpress.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {

  private int id;

  private String account;

  private String telephone;

  private String qq;

  private String wechat;

  private String password;

  private String nickName;

  private Date birthday;

  private String address;
}