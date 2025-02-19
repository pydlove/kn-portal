package com.aiocloud.onetable.console;

import com.aiocloud.onetable.console.bean.BaseBeanContainer;
import com.aiocloud.onetable.console.bean.GlobalBeanFactory;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
@MapperScan(basePackages = "com.aiocloud.onetable.mysql", annotationClass = Mapper.class)
@SpringBootApplication
public class UiConsoleApplication {

    private final ApplicationContext applicationContext;

    @PostConstruct
    public void init() throws Exception {
        GlobalBeanFactory.initInstance(BaseBeanContainer.class, applicationContext);
    }

    public static void main(String[] args) {
        SpringApplication.run(UiConsoleApplication.class, args);
    }
}

