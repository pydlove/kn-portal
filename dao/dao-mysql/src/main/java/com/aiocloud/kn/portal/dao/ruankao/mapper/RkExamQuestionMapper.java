package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import com.aiocloud.kn.portal.dao.ruankao.dto.RkExamQuestionDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: RkExamQuestionMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:46
 */
@Mapper
public interface RkExamQuestionMapper extends BaseMapper<RkExamQuestion> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamQuestion record);

    RkExamQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkExamQuestion record);

    int updateByPrimaryKey(RkExamQuestion record);

    Page<RkExamQuestionDTO> selectPageByCalendarId(
            Page<RkExamQuestionDTO> page,
            @Param("calendarId") Long calendarId,
            @Param("type") String type,
            @Param("title") String title,
            @Param("difficulty") Integer difficulty);
}
