package com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GeocoderResponse extends BaseResponse {

  private GeocoderResult result;
}
