package com.aiocloud.onetable.console.nlp.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ybin
 */
public class TableInfoCache {

    private static Map<String, Map<String, String>> tableMap = new HashMap<>();

    public static void put(String tableName, String columnComment, String columnName){
        Map<String, String> map = tableMap.get(tableName);
        if (map == null){
            map = new HashMap<>();
            tableMap.put(tableName, map);
        }
        map.put(columnComment, columnName);
    }

    public static String get(String tableName, String columnComment){
        Map<String, String> map = tableMap.get(tableName);
        if (map == null){
            return null;
        }
        return map.get(columnComment);
    }

    public static Map<String, String> getTableMap(String tableName) {
        return tableMap.get(tableName);
    }
}
