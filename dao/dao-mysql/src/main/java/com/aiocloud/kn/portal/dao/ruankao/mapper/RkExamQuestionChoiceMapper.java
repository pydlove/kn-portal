package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: RkExamQuestionChoiceMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:45
 */
@Mapper
public interface RkExamQuestionChoiceMapper extends BaseMapper<RkExamQuestionChoice> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamQuestionChoice record);

    RkExamQuestionChoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkExamQuestionChoice record);

    int updateByPrimaryKey(RkExamQuestionChoice record);

    RkExamQuestionChoice selectByQuestionId(@Param("questionId") Long questionId);

}
