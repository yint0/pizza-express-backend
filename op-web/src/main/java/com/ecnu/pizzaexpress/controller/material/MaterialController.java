package com.ecnu.pizzaexpress.controller.material;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.model.Material;
import com.ecnu.pizzaexpress.service.material.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/v1/api/material")
public class MaterialController extends BaseController {

  @Autowired
  private IMaterialService materialService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  @Authentication(Role.Admin)
  public Material createMaterial(@RequestBody Material material) {
    materialService.createMaterial(material);
    return material;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @Authentication(Role.Admin)
  public Material getMaterial(@PathVariable("id") int id) {
    return materialService.findById(id);
  }


}
