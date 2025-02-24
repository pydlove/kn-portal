package com.aiocloud.onetable.console.web.table.vo;

public class ColumnInfoVo {

    private String columnName;
    private String columnDesc;

    public ColumnInfoVo() {

    }

    public ColumnInfoVo(String columnName, String columnDesc) {
        this.columnName = columnName;
        this.columnDesc = columnDesc;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnInfoVo setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public ColumnInfoVo setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
        return this;
    }
}
