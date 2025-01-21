package com.aiocloud.onetable.console.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @description: JwtTokenProperties.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 14:03 
 */
@Data
@Component
@ConfigurationProperties(prefix = "system.jwt")
public class JwtTokenProperties {

    private String issuer;
    private String audience;
}
