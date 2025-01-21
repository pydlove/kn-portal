package com.aiocloud.onetable.mysql.test.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: TestInfoPO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 17:21 
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_test_info")
@Data
public class TestInfoPO extends BasePO {

    private String testName;
}
