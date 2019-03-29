package com.ecnu.pizzaexpress.service.message.impl;

import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.model.DeliverClerk;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.message.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by yerunjie on 2019-03-28
 *
 * @author yerunjie
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl implements IMessageService {

  private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

  @Override
  public boolean notifyDeliverClerk(int orderId, DeliverClerk deliverClerk) {
    // TODO: 2019-03-28
    logger.info("starting delivering orderId:{},deliverClerkId:{}", orderId, deliverClerk.getId());
    return true;
  }

  @Override
  public boolean notifyOrderStatusChange(int orderId, OrderStatus to) {
    // TODO: 2019-03-28
    logger.info("orderId:{},OrderStatus:{}", orderId, to.getDesc());
    return true;
  }
}
