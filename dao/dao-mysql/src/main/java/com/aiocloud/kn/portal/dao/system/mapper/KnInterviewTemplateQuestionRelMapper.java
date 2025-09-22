package com.aiocloud.kn.portal.dao.system.mapper;

import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplateQuestionRel;
import com.aiocloud.kn.portal.dao.system.dto.KnInterviewTemplateQuestionRelDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description: KnInterviewTemplateQuestionRelMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 22:20
 */
@Mapper
public interface KnInterviewTemplateQuestionRelMapper extends BaseMapper<KnInterviewTemplateQuestionRel> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(KnInterviewTemplateQuestionRel record);

    KnInterviewTemplateQuestionRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnInterviewTemplateQuestionRel record);

    int updateByPrimaryKey(KnInterviewTemplateQuestionRel record);

    int insertBatch(List<KnInterviewTemplateQuestionRel> entities);

    List<KnInterviewTemplateQuestionRelDTO> selectRelWithQuestionTitle(Long templateId);

    int deleteByTemplateId(@Param("templateId") Long templateId);
}
