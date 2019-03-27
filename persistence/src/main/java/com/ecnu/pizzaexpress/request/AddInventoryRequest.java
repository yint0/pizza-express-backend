package com.ecnu.pizzaexpress.request;

import lombok.Data;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@Data
public class AddInventoryRequest {

  private int materialId;

  private int factoryId;

  private double count;

  private double addCount;
}
