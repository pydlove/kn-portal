package com.aiocloud.onetable.console.web.table.vo;


import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TableVO
 * @Description 表格数据对象
 * @Author shux
 * @Date 2025/2/22 23:02
 */
@Data
public class TableVO {

    private List<ColumnInfoVo> headers;
    private List<Map<String, String>> rows;
}
