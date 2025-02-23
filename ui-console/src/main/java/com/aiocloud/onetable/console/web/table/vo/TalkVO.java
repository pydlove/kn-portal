package com.aiocloud.onetable.console.web.table.vo;

import java.util.List;

/**
 * @author: yangbin
 */
public class TalkVO {

    private int mode = 0;// 0：表格  1：柱状图  2：饼图  3：折线图
    private List<String> columnList;
    private List<List> dataList;

    public int getMode() {
        return mode;
    }

    public TalkVO setMode(int mode) {
        this.mode = mode;
        return this;
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public TalkVO setColumnList(List<String> columnList) {
        this.columnList = columnList;
        return this;
    }

    public List<List> getDataList() {
        return dataList;
    }

    public TalkVO setDataList(List<List> dataList) {
        this.dataList = dataList;
        return this;
    }
}
