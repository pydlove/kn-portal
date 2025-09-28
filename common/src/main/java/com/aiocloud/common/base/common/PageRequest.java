package com.aiocloud.common.base.common;

import lombok.Getter;

/**
 *
 * @description: PageRequest.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-22 11:24
 */
@Getter
public class PageRequest {

    private Integer page = 1;
    private Integer rows = 15;

    public PageRequest(Integer rows, Integer page) {
        setRows(rows == null ? 0 : rows);
        setPage(page == null ? 0 : page);
    }

    public void setRows(Integer rows) {

        if (rows != null) {

            if (rows <= 0) {
                this.rows = Integer.MAX_VALUE;
            } else {
                this.rows = rows;
            }

        } else {
            this.rows = Integer.MAX_VALUE;
        }

    }

    public void setPage(Integer page) {

        if (page != null) {

            if (page <= 0) {
                this.page = 1;
            } else {
                this.page = page;
            }

        } else {

            this.page = 1;
        }

    }
}
