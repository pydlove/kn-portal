package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.nlp.SQLExecutor;
import com.aiocloud.onetable.console.nlp.cache.TableInfoCache;
import com.aiocloud.onetable.console.nlp.parse.SQLGenerator;
import com.aiocloud.onetable.console.utils.Result;
import com.aiocloud.onetable.console.web.table.dto.TalkDTO;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther ybin
 */
@RequiredArgsConstructor
@Service
public class TalkServiceImpl implements TalkService {
    private static Logger logger = LoggerFactory.getLogger(TalkServiceImpl.class);

    @Resource
    private SQLExecutor sqlExecutor;

    @Override
    public Result question(String tableName, String content) {
        TalkDTO talkDTO = new TalkDTO();
        try {
            String select = SQLGenerator.generate(tableName, content);
            logger.info("解析SQL为：{}", select);
            List<String> columnList = new ArrayList<>();
            Map<String, String> tableMap = TableInfoCache.getTableMap(tableName);
            if (tableMap == null){
                logger.error("查不到表{}信息", tableName);
                Result.fail("查不到表" + tableName + "信息");
            }
            tableMap.forEach((key, value) -> {
                columnList.add(value);
            });
            List list = sqlExecutor.executeSql(select, columnList);
            talkDTO.setColumnList(columnList);
            talkDTO.setDataList(list);
        } catch (Exception e) {
            logger.error("会话异常", e);
            Result.fail("会话异常", e);
        }
        return Result.success("", talkDTO);
    }
}