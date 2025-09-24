package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamCalendarDate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: RkExamCalendarDateMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 17:41
 */
@Mapper
public interface RkExamCalendarDateMapper extends BaseMapper<RkExamCalendarDate> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkExamCalendarDate record);

    RkExamCalendarDate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkExamCalendarDate record);

    int updateByPrimaryKey(RkExamCalendarDate record);

}
