
package com.aiocloud.ruankao.portal.web.ruankao.vo;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkArticle;
import com.aiocloud.kn.portal.dao.system.domain.KnArticle;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class RkArticleVO extends RkArticle {

    private String menuName;
}
