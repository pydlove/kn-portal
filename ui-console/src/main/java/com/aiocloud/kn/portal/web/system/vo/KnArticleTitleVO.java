package com.aiocloud.kn.portal.web.system.vo;

import com.aiocloud.kn.portal.dao.system.domain.KnArticle;
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
public class KnArticleTitleVO {

    private Long articleId;

    private String title;

    private Long menuId;
}
