package com.aiocloud.onetable.console.web.table.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName BarVO
 * @Description TODO
 * @Author shux
 * @Date 2025/2/22 23:02
 */
@Data
public class BarVO {

    @JsonProperty("chart_name")
    private String chartName; // 图表名称

    @JsonProperty("title")
    private String title;

    @JsonProperty("legend")
    private String legend;

    @JsonProperty("xCode")
    private String xCode; // X轴code

    @JsonProperty("xName")
    private String xName; // X轴名称

    @JsonProperty("yCode")
    private String yCode; // Y轴code

    @JsonProperty("yName")
    private String yName; // Y轴名称

    @JsonProperty("xAxis")
    private List<String> xAxis;

    @JsonProperty("yAxis")
    private List<String> yAxis;
}
