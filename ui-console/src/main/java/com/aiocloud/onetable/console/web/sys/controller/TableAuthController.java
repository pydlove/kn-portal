package com.aiocloud.onetable.console.web.sys.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.sys.dto.MessageDTO;
import com.aiocloud.onetable.console.web.sys.service.MessageService;
import com.aiocloud.onetable.console.web.sys.service.TableAuthService;
import com.aiocloud.onetable.console.web.sys.vo.MessageVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/auth")
public class TableAuthController {

    private final TableAuthService tableAuthService;

    @GetMapping("/check")
    public CommonResponse<Boolean> checkAccessAuth(@RequestParam("tableId") Long tableId ) {

        return new CommonResponse<>(tableAuthService.checkAccessAuth(tableId));
    }

}
