package com.ecnu.pizzaexpress.service.dishes;

import com.ecnu.pizzaexpress.model.Dishes;
import com.ecnu.pizzaexpress.model.MaterialWithCount;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DishesWithContent extends Dishes {

  private List<MaterialWithCount> contents;
}
