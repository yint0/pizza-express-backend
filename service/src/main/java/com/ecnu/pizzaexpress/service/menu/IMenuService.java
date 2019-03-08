package com.ecnu.pizzaexpress.service.menu;

import java.util.List;

public interface IMenuService {

  int getMenuInfo();

  int manageMenu(String dishes, boolean isOnShelf);
}
