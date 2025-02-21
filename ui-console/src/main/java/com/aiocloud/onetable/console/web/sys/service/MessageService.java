package com.aiocloud.onetable.console.web.sys.service;

import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.sys.dto.MessageDTO;
import com.aiocloud.onetable.console.web.sys.vo.MessageVO;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import com.aiocloud.onetable.console.web.table.dto.BatchApplyDTO;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;

import java.util.List;

/**
 *
 * @description: MessageService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 10:34 
 */
public interface MessageService {

    Integer readMessage(List<Long> ids);

    PaginationResult<MessageVO> getMessagePage(MessageDTO messageDTO, PageRequest pageRequest);

    void submitApplyMessage(ApplyPO applyPO);
}
