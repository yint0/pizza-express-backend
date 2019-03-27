package com.ecnu.pizzaexpress.controller.admin;

import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.controller.common.LoginRequest;
import com.ecnu.pizzaexpress.controller.common.LoginResponse;
import com.ecnu.pizzaexpress.model.Admin;
import com.ecnu.pizzaexpress.service.admin.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/v1/api/admin")
public class AdminController extends BaseController {

  @Autowired
  private IAdminService adminService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public LoginResponse login(@RequestBody LoginRequest request) {
    Admin admin = adminService.getAdminByAccount(request.getAccount());
    if (admin == null) {
      throw new RuntimeException();
    }
    if (!admin.getPassword().equals(request.getPassword())) {
      throw new RuntimeException();
    }
    String token = tokenService.generateToken(Role.Admin, admin.getId());
    LoginResponse response = new LoginResponse();
    response.setToken(token);
    return response;
  }

}
