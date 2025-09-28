package com.aiocloud.kn.portal.dao.ruankao.mapper;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: RkUserMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 11:27
 */
@Mapper
public interface RkUserMapper extends BaseMapper<RkUser> {

    int deleteByPrimaryKey(Long id);

    int insert(RkUser record);

    int insertSelective(RkUser record);

    RkUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RkUser record);

    int updateByPrimaryKey(RkUser record);

}
