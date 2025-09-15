package com.aiocloud.kn.portal.dao.system.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
@TableName(value ="kn_menu")
@Data
public class KnMenu extends BaseDomain {

    private String menuName;

    private String menuDesc;

    private String menuUrl;

    private Long parentId;

    private Integer menuLevel;

    private Integer orderNo;

    private Integer enabledStatus;
}