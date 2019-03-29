package com.ecnu.pizzaexpress.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yerunjie on 2019-03-22
 *
 * @author yerunjie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchOrderRequest extends PageRequest {

  private int userId;

}
