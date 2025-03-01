package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.console.web.table.vo.TalkVO;

/**
 * @auther ybin
 */
public interface TalkService {

    TalkVO question(String tableName, String content, Integer pageSize, Integer pageNum) throws Exception;
}