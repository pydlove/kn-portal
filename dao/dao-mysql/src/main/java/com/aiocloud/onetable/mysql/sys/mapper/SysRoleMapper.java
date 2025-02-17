package com.aiocloud.onetable.mysql.sys.mapper;

import com.aiocloud.onetable.mysql.sys.po.SysRolePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: SysRoleMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-15 18:12
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRolePO> {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePO record);

    int insertSelective(SysRolePO record);

    SysRolePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePO record);

    int updateByPrimaryKey(SysRolePO record);

}
