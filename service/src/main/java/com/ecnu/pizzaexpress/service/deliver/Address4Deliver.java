package com.ecnu.pizzaexpress.service.deliver;

import lombok.Data;

/**
 * Created by yerunjie on 2019-03-20
 *
 * @author yerunjie
 */
@Data
public class Address4Deliver {

  private String address;
  private Double lng;
  private Double lat;

  public boolean need() {
    return (lat == null || lat == 0) || (lng == null || lng == 0);
  }
}
