package com.ecnu.pizzaexpress.service.deliver.impl.baidumap.configuration;

import com.ecnu.pizzaexpress.service.deliver.impl.baidumap.BaiduMapApi;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yerunjie on 2019-03-27
 *
 * @author yerunjie
 */
@Configuration
public class BaiduMapApiConfig implements RequestInterceptor {

  @Value("${baidumap.ak}")
  private String ak;

  @Bean
  public BaiduMapApi baiduMapApi() {
    return Feign.builder()
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .contract(new SpringMvcContract())
        .requestInterceptor(this).target(BaiduMapApi.class, "http://api.map.baidu.com");

  }

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.query("ak", ak);
    requestTemplate.query("output", "json");
  }
}
