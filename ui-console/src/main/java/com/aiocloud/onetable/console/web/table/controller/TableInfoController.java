package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.utils.Result;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther ybin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/table")
public class TableInfoController {

    @Resource
    private TableInfoService tableInfoService;

    @GetMapping("/list")
    @ResponseBody
    public Result selectList(){
        return tableInfoService.selectList();
    }
}
