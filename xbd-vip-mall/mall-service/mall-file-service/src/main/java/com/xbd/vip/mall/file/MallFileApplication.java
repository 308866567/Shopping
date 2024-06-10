package com.xbd.vip.mall.file;


import com.xbd.vip.mall.file.ceph.FileHandler;
import com.xbd.vip.mall.file.controller.FileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallFileApplication.class);

    }
}
