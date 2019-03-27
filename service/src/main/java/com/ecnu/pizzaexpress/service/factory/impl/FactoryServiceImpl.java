package com.ecnu.pizzaexpress.service.factory.impl;

import com.ecnu.pizzaexpress.constants.FactoryStatus;
import com.ecnu.pizzaexpress.mapper.FactoryMapper;
import com.ecnu.pizzaexpress.model.Factory;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.factory.IFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@Service
public class FactoryServiceImpl extends BaseServiceImpl implements IFactoryService {

  @Autowired
  private FactoryMapper factoryMapper;

  @Override
  public Factory create(Factory factory) {
    factory.setStatus(FactoryStatus.ONLINE);
    factoryMapper.insert(factory);
    return factory;
  }

  @Override
  public Factory findById(int id) {
    Factory factory = factoryMapper.selectByPrimaryKey(id);
    return factory;
  }

  @Override
  public boolean update(Factory factory) {
    factoryMapper.updateByPrimaryKey(factory);
    return true;
  }
}
