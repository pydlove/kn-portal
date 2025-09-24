package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: RkExamQuestionCaseMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:42
 */
@Mapper
public interface RkExamQuestionCaseMapper extends BaseMapper<RkExamQuestionCase> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamQuestionCase record);

    RkExamQuestionCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkExamQuestionCase record);

    int updateByPrimaryKey(RkExamQuestionCase record);

    RkExamQuestionCase selectByQuestionId(@Param("questionId") Long questionId);

}
