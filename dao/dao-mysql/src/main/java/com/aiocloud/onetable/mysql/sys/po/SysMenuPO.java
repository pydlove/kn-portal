package com.aiocloud.onetable.mysql.sys.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: SysMenu.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 22:39
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_menu")
@Data
public class SysMenuPO extends BasePO {

    private String menuCode;

    private String menuName;

    private String menuUrl;

    private String menuRemark;

    private Integer menuType;

    private Long parentId;
}