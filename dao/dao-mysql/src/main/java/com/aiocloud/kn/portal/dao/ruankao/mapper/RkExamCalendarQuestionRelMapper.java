package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamCalendarQuestionRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description:
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 18:56
 */
@Mapper
public interface RkExamCalendarQuestionRelMapper extends BaseMapper<RkExamCalendarQuestionRel> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamCalendarQuestionRel record);

    RkExamCalendarQuestionRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkExamCalendarQuestionRel record);

    int updateByPrimaryKey(RkExamCalendarQuestionRel record);

    int deleteByCalendarDateId(@Param("calendarId") Long calendarId);

    int insertBatch(@Param("relations") List<RkExamCalendarQuestionRel> relations);

    List<RkExamCalendarQuestionRel> selectByCalendarId(Long calendarId);
}
