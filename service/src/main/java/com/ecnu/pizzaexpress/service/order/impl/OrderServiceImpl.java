package com.ecnu.pizzaexpress.service.order.impl;

import com.ecnu.pizzaexpress.constants.DishesStatus;
import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.mapper.OrderDishesMapper;
import com.ecnu.pizzaexpress.mapper.OrderMapper;
import com.ecnu.pizzaexpress.model.Dishes;
import com.ecnu.pizzaexpress.model.DishesWithCount;
import com.ecnu.pizzaexpress.model.Order;
import com.ecnu.pizzaexpress.model.OrderDishes;
import com.ecnu.pizzaexpress.model.User;
import com.ecnu.pizzaexpress.mq.MessageProducer;
import com.ecnu.pizzaexpress.mq.dto.OrderStatusChange;
import com.ecnu.pizzaexpress.request.SearchOrderRequest;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.inventory.IInventoryService;
import com.ecnu.pizzaexpress.service.order.IOrderService;
import com.ecnu.pizzaexpress.service.order.OrderBo;
import com.ecnu.pizzaexpress.service.user.IUserService;
import com.ecnu.pizzaexpress.utils.GuidGenerator;
import com.google.common.base.Strings;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private OrderDishesMapper orderDishesMapper;

  @Autowired
  private IDishesService dishesService;

  @Autowired
  private IUserService userService;

  @Autowired
  private IInventoryService inventoryService;

  @Autowired
  private MessageProducer messageProducer;

  @Override
  @Transactional
  public OrderBo createOrder(OrderBo orderBo) {
    List<DishesWithCount> dishesWithCounts = orderBo.getDishes();
    checkInventory(dishesWithCounts);
    resetPrice(orderBo);
    orderBo.setStatus(OrderStatus.NEW);
    orderBo.setUuid(GuidGenerator.generate(14));
    orderBo.setUserId(getToken().getId());
    orderBo.setCreateTime(new Date());
    User user = userService.findById(getToken().getId());
    if (Strings.isNullOrEmpty(orderBo.getAddress())) {
      orderBo.setAddress(user.getAddress());
    }

    Order order = new Order();
    BeanUtils.copyProperties(orderBo, order);
    orderMapper.insert(order);
    int orderId = order.getId();
    orderBo.setId(orderId);
    resetOrderDishes(orderId, dishesWithCounts);
    return orderBo;
  }

  private void checkInventory(List<DishesWithCount> dishesWithCounts) {
    if (!dishesService
        .findByIds(dishesWithCounts.stream().map(Dishes::getId).collect(Collectors.toList()))
        .stream().allMatch(dishes -> dishes.getStatus() == DishesStatus.ONLINE)) {
      throw new RuntimeException();
    }
    //inventoryService.deductInventory()
  }

  private void resetPrice(OrderBo orderBo) {
    List<DishesWithCount> dishesWithCounts = orderBo.getDishes();
    List<Dishes> dishesList = dishesService
        .findByIds(dishesWithCounts.stream().map(Dishes::getId).collect(Collectors.toList()));
    Map<Integer, Dishes> dishesMap = dishesList.stream()
        .collect(Collectors.toMap(Dishes::getId, i -> i));
    BigDecimal costPrice = dishesWithCounts.stream()
        .map(dishesWithCount -> dishesMap.get(dishesWithCount.getId()).getCostPrice()
            .multiply(new BigDecimal(dishesWithCount.getCount())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    orderBo.setCostPrice(costPrice);
    BigDecimal salePrice = dishesWithCounts.stream()
        .map(dishesWithCount -> dishesMap.get(dishesWithCount.getId()).getSalePrice()
            .multiply(new BigDecimal(dishesWithCount.getCount())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    orderBo.setSalePrice(salePrice);
    String detail = dishesMap.get(dishesWithCounts.get(0).getId()).getName();
    if (dishesWithCounts.size() == 1) {
      orderBo.setDetail(detail);
    } else {
      orderBo.setDetail(detail + "等" + dishesWithCounts.size() + "件商品");
    }

  }

  private void resetOrderDishes(int orderId, List<DishesWithCount> dishesWithCounts) {
    orderDishesMapper.deleteByOrderId(orderId);
    if (dishesWithCounts.isEmpty()) {
      throw new RuntimeException();
    }
    for (DishesWithCount dishesWithCount : dishesWithCounts) {
      int dishesId = dishesWithCount.getId();
      int count = dishesWithCount.getCount();
      OrderDishes orderDishes = new OrderDishes();
      orderDishes.setOrderId(orderId);
      orderDishes.setDishesId(dishesId);
      orderDishes.setCount(count);
      orderDishesMapper.insert(orderDishes);
    }
  }

  @Override
  @Transactional
  public OrderBo updateOrder(OrderBo orderBo) {
    int orderId = orderBo.getId();
    Order old = orderMapper.selectByPrimaryKey(orderId);
    if (old.getStatus() != OrderStatus.NEW) {
      throw new RuntimeException();
    }
    List<DishesWithCount> dishesWithCounts = orderBo.getDishes();
    checkInventory(dishesWithCounts);
    resetPrice(orderBo);
    old.setCostPrice(orderBo.getCostPrice());
    old.setSalePrice(orderBo.getSalePrice());
    old.setRemark(Strings.nullToEmpty(orderBo.getRemark()));
    orderMapper.updateOrderInfo(old);
    resetOrderDishes(orderId, dishesWithCounts);
    return orderBo;
  }

  @Override
  @Transactional
  public OrderBo updateDeliverInfo(OrderBo orderBo) {
    orderMapper.updateDeliverInfo(orderBo);
    return orderBo;
  }

  @Override
  public List<Order> searchOrders(SearchOrderRequest request) {
    return orderMapper.findByRequest(request);
  }

  @Override
  public OrderBo findById(int id) {
    Order order = orderMapper.selectByPrimaryKey(id);
    if (order == null) {
      throw new RuntimeException();
    }
    List<DishesWithCount> dishes = orderDishesMapper.findByOrderId(id);
    OrderBo orderBo = new OrderBo();
    BeanUtils.copyProperties(order, orderBo);
    orderBo.setDishes(dishes);
    return orderBo;
  }

  private void checkStatus(OrderStatus from, OrderStatus to) {

  }

  @Override
  public boolean updateOrderStatus(int id, OrderStatus status) {
    Order order = orderMapper.selectByPrimaryKey(id);
    if (order == null) {
      throw new RuntimeException();
    }
    OrderStatus from = order.getStatus();
    checkStatus(from, status);
    order.setStatus(status);
    orderMapper.updateOrderStatus(order);
    OrderStatusChange orderStatusChange = new OrderStatusChange();
    orderStatusChange.setOrderId(id);
    orderStatusChange.setFrom(from);
    orderStatusChange.setTo(status);
    //orderStatusChange.setRole(getToken().getRole());
    messageProducer.send("order.status.change", orderStatusChange);
    return true;
  }
}
