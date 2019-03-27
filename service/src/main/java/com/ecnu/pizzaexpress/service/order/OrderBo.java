package com.ecnu.pizzaexpress.service.order;

import com.ecnu.pizzaexpress.model.DishesWithCount;
import com.ecnu.pizzaexpress.model.Order;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderBo extends Order {

  private List<DishesWithCount> dishes;
}
