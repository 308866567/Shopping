package com.xbd.vip.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xbd.vip.mall.mapper"})
public class MallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserApplication.class,args);
    }
}