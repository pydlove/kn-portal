package com.aiocloud.onetable.mysql.sys.mapper;

import com.aiocloud.onetable.mysql.sys.po.SysMessagePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: SysMessageMapper.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 10:29
 */
@Mapper
public interface SysMessageMapper extends BaseMapper<SysMessagePO> {

    int deleteByPrimaryKey(Long id);

    int insert(SysMessagePO record);

    int insertSelective(SysMessagePO record);

    SysMessagePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMessagePO record);

    int updateByPrimaryKey(SysMessagePO record);

}
