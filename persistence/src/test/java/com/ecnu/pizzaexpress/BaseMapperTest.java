package com.ecnu.pizzaexpress;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yerunjie on 2019-03-06
 *
 * @author yerunjie
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BaseMapperTest {

  @SpringBootApplication
  @MapperScan("com.ecnu.pizzaexpress.mapper")
  public static class Application {

    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }
  }
}
