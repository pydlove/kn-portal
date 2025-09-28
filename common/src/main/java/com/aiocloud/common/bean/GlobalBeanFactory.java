package com.aiocloud.common.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;

/**
 * @description: GlobalBeanFactory
 * @copyright: @Copyright (c) 2022
 * @company: Secsmart
 * @author: panyong
 * @version: 3.3.4
 * @createTime: 2024/3/4 17:14
 */
@Slf4j
public class GlobalBeanFactory {

    private static volatile GlobalBeanFactory instance;

    private GlobalBeanFactory(Class<? extends BaseBeanContainer> clazz, ApplicationContext applicationContext) throws Exception {

        BaseBeanContainer baseBeanContainer = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        initBeanValue(baseBeanContainer, applicationContext, fields);

        Class<?> superclass = clazz.getSuperclass();
        Field[] superFields = superclass.getDeclaredFields();
        initBeanValue(baseBeanContainer, applicationContext, superFields);
    }

    private void initBeanValue(BaseBeanContainer baseBeanContainer, ApplicationContext applicationContext, Field[] fields) throws IllegalAccessException {

        for (Field field : fields) {
            field.setAccessible(true);
            Object bean = getBean(applicationContext, field);
            field.set(baseBeanContainer, bean);
        }
    }

    /**
     * getBean
     * @since 3.3.4
     *
     * @return: java.lang.Object
     * @author: panyong
     * @version: 3.3.4
     * @createTime: 2024/5/17 15:49 
     */
    private Object getBean(ApplicationContext applicationContext, Field field) {
        Object bean;
        try {
            bean = applicationContext.getBean(field.getName());
        } catch (NoSuchBeanDefinitionException ex) {
            bean = getBeanByType(applicationContext, field);
        }

        return bean;
    }

    /**
     * getBeanByType
     * @since 3.3.4
     *
     * @return: java.lang.Object
     * @author: panyong
     * @version: 3.3.4
     * @createTime: 2024/5/17 15:54 
     */
    private Object getBeanByType(ApplicationContext applicationContext, Field field) {
        Object bean;
        try {
            Class<?> type = field.getType();
            bean = applicationContext.getBean(type);
        } catch (NoSuchBeanDefinitionException ex) {

            throw new NullPointerException("bean: " + field.getName() + " not found, please check !");
        }

        return bean;
    }

    public static GlobalBeanFactory getInstance(BaseBeanContainer baseBeanContainer, ApplicationContext applicationContext) throws Exception {
        initInstance(BaseBeanContainer.class, applicationContext);
        return instance;
    }

    public static void initInstance(Class<? extends BaseBeanContainer> clazz, ApplicationContext applicationContext) throws Exception {
        if (instance == null) {
            synchronized (GlobalBeanFactory.class) {
                if (instance == null) {
                    instance = new GlobalBeanFactory(clazz, applicationContext);
                }
            }
        }
    }
}
