package com.ecnu.pizzaexpress.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-05
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api")
public class HelloController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello";
  }
  
}
