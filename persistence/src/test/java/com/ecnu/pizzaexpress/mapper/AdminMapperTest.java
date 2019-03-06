package com.ecnu.pizzaexpress.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.ecnu.pizzaexpress.BaseMapperTest;
import com.ecnu.pizzaexpress.model.Admin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yerunjie on 2019-03-06
 *
 * @author yerunjie
 */
public class AdminMapperTest extends BaseMapperTest {

  @Autowired
  AdminMapper adminMapper;

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testFindById() {
    Admin admin = adminMapper.findById(1);
    assertNotNull(admin);
    admin = adminMapper.findById(10000);
    assertNull(admin);
  }

  @Test
  public void testFindByAccount() {
    Admin admin = adminMapper.findByAccount("admin");
    assertNotNull(admin);
    admin = adminMapper.findByAccount("abc");
    assertNull(admin);
  }
}