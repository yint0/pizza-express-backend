package com.ecnu.pizzaexpress.service.admin;

import com.ecnu.pizzaexpress.model.Admin;

/**
 * Created by yerunjie on 2019-03-06
 *
 * @author yerunjie
 */
public interface IAdminService {

  Admin getAdminById(int id);

  Admin getAdminByAccount(String account);
}
