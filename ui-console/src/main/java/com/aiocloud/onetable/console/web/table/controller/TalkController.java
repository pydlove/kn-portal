package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.exception.BadRequestException;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.web.data.service.DataService;
import com.aiocloud.onetable.console.web.table.dto.TalkDTO;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import com.aiocloud.onetable.console.web.table.vo.TalkResultVO;
import com.aiocloud.onetable.console.web.table.vo.TalkVO;
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
    @Resource
    private DataService dataService;

    @PostMapping("/question")
    @ResponseBody
    public CommonResponse question(@RequestBody TalkDTO talkDTO) throws Exception {
        if (talkDTO == null){
            return new CommonResponse<>(ErrorCode.PARAMETER_ERROR);
        }
        TalkResultVO talkResultVO = null;
        try {
            TalkVO talkVO = talkService.question(talkDTO.getTableName(), talkDTO.getContent(), talkDTO.getPageSize(), talkDTO.getPageNum());
            talkResultVO = dataService.generateChartData(talkVO);
        } catch (Exception e) {
            if (e instanceof BadRequestException){
                throw e;
            }
            throw new BadRequestException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return new CommonResponse<TalkResultVO>(talkResultVO);
    }

    @PostMapping("/preview")
    @ResponseBody
    public CommonResponse preview(@RequestBody TalkDTO talkDTO) throws Exception {
        if (talkDTO == null){
            return new CommonResponse<>(ErrorCode.PARAMETER_ERROR);
        }
        try {
            TalkVO talkVO = talkService.question(talkDTO.getTableName(), talkDTO.getContent(), talkDTO.getPageSize(), talkDTO.getPageNum());
            return new CommonResponse(talkVO);
        } catch (Exception e) {
            if (e instanceof BadRequestException){
                throw e;
            }
            throw new BadRequestException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
