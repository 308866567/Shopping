package com.xbd.vip.mall.file.ceph;

import lombok.Data;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ceph")
public class ContainerConfig {
    private String username;
    private String password;
    private String authUrl;
    private String defaultContainerName;


    /***
     * Ceph的账户信息配置
     * @return
     */
    @Bean
    public Account account() {
        AccountConfig config = new AccountConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setAuthUrl(authUrl);
        config.setAuthenticationMethod(AuthenticationMethod.BASIC);
        return new AccountFactory(config).createAccount();
    }
    /***
     * 容器对象
     * @return
     */
    @Bean
    public Container container() {
        Container newContainer = account().getContainer(defaultContainerName);
        if (!newContainer.exists()) {
            return newContainer.create();
        } else {
            return newContainer;
        }
    }
}
