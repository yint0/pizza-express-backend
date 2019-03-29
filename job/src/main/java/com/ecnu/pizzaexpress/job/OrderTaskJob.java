package com.ecnu.pizzaexpress.job;

import com.ecnu.pizzaexpress.mq.dto.OrderStatusChange;
import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import com.ecnu.pizzaexpress.service.message.IMessageService;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yerunjie on 2019-03-28
 *
 * @author yerunjie
 */
@Component
public class OrderTaskJob extends BaseMessageConsumer<OrderStatusChange> {

  @Override
  protected List<String> getTopics() {
    return Lists.newArrayList("order.status.change");
  }

  @Autowired
  private IDeliverService deliverService;

  @Autowired
  private IMessageService messageService;

  @Override
  public void onReceive(String topic, OrderStatusChange data) {
    switch (data.getTo()) {
      case PAID:
        deliverService.distributeDeliver(data.getOrderId());
        break;
      default:
        messageService.notifyOrderStatusChange(data.getOrderId(), data.getTo());
        break;
    }
  }


}
