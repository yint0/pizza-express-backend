package com.ecnu.pizzaexpress.controller.order;

import com.ecnu.pizzaexpress.model.DishesWithCount;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * Created by yerunjie on 2019-03-28
 *
 * @author yerunjie
 */
@Data
public class OrderVo {

  private int id;

  private String uuid;

  private int userId;

  private String status;

  private BigDecimal costPrice;

  private BigDecimal salePrice;

  private int factoryId;

  private int deliverClerkId;

  private String remark;

  private String createTime;

  private String address;

  private String deliverTime;

  private String detail;

  private List<DishesWithCount> dishes;
}
