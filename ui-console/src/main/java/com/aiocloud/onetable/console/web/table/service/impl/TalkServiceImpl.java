package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.nlp.SQLExecutor;
import com.aiocloud.onetable.console.nlp.cache.TableInfoCache;
import com.aiocloud.onetable.console.nlp.parse.SQLGenerator;
import com.aiocloud.onetable.console.utils.Result;
import com.aiocloud.onetable.console.utils.StringUtil;
import com.aiocloud.onetable.console.web.sys.service.TableAuthService;
import com.aiocloud.onetable.console.web.table.dto.TalkDTO;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private TableInfoService tableInfoService;
    @Resource
    private TableAuthService tableAuthService;

    @Override
    public CommonResponse question(String tableName, String content) {
        TalkDTO talkDTO = new TalkDTO();
        try {
            if (!checkTableAuth(tableName)){
                return new CommonResponse(ErrorCode.UNAUTHORIZED, talkDTO);
            }
        } catch (Exception e) {
            return new CommonResponse(ErrorCode.UNAUTHORIZED, talkDTO);
        }
        try {
            String select = SQLGenerator.generate(tableName, content);
            logger.info("解析SQL为：{}", select);
            Map<String, String> tableMap = TableInfoCache.getTableMap(tableName);
            if (tableMap == null){
                logger.error("查不到表{}信息", tableName);
                return new CommonResponse(ErrorCode.NOTFOUNDTABLE, talkDTO);
            }
            List<String> columnList = fullColumns(select, tableMap);
            if (select.contains("*")){//将*替换为真实查询的字段
                select = select.replace("*", String.join(",", columnList));
            }
            List list = sqlExecutor.executeSql(select, columnList);
            talkDTO.setColumnList(columnList).setDataList(list);
        } catch (Exception e) {
            logger.error("对话异常", e);
            return new CommonResponse(ErrorCode.UNRECOGNIZED, talkDTO);
        }
        return new CommonResponse(talkDTO);
    }

    /**
     * 返回给前端的表头
     * @param sql
     * @param tableMap
     */
    public List<String> fullColumns(String sql, Map<String, String> tableMap){
        List<String> columnList = new ArrayList<>();
        if (sql.contains("*")){
            tableMap.forEach((key, value) -> {
                columnList.add(value);
            });
            return columnList;
        }
        //从sql中获取真实的展示字段
        String showColumns = sql.substring(sql.indexOf("select") + 6, sql.indexOf("from"));
        if (!StringUtil.isBlank(showColumns)){
            String[] columns = showColumns.trim().split(",");
            for (String column : columns) {
                if (column.contains("count(1)")){ //统计列替换
                    columnList.add("count");
                    continue;
                }
                columnList.add(column);
            }
        }
        return columnList;
    }

    /**
     * 校验表权限
     * @param tableName
     * @return
     */
    private boolean checkTableAuth(String tableName){
        TableInfoPO tableInfoPO = new TableInfoPO();
        tableInfoPO.setTableName(tableName);
        List<TableInfoPO> tableInfoPOS = tableInfoService.selectList(tableInfoPO);
        if (tableInfoPOS == null || tableInfoPOS.size() == 0){
            return false;
        }
        return tableAuthService.checkAccessAuth(tableInfoPOS.get(0).getId());
    }
}