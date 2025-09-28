package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkUserCalendarRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: RkUserCalendarRelMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-28 8:57
 */
@Mapper
public interface RkUserCalendarRelMapper extends BaseMapper<RkUserCalendarRel> {

    int deleteByPrimaryKey(Long id);

    int insertSelective(RkUserCalendarRel record);

    RkUserCalendarRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkUserCalendarRel record);

    int updateByPrimaryKey(RkUserCalendarRel record);

}
