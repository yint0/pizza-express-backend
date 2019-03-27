package com.ecnu.pizzaexpress.service.deliver.impl;

import com.ecnu.pizzaexpress.model.Factory;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.deliver.Address4Deliver;
import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
@Service
public class DeliverServiceImpl extends BaseServiceImpl implements IDeliverService {

  @Override
  public List<Factory> getAvailableFactories(Address4Deliver address4Deliver) {
    return null;
  }
}
