package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.web.table.service.ApplyService;
import com.aiocloud.onetable.mysql.table.mapper.ApplyMapper;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @description: ApplyServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 14:51 
 */
@RequiredArgsConstructor
@Service
public class ApplyServiceImpl implements ApplyService {

    private final ApplyMapper applyMapper;

    @Override
    public int submitApply(ApplyPO applyPO) {
        return applyMapper.insertSelective(applyPO);
    }

    @Override
    public List<ApplyPO> getAllApplies() {
        return applyMapper.getAllApplies();
    }
}