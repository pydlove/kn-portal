package com.aiocloud.kn.portal.dao.system.mapper;

import com.aiocloud.kn.portal.dao.system.domain.KnInterviewQuestions;
import com.aiocloud.kn.portal.dao.system.dto.KnInterviewQuestionsDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description: KnInterviewQuestionsMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:27
 */
@Mapper
public interface KnInterviewQuestionsMapper extends BaseMapper<KnInterviewQuestions> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(KnInterviewQuestions record);

    KnInterviewQuestions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnInterviewQuestions record);

    int updateByPrimaryKey(KnInterviewQuestions record);

    List<KnInterviewQuestionsDTO> selectByTemplateId(@Param("templateId") Long templateId);
}
