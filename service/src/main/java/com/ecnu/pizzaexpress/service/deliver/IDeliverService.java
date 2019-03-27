package com.ecnu.pizzaexpress.service.deliver;

import com.ecnu.pizzaexpress.model.Factory;
import java.util.List;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
public interface IDeliverService {

  List<Factory> getAvailableFactories(Address4Deliver address4Deliver);

}
