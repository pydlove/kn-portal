package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: RkExamQuestionEssayMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:45
 */
@Mapper
public interface RkExamQuestionEssayMapper extends BaseMapper<RkExamQuestionEssay> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamQuestionEssay record);

    RkExamQuestionEssay selectByPrimaryKey(Long id);

    int updateByQuestionId(RkExamQuestionEssay record);

    int updateByPrimaryKey(RkExamQuestionEssay record);

    RkExamQuestionEssay selectByQuestionId(@Param("questionId") Long questionId);
}
