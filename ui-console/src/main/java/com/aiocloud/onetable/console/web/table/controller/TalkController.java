package com.aiocloud.onetable.console.web.table.controller;

import com.aiocloud.onetable.console.utils.Result;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping("/question")
    public Result question(@RequestBody String tableName, @RequestBody String content){
        return talkService.question(tableName, content);
    }
}
