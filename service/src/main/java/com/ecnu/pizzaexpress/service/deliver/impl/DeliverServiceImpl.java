package com.ecnu.pizzaexpress.service.deliver.impl;

import com.ecnu.pizzaexpress.constants.DeliverClerkStatus;
import com.ecnu.pizzaexpress.constants.OrderStatus;
import com.ecnu.pizzaexpress.mapper.DeliverClerkMapper;
import com.ecnu.pizzaexpress.mapper.FactoryMapper;
import com.ecnu.pizzaexpress.model.DeliverClerk;
import com.ecnu.pizzaexpress.model.Factory;
import com.ecnu.pizzaexpress.model.Inventory;
import com.ecnu.pizzaexpress.model.Material;
import com.ecnu.pizzaexpress.model.MaterialWithCount;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.deliver.Address4Deliver;
import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.BaiduMapApi;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.GeocoderResponse;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.Location;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.RidingResponse;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.RidingResult;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import com.ecnu.pizzaexpress.service.inventory.IInventoryService;
import com.ecnu.pizzaexpress.service.message.IMessageService;
import com.ecnu.pizzaexpress.service.order.IOrderService;
import com.ecnu.pizzaexpress.service.order.OrderBo;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
@Service
public class DeliverServiceImpl extends BaseServiceImpl implements IDeliverService {

  @Autowired
  private BaiduMapApi baiduMapApi;

  @Autowired
  private FactoryMapper factoryMapper;

  @Autowired
  private DeliverClerkMapper deliveryClerkMapper;

  @Autowired
  private IMessageService messageService;

  @Autowired
  private IInventoryService inventoryService;

  @Autowired
  private IOrderService orderService;

  @Autowired
  private IDishesService dishesService;

  @Override
  public Map<Integer, Integer> getAvailableFactories(Address4Deliver address4Deliver,
      int secondThreshold) {
    if (address4Deliver.need()) {
      GeocoderResponse geocoder = baiduMapApi.geocoder(address4Deliver.getAddress());
      Location location = geocoder.getResult().getLocation();
      address4Deliver.setLat(location.getLat());
      address4Deliver.setLng(location.getLng());
    }
    String destinations = address4Deliver.getLat() + "," + address4Deliver.getLng();
    List<Factory> factories = factoryMapper.selectAll();
    String origins = factories.stream().map(factory -> factory.getLat() + "," + factory.getLng())
        .collect(Collectors.joining("|"));
    RidingResponse riding = baiduMapApi.riding(origins, destinations);
    List<RidingResult> ridingResults = riding.getResult();
    Map<Integer, Integer> result = new HashMap<>();
    for (int i = 0; i < factories.size(); i++) {
      Factory factory = factories.get(i);
      LocalTime now = LocalTime.now();
      if (now.isAfter(factory.getOpenTime()) && now.isBefore(factory.getCloseTime())) {
        // FIXME: 2019-03-28 暂时不考虑承载量
        if (ridingResults.get(i).getDuration().getValue() < secondThreshold) {
          result.put(factory.getId(), ridingResults.get(i).getDuration().getValue());
        }
      }
    }
    return result;
  }

  @Override
  public List<DeliverClerk> getAvailableDeliveryClerk(int factoryId) {
    List<DeliverClerk> deliverClerks = deliveryClerkMapper.findByFactoryId(factoryId);
    return deliverClerks.stream()
        .filter(deliverClerk -> deliverClerk.getStatus() == DeliverClerkStatus.IDLE)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void distributeDeliver(int orderId) {
    OrderBo orderBo = orderService.findById(orderId);
    if (orderBo.getStatus() != OrderStatus.PAID) {
      return;
    }
    Address4Deliver address4Deliver = new Address4Deliver();
    address4Deliver.setAddress(orderBo.getAddress());
    // FIXME: 2019-03-28 版本1不计算制作时间
    Map<Integer, Integer> availableFactories = getAvailableFactories(address4Deliver, 1800);
    List<Entry<Integer, Integer>> collect = availableFactories.entrySet().stream()
        .sorted(Entry.comparingByValue()).collect(Collectors.toList());
    if (collect.isEmpty()) {
      distributeFail(orderId);
      return;
    }
    DeliverClerk deliverClerk = null;
    for (Entry<Integer, Integer> entry : collect) {
      int factoryId = entry.getKey();
      List<DeliverClerk> deliverClerks = getAvailableDeliveryClerk(factoryId);
      if (deliverClerks.isEmpty()) {
        continue;
      }
      List<Inventory> inventories = orderBo.getDishes().stream()
          .flatMap(dish ->
              dishesService.findByIdWithContent(dish.getId()).getContents().stream().peek(
                  materialWithCount -> materialWithCount
                      .setUseCount(materialWithCount.getUseCount() * dish.getCount())))
          .collect(Collectors.groupingBy(Material::getId,
              Collectors.reducing(0.0, MaterialWithCount::getUseCount, Double::sum)))
          .entrySet().stream()
          .map(doubleEntry -> {
            Inventory inventory = new Inventory();
            inventory.setFactoryId(factoryId);
            inventory.setMaterialId(doubleEntry.getKey());
            inventory.setCount(doubleEntry.getValue());
            return inventory;
          }).collect(Collectors.toList());
      if (inventories.stream().allMatch(inventory -> inventoryService.checkInventory(inventory))) {
        inventories.forEach(inventory -> inventoryService.deductInventory(inventory));
      } else {
        continue;
      }
      deliverClerk = deliverClerks.get(0);
      break;
    }
    if (deliverClerk == null) {
      distributeFail(orderId);
      return;
    }
    orderBo.setStatus(OrderStatus.DELIVERING);
    orderBo.setFactoryId(deliverClerk.getFactoryId());
    orderBo.setDeliverClerkId(deliverClerk.getId());
    orderBo.setDeliverTime(new Date());
    orderService.updateDeliverInfo(orderBo);
    orderService.updateOrderStatus(orderId, OrderStatus.DELIVERING);
    messageService.notifyDeliverClerk(orderBo.getId(), deliverClerk);
    deliverClerk.setStatus(DeliverClerkStatus.DELIVERING);
    deliveryClerkMapper.updateStatus(deliverClerk);
    // FIXME: 2019-03-29
    executorService.schedule(new Temp(deliverClerk, orderId), 15, TimeUnit.SECONDS);
  }

  class Temp implements Runnable {

    DeliverClerk deliverClerk;
    int orderId;

    public Temp(DeliverClerk deliverClerk, int orderId) {
      this.deliverClerk = deliverClerk;
      this.orderId = orderId;
    }

    @Override
    public void run() {
      orderService.updateOrderStatus(orderId, OrderStatus.FINISH);
      deliverClerk.setStatus(DeliverClerkStatus.IDLE);
      deliveryClerkMapper.updateStatus(deliverClerk);
    }
  }

  private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

  private void distributeFail(int orderId) {
    orderService.updateOrderStatus(orderId, OrderStatus.CANCEL);
  }

}
