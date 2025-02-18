package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.mysql.table.po.ApplyPO;

import java.util.List;

/**
 *
 * @description: ApplyService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 14:51 
 */
public interface ApplyService {

    int submitApply(ApplyPO applyPO);

    List<ApplyPO> getAllApplies();
}