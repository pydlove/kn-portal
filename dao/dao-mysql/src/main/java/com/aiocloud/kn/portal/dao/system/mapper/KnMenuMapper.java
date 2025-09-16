package com.aiocloud.kn.portal.dao.system.mapper;

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
public interface KnMenuMapper extends BaseMapper<KnMenu> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(KnMenu record);

    KnMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnMenu record);

    int updateByPrimaryKey(KnMenu record);

    List<KnMenu> selectAll();

    List<KnMenu> selectByParentId(@Param("parentId") Long parentId);

    List<KnMenu> selectRootMenu(@Param("menuIds") Set<Long> menuIds);
}
