package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkMenu;
import com.aiocloud.kn.portal.dao.system.domain.KnMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 *
 * @description: KnMenuMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:35
 */
@Mapper
public interface RkMenuMapper extends BaseMapper<RkMenu> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkMenu record);

    RkMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkMenu record);

    int updateByPrimaryKey(RkMenu record);

    List<RkMenu> selectAll();

    List<RkMenu> selectByParentId(@Param("parentId") Long parentId);

    List<RkMenu> selectRootMenu(@Param("menuIds") Set<Long> menuIds);
}
