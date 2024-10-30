package com.xbd.vip.mall.dw;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//mapper层
@MapperScan(basePackages = {"com.xbd.vip.mall.dw.mapper"})
@EnableCaching
public class DwApplication {
    public static void main(String[] args) {
        SpringApplication.run(DwApplication.class,args);
    }
}
