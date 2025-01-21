package com.aiocloud.onetable.console;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @description: UiConsoleApplication.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-20 15:10 
 */
@MapperScan(basePackages = "com.aiocloud.onetable.mysql", annotationClass = Mapper.class)
@SpringBootApplication
public class UiConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiConsoleApplication.class, args);
    }
}

