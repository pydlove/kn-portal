package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.utils.Result;
import com.aiocloud.onetable.console.web.table.dto.TalkDTO;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @auther ybin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/talk")
public class TalkController {

    @Resource
    private TalkService talkService;

    @PostMapping("/question")
    @ResponseBody
    public CommonResponse question(@RequestBody TalkDTO talkDTO){
        if (talkDTO == null){
            return new CommonResponse<>(ErrorCode.PARAMETER_ERROR);
        }
        return talkService.question(talkDTO.getTableName(), talkDTO.getContent(), talkDTO.getPageSize(), talkDTO.getPageNum());
    }
}
