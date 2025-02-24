package com.aiocloud.onetable.console.nlp.cache;

import com.aiocloud.onetable.mysql.table.po.ColumnInfoPO;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ybin
 */
public class TableInfoCache {

    private static Map<String, Map<String, ColumnInfoPO>> tableMap = new HashMap<>();

    public static void put(String tableName, String columnComment, ColumnInfoPO columnInfo){
        Map<String, ColumnInfoPO> map = tableMap.get(tableName);
        if (map == null){
            map = new HashMap<>();
            tableMap.put(tableName, map);
        }
        map.put(columnComment, columnInfo);
    }

    public static ColumnInfoPO get(String tableName, String columnComment){
        Map<String, ColumnInfoPO> map = tableMap.get(tableName);
        if (map == null){
            return null;
        }
        return map.get(columnComment);
    }

    public static Map<String, ColumnInfoPO> getTableMap(String tableName) {
        return tableMap.get(tableName);
    }
}
