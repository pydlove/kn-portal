package com.aiocloud.ruankao.portal.web.ruankao.vo;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkUser;
import lombok.Data;

/**
 *
 * @description: UserLoginResponseVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:38
 */
@Data
public class UserLoginResponseVO {

    private boolean success;
    private String token;
    private String message;
    private RkUser user;
}
