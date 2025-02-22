package com.aiocloud.onetable.console.web.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: MenuVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 22:40
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuVO {

    private String menuCode;
    private String menuName;
    private String menuUrl;
}
