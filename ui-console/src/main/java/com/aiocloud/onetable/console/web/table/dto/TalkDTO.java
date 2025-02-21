package com.aiocloud.onetable.console.web.table.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: yangbin
 */
@Data
public class TalkDTO {

    private int mode = 0;// 0：表格  1：柱状图  2：饼图  3：折线图
    private List<String> columnList;
    private List<List> dataList;

}
