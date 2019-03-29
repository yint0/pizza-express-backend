package com.ecnu.pizzaexpress.controller.user;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.controller.common.LoginRequest;
import com.ecnu.pizzaexpress.controller.common.LoginResponse;
import com.ecnu.pizzaexpress.model.User;
import com.ecnu.pizzaexpress.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-13
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/user")
public class UserController extends BaseController {

  @Autowired
  private IUserService userService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @Authentication({Role.Admin, Role.User})
  public User getUserById(@PathVariable("id") int id) {
    User user = userService.findById(id);
    return user;
  }

  @RequestMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    User user = userService.findByAccount(request.getAccount());
    if (user == null) {
      throw new RuntimeException();
    }
    if (!user.getPassword().equals(request.getPassword())) {
      throw new RuntimeException();
    }
    String token = tokenService.generateToken(Role.User, user.getId());
    LoginResponse response = new LoginResponse();
    response.setToken(token);
    addCookie("token", token);
    return response;
  }

  @RequestMapping("/register")
  public LoginResponse register(@RequestBody LoginRequest request) {
    User user = userService.findByAccount(request.getAccount());
    if (user == null) {
      throw new RuntimeException();
    }
    if (!user.getPassword().equals(request.getPassword())) {
      throw new RuntimeException();
    }
    String token = tokenService.generateToken(Role.User, user.getId());
    LoginResponse response = new LoginResponse();
    response.setToken(token);
    return response;
  }
}
