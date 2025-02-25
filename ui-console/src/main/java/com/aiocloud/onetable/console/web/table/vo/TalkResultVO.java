package com.aiocloud.onetable.console.web.table.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName TalkResultVO
 * @Description TODO
 * @Author shux
 * @Date 2025/2/22 22:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TalkResultVO {

    // 图表类型， "0 table",  "1 bar", "2 pie", "3 line"
    private String chartType;
    // 表格数据对象
    private TableVO tableData;
    // 柱状图数据对象
    private BarVO barData;
    // 折线图数据对象
    private LineVO lineData;
    // 饼图数据对象
    private PieVO pieData;
}
