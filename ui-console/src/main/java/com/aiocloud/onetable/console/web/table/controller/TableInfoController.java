package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.utils.Result;
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

    @GetMapping("/list")
    public Result selectList(TableInfoPO tableInfoPO) {
        try {
            return Result.success("", tableInfoService.selectList(tableInfoPO));
        } catch (Exception e) {
            log.error("查询表配置信息异常", e);
            return Result.fail(ErrorCode.INTERNAL_SERVER_ERROR.getMsg(), e);
        }
    }

    @GetMapping("/all")
    public CommonResponse<List<TableInfoVO>> getAllTables() {
        return new CommonResponse<>(tableInfoService.getAllTables());
    }
}
