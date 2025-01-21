package com.aiocloud.onetable.console.web.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiocloud.onetable.console.web.test.service.TestService;
import com.aiocloud.onetable.console.web.test.vo.TestInfoVO;
import com.aiocloud.onetable.mysql.test.mapper.TestMapper;
import com.aiocloud.onetable.mysql.test.po.TestInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @description: TestServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 16:36 
 */
@RequiredArgsConstructor
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestInfoPO> implements TestService {

    private final TestMapper testMapper;

    @Override
    public TestInfoVO getOneTest() {

        QueryWrapper<TestInfoPO> wrapper = new QueryWrapper<>();
        TestInfoPO testInfoPO = testMapper.selectOne(wrapper);

        return BeanUtil.copyProperties(testInfoPO, TestInfoVO.class);
    }
}
