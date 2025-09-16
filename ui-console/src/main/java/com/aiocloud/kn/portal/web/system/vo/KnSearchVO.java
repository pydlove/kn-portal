package com.aiocloud.kn.portal.web.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: KnSearchVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-16 17:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KnSearchVO {

    private Long id;
    private Long rootMenuId;
    private Long menuId;
    private String title;
    private String content;
}
