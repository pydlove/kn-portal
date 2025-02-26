package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.console.web.table.vo.TableInfoVO;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther ybin
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/table")
public class TableInfoController {

    @Resource
    private TableInfoService tableInfoService;

    @GetMapping("/tableInfo")
    @ResponseBody
    public CommonResponse tableInfo(TableInfoPO tableInfoPO) {
        return new CommonResponse(tableInfoService.tableInfo(tableInfoPO));
    }

    @GetMapping("/all")
    public CommonResponse<List<TableInfoVO>> getAllTables() {
        return new CommonResponse<>(tableInfoService.getAllTables());
    }
}
