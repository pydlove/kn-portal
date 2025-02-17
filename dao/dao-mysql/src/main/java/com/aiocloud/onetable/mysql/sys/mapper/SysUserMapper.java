package com.aiocloud.onetable.mysql.sys.mapper;

import com.aiocloud.onetable.mysql.sys.po.SysUserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: SysUserMapper.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-15 17:40 
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPO> {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserPO record);

    int insertSelective(SysUserPO record);

    SysUserPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserPO record);

    int updateByPrimaryKey(SysUserPO record);

    SysUserPO selectByUsername(@Param("username") String username);
}
