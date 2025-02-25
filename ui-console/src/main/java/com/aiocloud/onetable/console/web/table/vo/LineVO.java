package com.aiocloud.onetable.console.web.table.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName LineVO
 * @Description 折线图数据对象
 * @Author shux
 * @Date 2025/2/22 23:02
 */
@Data
public class LineVO {

    @JsonProperty("xAxis")
    private List<String> xAxis;

    @JsonProperty("yAxis")
    private List<String> yAxis;
}
