package com.aiocloud.onetable.console.web.test.service;

import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.test.dto.TestInfoDTO;
import com.aiocloud.onetable.console.web.test.vo.TestInfoVO;
import com.aiocloud.onetable.mysql.test.po.TestInfoPO;

/**
 *
 * @description: TestService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 17:04
 */
public interface TestService {

    TestInfoVO getOneTest();

    PaginationResult<TestInfoVO> selectPageExample(TestInfoDTO testInfoDTO, PageRequest pageRequest);

}
