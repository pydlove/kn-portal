package com.aiocloud.ruankao.portal.config.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @description: PermissionProperties.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-15 9:45
 */
@Data
@Component
@ConfigurationProperties(prefix = "permission")
public class PermissionProperties {

    /**
     * 权限验证开关
     */
    private boolean enabled = true;

    /**
     * 验证token值
     */
    private String token = "default-secret-token";
}
