package com.ecnu.pizzaexpress.dto;

import com.ecnu.pizzaexpress.constants.Role;
import lombok.Data;

@Data
public class Token {

  private Role role;
  private long expireTime;
  private int id;
  private int version;

  public Token(Role role, int id, long expireTime) {
    this(role, id, expireTime, -1);
  }

  public Token(Role role, int id, long expireTime, int version) {
    this.role = role;
    this.expireTime = expireTime;
    this.id = id;
    this.version = version;
  }

  public boolean isExpired() {
    return System.currentTimeMillis() >= expireTime;
  }

  @Override
  public String toString() {
    return role.toString() + "_" + id;
  }

  public boolean isAdmin() {
    return role == Role.Admin;
  }

  public boolean isUser() {
    return role == Role.User;
  }
}
