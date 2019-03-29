package com.ecnu.pizzaexpress.service.dishes.impl;

import com.ecnu.pizzaexpress.constants.DishesStatus;
import com.ecnu.pizzaexpress.exception.ResourceNotFoundException;
import com.ecnu.pizzaexpress.mapper.DishesContentMapper;
import com.ecnu.pizzaexpress.mapper.DishesMapper;
import com.ecnu.pizzaexpress.mapper.TypeMapper;
import com.ecnu.pizzaexpress.model.Dishes;
import com.ecnu.pizzaexpress.model.DishesContent;
import com.ecnu.pizzaexpress.model.MaterialWithCount;
import com.ecnu.pizzaexpress.model.Type;
import com.ecnu.pizzaexpress.request.SearchDishesRequest;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.dishes.DishesWithContent;
import com.ecnu.pizzaexpress.service.dishes.IDishesService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yerunjie on 2019-03-15
 *
 * @author yerunjie
 */
@Service
public class DishesServiceImpl extends BaseServiceImpl implements IDishesService {

  @Autowired
  private DishesMapper dishesMapper;

  @Autowired
  private DishesContentMapper dishesContentMapper;

  @Autowired
  private TypeMapper typeMapper;

  @Override
  @Transactional
  public DishesWithContent createDishes(DishesWithContent dishesWithContent) {
    Dishes dishes = new Dishes();
    BeanUtils.copyProperties(dishesWithContent, dishes);
    dishes.setStatus(DishesStatus.ONLINE);
    Type type = typeMapper.selectByPrimaryKey(dishes.getTypeId());
    dishes.setTypeName(type.getName());
    dishesMapper.insert(dishes);
    dishesWithContent.setId(dishes.getId());
    resetContents(dishes.getId(), dishesWithContent.getContents());
    return dishesWithContent;
  }

  @Override
  public DishesWithContent findByIdWithContent(int id) {
    Dishes dishes = dishesMapper.selectByPrimaryKey(id);
    if (dishes == null) {
      throw new ResourceNotFoundException();
    }
    List<MaterialWithCount> contents = dishesContentMapper.findByDishesId(id);
    if (contents.isEmpty()) {
      throw new RuntimeException();
    }
    DishesWithContent dishesWithContent = new DishesWithContent();
    BeanUtils.copyProperties(dishes, dishesWithContent);
    dishesWithContent.setContents(contents);
    return dishesWithContent;
  }

  @Override
  @Transactional
  public boolean updateDishes(DishesWithContent dishesWithContent) {
    Dishes dishes = new Dishes();
    BeanUtils.copyProperties(dishesWithContent, dishes);
    dishesMapper.updateByPrimaryKey(dishes);
    resetContents(dishes.getId(), dishesWithContent.getContents());
    return true;
  }

  private void resetContents(int dishesId, List<MaterialWithCount> contents) {
    dishesContentMapper.deleteByDishesId(dishesId);
    if (contents.isEmpty()) {
      throw new RuntimeException();
    }
    for (MaterialWithCount content : contents) {
      DishesContent dishesContent = new DishesContent();
      dishesContent.setDishesId(dishesId);
      dishesContent.setMaterialId(content.getId());
      dishesContent.setUseCount(content.getUseCount());
      dishesContentMapper.insert(dishesContent);
    }
  }

  @Override
  public List<Dishes> findByIds(List<Integer> ids) {
    return dishesMapper.selectAll();
  }

  @Override
  public List<Dishes> findByRequest(SearchDishesRequest request) {
    return dishesMapper.selectAll();
  }
}
