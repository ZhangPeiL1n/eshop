package com.zpl.eshop.common.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring容器组件
 *
 * @author ZhangPeiL1n
 * @date 2022/7/5 23:17
 **/
@Component
public class SpringApplicationContext {

    private final ApplicationContext context;

    /**
     * 构造函数
     *
     * @param context spring容器
     */
    @Autowired
    public SpringApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    /**
     * 获取bean
     *
     * @param clazz bean 类型
     * @param <T>
     * @return bean实例
     */
    public <T> T getBean(Class<? extends T> clazz) {
        return context.getBean(clazz);
    }
}
