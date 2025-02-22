package com.aiocloud.onetable.mysql.sys.mapper;

import com.aiocloud.onetable.mysql.sys.po.SysRoleMenuRelPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: SysRoleMenuRelMapper.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 22:36 
 */
@Mapper
public interface SysRoleMenuRelMapper extends BaseMapper<SysRoleMenuRelPO> {

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenuRelPO record);

    int insertSelective(SysRoleMenuRelPO record);

    SysRoleMenuRelPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenuRelPO record);

    int updateByPrimaryKey(SysRoleMenuRelPO record);

}
