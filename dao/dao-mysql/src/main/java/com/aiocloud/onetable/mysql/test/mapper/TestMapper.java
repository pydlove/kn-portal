package com.aiocloud.onetable.mysql.test.mapper;

import com.aiocloud.onetable.mysql.test.po.TestInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description: TestMapper.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 17:20 
 */
@Mapper
public interface TestMapper extends BaseMapper<TestInfoPO> {
}

