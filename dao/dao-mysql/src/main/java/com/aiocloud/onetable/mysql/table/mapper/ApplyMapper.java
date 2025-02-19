package com.aiocloud.onetable.mysql.table.mapper;

import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import com.aiocloud.onetable.mysql.test.po.TestInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @description: ApplyMapper.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 17:54
 */
@Mapper
public interface ApplyMapper extends BaseMapper<ApplyPO> {

    int deleteByPrimaryKey(Long id);

    int insert(ApplyPO record);

    int insertSelective(ApplyPO record);

    ApplyPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplyPO record);

    int updateByPrimaryKey(ApplyPO record);

    List<ApplyPO> getAllApplies();

}
