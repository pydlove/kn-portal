package com.aiocloud.onetable.mysql.sys.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: SysRoleMenuRel.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 22:36 
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_role_menu_rel")
@Data
public class SysRoleMenuRelPO extends BasePO {

    private String menuCode;

    private Long roleId;

}