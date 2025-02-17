package com.aiocloud.onetable.console.web.login.dto;

import lombok.Data;

/**
 *
 * @description: LoginDTO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-15 17:23 
 */
@Data
public class LoginDTO {

    private String username;
    private String userPwd;
    private String randomId;
}
