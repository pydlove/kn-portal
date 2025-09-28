package com.aiocloud.ruankao.portal.web.ruankao.vo;

import lombok.Data;

/**
 *
 * @description: UserRegisterVO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-27 11:33 
 */
@Data
public class UserRegisterVO {

    private String username;
    private String password;
    private String confirmPassword;
    private String verificationKey;
    private String verificationCode;
}
