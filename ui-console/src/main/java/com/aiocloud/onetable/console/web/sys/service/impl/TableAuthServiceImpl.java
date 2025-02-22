package com.aiocloud.onetable.console.web.sys.service.impl;

import com.aiocloud.onetable.console.web.sys.service.TableAuthService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.mysql.table.mapper.TableUserRelMapper;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import com.aiocloud.onetable.mysql.table.po.TableUserRelPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 * @description: TableAuthServiceImpl.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 14:34 
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TableAuthServiceImpl implements TableAuthService {

    private final UserService userService;
    private final TableUserRelMapper tableUserRelMapper;

    @Override
    public Boolean checkAccessAuth(Long tableId) {

        Long currentUserId = userService.getCurrentUserId();
        TableUserRelPO tableUserRel = tableUserRelMapper.selectByUserIdAndTableId(currentUserId, tableId);
        return Objects.nonNull(tableUserRel);
    }

    @Override
    public void addAccessAuth(ApplyPO apply) {

        Long currentUserId = userService.getCurrentUserId();

        Long userId = apply.getUserId();
        Long tableId = apply.getTableId();
        TableUserRelPO tableUserRel = tableUserRelMapper.selectByUserIdAndTableId(userId, tableId);
        if (Objects.nonNull(tableUserRel)) {
            return;
        }

        TableUserRelPO tableUserRelPO = new TableUserRelPO();
        tableUserRelPO.setUserId(userId);
        tableUserRelPO.setTableId(tableId);
        tableUserRelPO.setCreateUid(currentUserId);
        tableUserRelPO.setUpdateUid(currentUserId);
        tableUserRelMapper.insertSelective(tableUserRelPO);
    }
}
