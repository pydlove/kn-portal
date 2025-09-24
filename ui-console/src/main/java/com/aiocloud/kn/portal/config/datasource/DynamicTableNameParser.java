// DynamicTableNameParser.java
package com.aiocloud.kn.portal.config.datasource;

import com.aiocloud.kn.portal.config.TableNameContext;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

import java.util.Map;

/**
 *
 * @description: DynamicTableNameParser.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 15:13
 */
public class DynamicTableNameParser implements TableNameHandler {

    @Override
    public String dynamicTableName(String sql, String tableName) {

        // 获取所有前缀
        Map<String, Object> prefixes = TableNameContext.getPrefixes();

        if (prefixes != null && !prefixes.isEmpty()) {
            // 遍历所有前缀，检查是否有对应的表名转换规则
            for (String prefix : prefixes.keySet()) {
                // 如果表名以 kn_ 开头，则替换为对应的前缀
                if (tableName.startsWith("kn_")) {
                    return prefix + "_" + tableName.substring(3);
                }
            }
        }

        return tableName;
    }
}
