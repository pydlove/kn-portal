package com.aiocloud.onetable.console.web.data.service;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.web.table.vo.TalkResultVO;
import com.aiocloud.onetable.console.web.table.vo.TalkVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DataService
 * @Description TODO
 * @Author shux
 * @Date 2025/2/20 20:22
 */
public interface DataService {
    TalkResultVO generateChartData(CommonResponse<TalkVO> list, String chartType);

    // 导出数据
//    byte[] exportData(String tableName, String format, Map<String, Object> filters);
}
