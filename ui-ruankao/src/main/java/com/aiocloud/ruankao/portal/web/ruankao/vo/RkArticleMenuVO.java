package com.aiocloud.ruankao.portal.web.ruankao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class RkArticleMenuVO {

    private Long menuId;

    private String menuName;

    private List<RkArticleTitleVO> articleTitleList;
}
