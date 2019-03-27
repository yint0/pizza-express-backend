package com.ecnu.pizzaexpress.service.factory;

import com.ecnu.pizzaexpress.model.Factory;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
public interface IFactoryService {

  Factory create(Factory factory);

  Factory findById(int id);

  boolean update(Factory factory);
}
