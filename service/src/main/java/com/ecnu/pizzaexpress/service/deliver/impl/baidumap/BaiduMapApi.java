package com.ecnu.pizzaexpress.service.deliver.impl.baidumap;

import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.GeocoderResponse;
import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.response.RidingResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
public interface BaiduMapApi {

  @RequestMapping(value = "/geocoder/v2/", method = RequestMethod.GET)
  GeocoderResponse geocoder(@RequestParam("address") String address);


  @RequestMapping(value = "/routematrix/v2/riding", method = RequestMethod.GET)
  RidingResponse riding(@RequestParam("origins") String origins,
      @RequestParam("destinations") String destinations);
}
