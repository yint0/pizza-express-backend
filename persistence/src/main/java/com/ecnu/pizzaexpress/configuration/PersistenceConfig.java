package com.ecnu.pizzaexpress.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.ecnu.pizzaexpress.mapper")
@EnableTransactionManagement
public class PersistenceConfig {

}