package com.aiocloud.onetable.mysql.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @description: MapperConfig.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 17:05 
 */
@Configuration
@MapperScan(basePackages = "com.aiocloud.onetable.mysql", annotationClass = Mapper.class)
public class MapperConfiguration {
}
