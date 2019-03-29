package com.ecnu.pizzaexpress.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@RestController
public class TestController extends BaseController {


  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String getUserById() {
    return "hello";
  }
}
