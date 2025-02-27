package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.nlp.SQLExecutor;
import com.aiocloud.onetable.console.nlp.cache.TableInfoCache;
import com.aiocloud.onetable.console.nlp.parse.SQLGenerator;
import com.aiocloud.onetable.console.utils.StringUtil;
import com.aiocloud.onetable.console.web.sys.service.TableAuthService;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.console.web.table.service.TalkService;
import com.aiocloud.onetable.console.web.table.vo.ColumnInfoVo;
import com.aiocloud.onetable.console.web.table.vo.TalkVO;
import com.aiocloud.onetable.mysql.table.po.ColumnInfoPO;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    public CommonResponse question(String tableName, String content, Integer pageSize, Integer pageNum) {
        TalkVO talkVO = new TalkVO();
        /*try {
            if (!checkTableAuth(tableName)){
                return new CommonResponse(ErrorCode.UNAUTHORIZED, talkVO);
            }
        } catch (Exception e) {
            return new CommonResponse(ErrorCode.UNAUTHORIZED, talkVO);
        }*/
        try {
            String select = SQLGenerator.generate(tableName, content,pageSize, pageNum);
            logger.info("解析SQL为：{}", select);
            Map<String, ColumnInfoPO> tableMap = TableInfoCache.getTableMap(tableName);
            if (tableMap == null){
                logger.error("查不到表{}信息", tableName);
                return new CommonResponse(ErrorCode.NOTFOUNDTABLE, talkVO);
            }

            Map<String, ColumnInfoVo> columnInfoVoMap = formatColumnInfo(tableMap);
            List<String> columnList = fullColumns(select, columnInfoVoMap);
            if (select.contains("*")){//将*替换为真实查询的字段
                select = select.replace("*", String.join(",", columnList));
            }
            List list = sqlExecutor.executeSql(select, columnList);
            List<ColumnInfoVo> resultColumns = getColumnInfo(columnInfoVoMap, columnList);
            talkVO.setColumnList(resultColumns).setDataList(list);
        } catch (Exception e) {
            logger.error("对话异常", e);
            return new CommonResponse(ErrorCode.UNRECOGNIZED, talkVO);
        }
        return new CommonResponse(talkVO);
    }

    /**
     * 返回给前端的表头
     * @param sql
     * @param tableMap
     */
    public List<String> fullColumns(String sql, Map<String, ColumnInfoVo> tableMap){
        List<String> columnList = new ArrayList<>();
        if (sql.contains("*")){
            tableMap.forEach((key, value) -> {
                if (!"count".equals(key)){
                    columnList.add(value.getColumnName());
                }
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
                columnList.add(tableMap.get(column.trim()).getColumnName());
            }
        }
        return columnList;
    }

    /**
     * 表字段信息去重
     * @param tableMap
     * @return
     */
    private Map<String, ColumnInfoVo> formatColumnInfo(Map<String, ColumnInfoPO> tableMap){
        Map<String, ColumnInfoVo> columnInfoVoMap = new HashMap<>();
        tableMap.forEach((key, value) -> {
            if (!columnInfoVoMap.containsKey(value.getColumnName())){
                columnInfoVoMap.put(value.getColumnName(), new ColumnInfoVo(value.getColumnName(), value.getColumnComment()));
            }
        });
        columnInfoVoMap.put("count", new ColumnInfoVo("count", "数量"));
        return columnInfoVoMap;
    }

    private List<ColumnInfoVo> getColumnInfo(Map<String, ColumnInfoVo> tableMap, List<String> columnList){
        List<ColumnInfoVo> columnInfoVoList = new ArrayList<>();
        for (String column : columnList) {
            columnInfoVoList.add(tableMap.get(column));
        }
        return columnInfoVoList;
    }

//    /**
//     * 校验表权限
//     * @param tableName
//     * @return
//     */
//    private boolean checkTableAuth(String tableName){
//        TableInfoPO tableInfoPO = new TableInfoPO();
//        tableInfoPO.setTableName(tableName);
//        List<TableInfoPO> tableInfoPOS = tableInfoService.selectList(tableInfoPO);
//        if (tableInfoPOS == null || tableInfoPOS.size() == 0){
//            return false;
//        }
//        return tableAuthService.checkAccessAuth(tableInfoPOS.get(0).getId());
//    }
}