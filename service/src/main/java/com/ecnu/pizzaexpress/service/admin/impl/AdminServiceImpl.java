package com.ecnu.pizzaexpress.service.admin.impl;

import com.ecnu.pizzaexpress.exception.ResourceNotFoundException;
import com.ecnu.pizzaexpress.mapper.AdminMapper;
import com.ecnu.pizzaexpress.model.Admin;
import com.ecnu.pizzaexpress.service.admin.IAdminService;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-06
 *
 * @author yerunjie
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl implements IAdminService {

  @Autowired
  private AdminMapper adminMapper;

  @Override
  public Admin getAdminById(int id) {
    Admin admin = adminMapper.findById(id);
    if (admin == null) {
      throw new ResourceNotFoundException();
    }
    return admin;
  }

  @Override
  public Admin getAdminByAccount(String account) {
    return adminMapper.findByAccount(account);
  }
}
