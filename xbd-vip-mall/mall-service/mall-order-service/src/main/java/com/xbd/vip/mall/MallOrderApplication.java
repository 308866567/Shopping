package com.xbd.vip.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = "com.xbd.vip.mall.order.mapper")
@EnableFeignClients(basePackages = {"com.xbd.vip.mall.goods.feign","com.xbd.vip.mall.cart.feign"})
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class,args);
    }
}




