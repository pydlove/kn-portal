package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @description: RkUser.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:27
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_user")
@Data
public class RkUser extends BaseDomain {

    private String username;

    private String userPwd;

    private String salt;

    private Date lastLoginTime;

    private String avatar;

    private Integer userStatus;
}