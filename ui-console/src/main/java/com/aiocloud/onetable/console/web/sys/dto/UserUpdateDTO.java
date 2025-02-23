package com.aiocloud.onetable.console.web.sys.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @description: UserAddDTO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-22 21:35
 */
@Data
public class UserUpdateDTO {

    private Long userId;
    private String userName;
    private String userPwd;
    private Long roleId;
    private List<Long> tableIds;
    private String randomId;
}
