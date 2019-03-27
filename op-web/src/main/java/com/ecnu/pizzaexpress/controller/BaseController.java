package com.ecnu.pizzaexpress.controller;

import com.ecnu.pizzaexpress.dto.Token;
import com.ecnu.pizzaexpress.service.base.TokenService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yerunjie on 2018-12-16
 *
 * @author yerunjie
 */
public abstract class BaseController {

  @Autowired
  protected HttpServletRequest request;

  @Autowired
  protected HttpServletResponse response;

  @Autowired
  protected TokenService tokenService;

  public void setToken(Token token) {
    tokenService.setToken(token);
  }

  public Token getToken() {
    return tokenService.getToken();
  }

  protected String getCookie(String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      return null;
    }
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(cookieName)) {
        return cookie.getValue();
      }
    }
    return null;
  }

  protected void addCookie(String name, String value) {
    addCookie(name, value, "/");
  }

  protected void addCookie(String name, String value, String path) {
    addCookie(name, value, path, -1);
  }

  protected void addCookie(String name, String value, String path, int maxAge) {
    Cookie cookie = new Cookie(name.trim(), value.trim());
    cookie.setMaxAge(maxAge);
    cookie.setPath(path);
    response.addCookie(cookie);
  }
}
