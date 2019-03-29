package com.ecnu.pizzaexpress.service.deliver;

import com.ecnu.pizzaexpress.model.DeliverClerk;
import java.util.List;
import java.util.Map;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
public interface IDeliverService {

  Map<Integer, Integer> getAvailableFactories(Address4Deliver address4Deliver, int secondThreshold);

  List<DeliverClerk> getAvailableDeliveryClerk(int factoryId);

  void distributeDeliver(int orderId);
}
