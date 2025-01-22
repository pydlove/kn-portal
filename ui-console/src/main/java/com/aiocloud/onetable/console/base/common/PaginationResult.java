package com.aiocloud.onetable.console.base.common;


import cn.hutool.core.collection.CollUtil;
import com.aiocloud.onetable.console.base.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.aiocloud.onetable.console.base.exception.ErrorCode;

/**
 *
 * @description: PaginationResult.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-22 11:11 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaginationResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final int PAGE_SIZE_MAX_LIMIT = Integer.MAX_VALUE - 1;
    public static final String PAGE_SIZE_PROPERTY_KEY = "max.page.size";
    public static final Integer PAGE_SIZE_DEFAULT_VALUE = 50;

    @Setter
    private long total;

    private List<T> rows;



    public void setRows(List<T> rows) {

        if (CollUtil.isNotEmpty(rows) && rows.size() > PAGE_SIZE_MAX_LIMIT) {
            throw new BadRequestException(ErrorCode.INTERNAL_SERVER_ERROR, "rows not more than " + PAGE_SIZE_MAX_LIMIT);
        }
        this.rows = rows;
    }

    /**
     *
     * Provide original unlimited number of rows
     * @param rows row data
     */
    public void setRowsWithNoLimit(List<T> rows) {
        this.rows = rows;
    }

}

