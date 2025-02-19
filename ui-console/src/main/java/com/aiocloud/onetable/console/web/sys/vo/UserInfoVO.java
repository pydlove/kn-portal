package com.aiocloud.onetable.console.web.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: UserInfoVO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-15 17:44
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoVO {

    private String token;
    private Long userId;
    private String username;
}
