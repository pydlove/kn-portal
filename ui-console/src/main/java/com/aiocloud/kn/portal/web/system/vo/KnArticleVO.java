package com.aiocloud.kn.portal.web.system.vo;

import com.aiocloud.kn.portal.dao.system.domain.KnArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Data
public class KnArticleVO extends KnArticle {

    private String menuName;
}
