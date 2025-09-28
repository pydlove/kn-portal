package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: KnMenu.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:10
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_menu")
@Data
public class RkMenu extends BaseDomain {

    private String menuName;

    private String menuDesc;

    private String menuUrl;

    private Long parentId;

    private Integer menuLevel;

    private Integer orderNo;

    private Integer enabledStatus;
}