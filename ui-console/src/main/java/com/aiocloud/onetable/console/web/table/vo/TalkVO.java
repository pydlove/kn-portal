package com.aiocloud.onetable.console.web.table.vo;

import java.util.List;
import java.util.Map;

/**
 * @author: yangbin
 */
public class TalkVO {

    private int mode = 0;// 0：表格  1：柱状图  2：饼图  3：折线图
    private List<ColumnInfoVo> columnList;
    private List<Map> dataList;

    public int getMode() {
        return mode;
    }

    public TalkVO setMode(int mode) {
        this.mode = mode;
        return this;
    }

    public List<ColumnInfoVo> getColumnList() {
        return columnList;
    }

    public TalkVO setColumnList(List<ColumnInfoVo> columnList) {
        this.columnList = columnList;
        return this;
    }

    public List<Map> getDataList() {
        return dataList;
    }

    public TalkVO setDataList(List<Map> dataList) {
        this.dataList = dataList;
        return this;
    }
}
