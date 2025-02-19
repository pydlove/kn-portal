package com.aiocloud.onetable.console.nlp.model;

/**
 * @auther ybin
 */
public class Sort {

    private String columnName;
    private String sortType = "asc";

    public Sort(){

    }

    public Sort(String columnName, String sortType) {
        this.columnName = columnName;
        this.sortType = sortType;
    }

    public String generateSort(){
        return String.join("", this.columnName, " ", this.sortType);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
