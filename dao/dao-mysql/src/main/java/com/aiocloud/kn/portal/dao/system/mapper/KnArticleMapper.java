package com.aiocloud.kn.portal.dao.system.mapper;

import com.aiocloud.kn.portal.dao.system.domain.KnArticle;
import com.aiocloud.kn.portal.dao.system.domain.KnMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @description: KnArticleMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:11
 */
@Mapper
public interface KnArticleMapper extends BaseMapper<KnArticle> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(KnArticle record);

    KnArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnArticle record);

    int updateByPrimaryKey(KnArticle record);

    List<KnArticle> selectTitleByMenuIds(List<Long> menuIds);
}
