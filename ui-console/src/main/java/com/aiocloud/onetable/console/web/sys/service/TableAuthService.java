package com.aiocloud.onetable.console.web.sys.service;

import com.aiocloud.onetable.mysql.table.po.ApplyPO;

/**
 *
 * @description: TableAuthService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 14:33 
 */
public interface TableAuthService {
    Boolean checkAccessAuth(Long tableId);

    void addAccessAuth(ApplyPO apply);
}
