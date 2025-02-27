package com.aiocloud.onetable.console.web.sys.service.impl;

import com.aiocloud.onetable.console.web.sys.enums.AuthCheckEnum;
import com.aiocloud.onetable.console.web.sys.service.TableAuthService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.table.service.ApplyService;
import com.aiocloud.onetable.mysql.table.mapper.TableUserRelMapper;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import com.aiocloud.onetable.mysql.table.po.TableUserRelPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Lazy
    @Resource
    private ApplyService applyService;
    private final UserService userService;
    private final TableUserRelMapper tableUserRelMapper;

    @Override
    public Integer checkAccessAuth(Long tableId) {

        Long currentUserId = userService.getCurrentUserId();
        TableUserRelPO tableUserRel = tableUserRelMapper.selectByUserIdAndTableId(currentUserId, tableId);

        if (Objects.isNull(tableUserRel)) {

            if (applyService.checkSubmitApply(tableId, currentUserId)) {
                return AuthCheckEnum.NO_PERMISSION_APPLIED.getCode();
            } else {
                return AuthCheckEnum.NO_PERMISSION_NOT_APPLY.getCode();
            }

        } else {
            return  AuthCheckEnum.HAS_PERMISSION.getCode();
        }
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
