package com.ecnu.pizzaexpress.service.base;

import com.ecnu.pizzaexpress.dto.Token;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yerunjie on 2019-03-06
 *
 * @author yerunjie
 */
public class BaseServiceImpl {

  @Autowired
  private TokenService tokenService;

  protected Token getToken() {
    return tokenService.getToken();
  }
}
