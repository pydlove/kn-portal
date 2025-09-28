package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: CodeEntry.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeEntry {

    private String code;
    private Long expireTime;
}
