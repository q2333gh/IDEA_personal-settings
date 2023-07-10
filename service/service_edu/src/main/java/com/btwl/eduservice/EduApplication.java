package com.btwl.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient  //nacos注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.btwl"}) //配置扫描,如Swagger2的@Configuration扫描
@MapperScan("com.btwl.eduservice.mapper.*")
public class EduApplication {

  public static void main(String[] args) {

    SpringApplication.run(EduApplication.class, args);
  }
}
