package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description: RkArticleMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-11 20:11
 */
@Mapper
public interface RkArticleMapper extends BaseMapper<RkArticle> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkArticle record);

    RkArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkArticle record);

    int updateByPrimaryKey(RkArticle record);

    List<RkArticle> selectTitleByMenuIds(List<Long> menuIds);

    int selectCountByFulltextSearch(String content);

    List<RkArticle> selectByFulltextSearch(@Param("content") String content, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}
