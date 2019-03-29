package com.ecnu.pizzaexpress.controller.deliver;

import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.service.deliver.Address4Deliver;
import com.ecnu.pizzaexpress.service.deliver.IDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@RestController
@RequestMapping("/v1/api/deliver")
public class DeliverController extends BaseController {

  @Autowired
  private IDeliverService deliverService;

  @RequestMapping(value = "/available_factories", method = RequestMethod.POST)
  public Object updateFactory(@RequestBody Address4Deliver address4Deliver) {
    return deliverService.getAvailableFactories(address4Deliver, 1800);
  }
}
