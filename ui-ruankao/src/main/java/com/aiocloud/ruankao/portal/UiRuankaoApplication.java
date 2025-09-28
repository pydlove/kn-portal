package com.aiocloud.ruankao.portal;

import com.aiocloud.common.bean.BaseBeanContainer;
import com.aiocloud.common.bean.GlobalBeanFactory;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 *
 * @description: UiConsoleApplication.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-20 15:10 
 */
@RequiredArgsConstructor
@MapperScan(basePackages = "com.aiocloud.kn.portal.dao", annotationClass = Mapper.class)
@SpringBootApplication
@ComponentScan(basePackages = {"com.aiocloud"})
public class UiRuankaoApplication {

    private final ApplicationContext applicationContext;

    @PostConstruct
    public void init() throws Exception {
        GlobalBeanFactory.initInstance(BaseBeanContainer.class, applicationContext);
    }

    public static void main(String[] args) {
        SpringApplication.run(UiRuankaoApplication.class, args);
    }
}

