package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionArticle;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: RkExamQuestionArticleMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-24 16:43
 */
@Mapper
public interface RkExamQuestionArticleMapper extends BaseMapper<RkExamQuestionArticle> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamQuestionArticle record);

    RkExamQuestionArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkExamQuestionArticle record);

    int updateByPrimaryKey(RkExamQuestionArticle record);

    RkExamQuestionArticle selectByQuestionId(@Param("questionId") Long questionId);

}
