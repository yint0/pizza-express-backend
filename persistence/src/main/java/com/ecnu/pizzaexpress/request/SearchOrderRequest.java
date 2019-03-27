package com.ecnu.pizzaexpress.request;

import lombok.Data;

/**
 * Created by yerunjie on 2019-03-22
 *
 * @author yerunjie
 */
@Data
public class SearchOrderRequest {

  private int offset;
  private int pageSize;

}
