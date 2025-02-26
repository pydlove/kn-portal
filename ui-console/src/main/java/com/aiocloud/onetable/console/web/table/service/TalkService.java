package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.utils.Result;

/**
 * @auther ybin
 */
public interface TalkService {

    CommonResponse question(String tableName, String content, Integer pageSize, Integer pageNum);
}