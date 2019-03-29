package com.ecnu.pizzaexpress.mq.dto;

import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.constants.Role;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by yerunjie on 2019-03-28
 *
 * @author yerunjie
 */
@Data
public class OrderStatusChange implements Serializable {

  private Role role;
  private OrderStatus from;
  private OrderStatus to;
  private int orderId;
}
