package com.aiocloud.kn.portal.web.system.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: LoginVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-15 10:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginVO {

    private String username;

    private Boolean success;

    private String  token;
}
