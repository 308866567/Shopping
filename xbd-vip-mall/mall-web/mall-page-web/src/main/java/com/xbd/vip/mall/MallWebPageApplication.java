package com.xbd.vip.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.xbd.vip.mall.goods.feign","com.xbd.vip.mall.seckill.feign"})
public class MallWebPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWebPageApplication.class,args);
    }
}
