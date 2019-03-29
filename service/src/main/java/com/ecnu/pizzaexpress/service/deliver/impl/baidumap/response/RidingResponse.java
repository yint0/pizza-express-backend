package com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RidingResponse extends BaseResponse {

  private List<RidingResult> result;
}
