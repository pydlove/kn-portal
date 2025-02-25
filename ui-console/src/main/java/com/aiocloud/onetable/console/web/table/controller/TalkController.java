package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.web.data.service.DataService;
import com.aiocloud.onetable.console.web.table.dto.TalkDTO;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import com.aiocloud.onetable.console.web.table.vo.BarVO;
import com.aiocloud.onetable.console.web.table.vo.ColumnInfoVo;
import com.aiocloud.onetable.console.web.table.vo.TalkResultVO;
import com.aiocloud.onetable.console.web.table.vo.TalkVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public CommonResponse<TalkResultVO> question(@RequestBody TalkDTO talkDTO){
        /*if (talkDTO == null){
            return new CommonResponse<>(ErrorCode.PARAMETER_ERROR);
        }*/
        CommonResponse<TalkVO> question = talkService.question(talkDTO.getTableName(), talkDTO.getContent());

        TalkResultVO talkResultVO = dataService.generateChartData(question, "3");

        return new CommonResponse<TalkResultVO>(talkResultVO);
    }
}
