package com.xbd.vip.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.xbd.vip.mall.search.feign")
public class MallWebSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWebSearchApplication.class, args);
    }
}
