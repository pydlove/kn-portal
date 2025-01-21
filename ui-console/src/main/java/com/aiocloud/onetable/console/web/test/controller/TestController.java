package com.aiocloud.onetable.console.web.test.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.web.test.service.TestService;
import com.aiocloud.onetable.console.web.test.vo.TestInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @description: TestController.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 15:57 
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @GetMapping("/example")
    public CommonResponse<String> example() {
        return new CommonResponse<>("Example interface, Will be deleted before going online");
    }

    @GetMapping("/select")
    public CommonResponse<TestInfoVO> selectExample() {

        return new CommonResponse<>(testService.getOneTest());
    }
}
