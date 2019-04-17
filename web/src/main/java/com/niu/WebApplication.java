package com.niu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 多模块项目一定要注意主类的位置，不然会导致bean无法被扫描到
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.niu.dao.mapper"})
public class WebApplication {

  public static void main(String[] args) {

    SpringApplication.run(WebApplication.class, args);
  }

}
