package com.aiocloud.onetable.console.web.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.config.security.JwtTokenGenerator;
import com.aiocloud.onetable.console.config.security.JwtTokenProperties;
import com.aiocloud.onetable.console.constant.SystemConstant;
import com.aiocloud.onetable.console.web.sys.UserPwdTool;
import com.aiocloud.onetable.console.web.sys.dto.LoginDTO;
import com.aiocloud.onetable.console.web.sys.dto.MessageDTO;
import com.aiocloud.onetable.console.web.sys.service.MessageService;
import com.aiocloud.onetable.console.web.sys.service.UserService;
import com.aiocloud.onetable.console.web.sys.vo.MessageVO;
import com.aiocloud.onetable.console.web.sys.vo.UserInfoVO;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @description: MessageController.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-16 15:47 
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/read")
    public CommonResponse<Integer> readMessage(@RequestBody MessageDTO messageDTO) {

        List<Long> ids = messageDTO.getIds();

        return new CommonResponse<>(messageService.readMessage(ids));
    }

    @GetMapping("/page")
    public CommonResponse<PaginationResult<MessageVO>> getMessagePage(MessageDTO messageDTO, PageRequest pageRequest) {

        return new CommonResponse<>(messageService.getMessagePage(messageDTO, pageRequest));
    }
}
