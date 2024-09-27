package com.xbd.vip.canal.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext act;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act=applicationContext;
    }
    //获取指定对象
    public static <T> T getBean(Class<T> clazz) {
        return act.getBean(clazz);
    }
}
