package com.ecnu.pizzaexpress.service.cooking;

import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public interface ICookingService {

  int calculateCookingTime(List dishesList);

}
