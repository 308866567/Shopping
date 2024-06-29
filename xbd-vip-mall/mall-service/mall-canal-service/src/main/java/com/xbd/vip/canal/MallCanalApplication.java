package com.xbd.vip.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//关闭数据库自动下载
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCanalApplication.class,args);
    }
}
