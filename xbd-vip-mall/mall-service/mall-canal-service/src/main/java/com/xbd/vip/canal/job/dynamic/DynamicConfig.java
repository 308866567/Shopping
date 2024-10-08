package com.xbd.vip.canal.job.dynamic;


import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicConfig {

    /**
     * 指定当前注册地址信息
     */

    @Value("${dynamiczk}")
    private String dynamiczk;
    @Value("${dynamicnamespace}")
    private String dynamicnamespace;
    @Bean
    public ZookeeperConfiguration zookeeperConfiguration(){
        return new ZookeeperConfiguration(dynamiczk,dynamicnamespace);
    }
    /**
     * 向zookeeper服务注册
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(ZookeeperConfiguration zookeeperConfiguration){
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }
}
