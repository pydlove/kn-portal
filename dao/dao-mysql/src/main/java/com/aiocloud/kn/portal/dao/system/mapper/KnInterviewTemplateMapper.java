package com.aiocloud.kn.portal.dao.system.mapper;

import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description: KnInterviewTemplateMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:25
 */
@Mapper
public interface KnInterviewTemplateMapper extends BaseMapper<KnInterviewTemplate> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(KnInterviewTemplate record);

    KnInterviewTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnInterviewTemplate record);

    int updateByPrimaryKey(KnInterviewTemplate record);

    List<KnInterviewTemplate> selectByLevel(@Param("level") Integer level);
}
