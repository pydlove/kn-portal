package com.aiocloud.ruankao.portal.web.ruankao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: KnArticleVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:11
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RkArticleTitleVO {

    private Long articleId;

    private String title;

    private Long menuId;
}
