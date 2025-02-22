package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.console.utils.Result;

/**
 * @auther ybin
 */
public interface TalkService {

    Result question(String tableName, String content);
}