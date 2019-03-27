package com.ecnu.pizzaexpress.service.material;

import com.ecnu.pizzaexpress.model.Material;

/**
 * Created by yerunjie on 2019-03-13
 *
 * @author yerunjie
 */
public interface IMaterialService {

  Material createMaterial(Material material);

  Material findById(int id);
}
