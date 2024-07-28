package com.xbd.vip.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//关闭数据库自动下载
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = {
        "com.xbd.vip.mall.page.feign",
        "com.xbd.vip.mall.search.feign",
        "com.xbd.vip.mall.goods.feign",
        "com.xbd.vip.mall.*.feign"})
//@ComponentScan(basePackages = "com.xbd.vip.mall.config")
public class MallCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCanalApplication.class, args);
    }
}
