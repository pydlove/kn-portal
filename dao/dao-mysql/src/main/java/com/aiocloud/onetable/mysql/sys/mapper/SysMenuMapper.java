package com.aiocloud.onetable.mysql.sys.mapper;

import com.aiocloud.onetable.mysql.sys.po.SysMenuPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description: SysMenuMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 22:36
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuPO> {

    int deleteByPrimaryKey(Long id);

    int insert(SysMenuPO record);

    int insertSelective(SysMenuPO record);

    SysMenuPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenuPO record);

    int updateByPrimaryKey(SysMenuPO record);

    List<SysMenuPO> selectByUserName(@Param("userName") String userName);
}
