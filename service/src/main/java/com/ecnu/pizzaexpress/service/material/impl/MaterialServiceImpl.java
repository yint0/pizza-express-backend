package com.ecnu.pizzaexpress.service.material.impl;

import com.ecnu.pizzaexpress.constants.MaterialStatus;
import com.ecnu.pizzaexpress.dto.Token;
import com.ecnu.pizzaexpress.exception.AuthenticationFailureException;
import com.ecnu.pizzaexpress.exception.ResourceNotFoundException;
import com.ecnu.pizzaexpress.mapper.MaterialLogMapper;
import com.ecnu.pizzaexpress.mapper.MaterialMapper;
import com.ecnu.pizzaexpress.model.Material;
import com.ecnu.pizzaexpress.model.MaterialLog;
import com.ecnu.pizzaexpress.service.base.BaseServiceImpl;
import com.ecnu.pizzaexpress.service.material.IMaterialService;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yerunjie on 2019-03-13
 *
 * @author yerunjie
 */
@Service
public class MaterialServiceImpl extends BaseServiceImpl implements IMaterialService {

  private static final Logger logger = LoggerFactory.getLogger(MaterialServiceImpl.class);

  @Autowired
  private MaterialLogMapper materialLogMapper;

  @Autowired
  private MaterialMapper materialMapper;

  @Override
  @Transactional
  public Material createMaterial(Material material) {
    material.setStatus(MaterialStatus.ONLINE);
    materialMapper.insert(material);
    MaterialLog log = getLogTemplate();
    log.setMaterialId(material.getId());
    log.setFactoryId(0);
    log.setContent("Create");
    materialLogMapper.insert(log);
    return material;
  }

  @Override
  public Material findById(int id) {
    Material material = materialMapper.selectByPrimaryKey(id);
    if (material == null) {
      throw new ResourceNotFoundException();
    }
    return material;
  }

  private MaterialLog getLogTemplate() {
    MaterialLog log = new MaterialLog();
    Token token = getToken();
    if (!token.isAdmin()) {
      throw new AuthenticationFailureException("");
    }
    log.setAdminId(token.getId());
    log.setOperateTime(new Date());
    return log;
  }
}
