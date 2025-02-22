package com.aiocloud.onetable.console.web.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.sys.dto.MessageDTO;
import com.aiocloud.onetable.console.web.sys.enums.DeleteFlagEnum;
import com.aiocloud.onetable.console.web.sys.enums.MessageTypeEnum;
import com.aiocloud.onetable.console.web.sys.enums.MessageOpStatusEnum;
import com.aiocloud.onetable.console.web.sys.service.MessageService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.MessageVO;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import com.aiocloud.onetable.console.web.table.dto.BatchApplyDTO;
import com.aiocloud.onetable.console.web.table.enums.ApplyStatusEnum;
import com.aiocloud.onetable.mysql.sys.mapper.SysMessageMapper;
import com.aiocloud.onetable.mysql.sys.po.SysMessagePO;
import com.aiocloud.onetable.mysql.sys.po.SysUserPO;
import com.aiocloud.onetable.mysql.table.mapper.ApplyMapper;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @description: MessageService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-21 15:24 
 */
@RequiredArgsConstructor
@Service
public class MessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessagePO> implements MessageService {

    private final UserService userService;
    private final SysMessageMapper sysMessageMapper;
    private final ApplyMapper applyMapper;

    private static String applyMessageFormat = "您的申请已经审批，申请结果：%s";

    @Override
    public Integer readMessage(List<Long> ids) {

        for (Long id : ids) {
            SysMessagePO sysMessagePO = new SysMessagePO();
            sysMessagePO.setId(id);
            sysMessagePO.setOpStatus(MessageOpStatusEnum.READ.getCode());
            sysMessageMapper.updateByPrimaryKeySelective(sysMessagePO);
        }

        return null;
    }

    @Override
    public PaginationResult<MessageVO> getMessagePage(MessageDTO messageDTO, PageRequest pageRequest) {

        Page<SysMessagePO> applyPage = new Page<>(pageRequest.getPage(), pageRequest.getRows());
        QueryWrapper<SysMessagePO> queryWrapper = new QueryWrapper<>();

        if (StrUtil.isNotEmpty(messageDTO.getContent())) {
            queryWrapper.lambda().eq(SysMessagePO::getContent, messageDTO.getContent());
        }

        Long currentUserId = userService.getCurrentUserId();
        queryWrapper.lambda().eq(SysMessagePO::getToUserId, currentUserId);
        queryWrapper.lambda().eq(SysMessagePO::getOpStatus, MessageOpStatusEnum.UNREAD.getCode());

        queryWrapper.lambda().eq(SysMessagePO::getDeleteFlag, DeleteFlagEnum.UNDELETE.getCode());
        queryWrapper.lambda().orderByDesc(SysMessagePO::getCreateTime);

        Page<SysMessagePO> result = sysMessageMapper.selectPage(applyPage, queryWrapper);
        List<SysMessagePO> records = Optional.ofNullable(result.getRecords()).orElse(new ArrayList<>());
        List<MessageVO> messages = BeanUtil.copyToList(records, MessageVO.class);

        messages.forEach(vo -> {
            MessageOpStatusEnum opStatusEnum = MessageOpStatusEnum.fromCode(vo.getOpStatus());
            vo.setChOpStatus(null != opStatusEnum ? opStatusEnum.getMessage() : null);
        });

        return new PaginationResult<>(result.getTotal(), messages);
    }

    @Override
    public void submitApplyMessage(ApplyPO applyPO) {

        Long currentUserId = userService.getCurrentUserId();
        sendApplyMessage(applyPO, currentUserId);
    }

    /**
     * sendApplyMessage
     *
     * @since 1.0.0
     *
     * @param: currentUserId
     * @return: void
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-02-21 14:03 
     */
    private void sendApplyMessage(ApplyPO applyPO, Long currentUserId) {

        Long userId = applyPO.getUserId();

        ApplyStatusEnum applyStatusEnum = ApplyStatusEnum.fromCode(applyPO.getApplyStatus());
        String description = applyStatusEnum == null ? "" : applyStatusEnum.getDescription();
        String message = applyMessageFormat.formatted(description);

        SysMessagePO sysMessagePO = new SysMessagePO();
        sysMessagePO.setContent(message);
        sysMessagePO.setMsgType(MessageTypeEnum.APPLY.getCode());
        sysMessagePO.setOpStatus(MessageOpStatusEnum.UNREAD.getCode());
        sysMessagePO.setToUserId(userId);
        sysMessagePO.setCreateUid(currentUserId);
        sysMessagePO.setUpdateUid(currentUserId);
        sysMessageMapper.insertSelective(sysMessagePO);
    }

}
