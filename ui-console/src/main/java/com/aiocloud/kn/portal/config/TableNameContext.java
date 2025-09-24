package com.aiocloud.kn.portal.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @description: TableNameContext.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 6:38
 */
public class TableNameContext {
    private static final ThreadLocal<Map<String, Object>> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置前缀标识
     * @param prefix 前缀名称
     */
    public static void setPrefix(String prefix) {
        Map<String, Object> context = CONTEXT_HOLDER.get();
        if (context == null) {
            context = new ConcurrentHashMap<>();
            CONTEXT_HOLDER.set(context);
        }
        context.put(prefix, Boolean.TRUE);
    }

    /**
     * 检查是否存在指定前缀
     * @param prefix 前缀名称
     * @return 是否存在该前缀
     */
    public static boolean hasPrefix(String prefix) {
        Map<String, Object> context = CONTEXT_HOLDER.get();
        if (context == null) {
            return false;
        }
        Object flag = context.get(prefix);
        return flag != null && (Boolean) flag;
    }

    /**
     * 获取所有前缀
     * @return 前缀集合
     */
    public static Map<String, Object> getPrefixes() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清理上下文
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}
