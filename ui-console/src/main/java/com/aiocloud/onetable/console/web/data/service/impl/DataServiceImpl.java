package com.aiocloud.onetable.console.web.data.service.impl;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.enums.ChartTypeEnum;
import com.aiocloud.onetable.console.web.data.service.DataService;
import com.aiocloud.onetable.console.web.table.vo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public TalkResultVO generateChartData(CommonResponse<TalkVO> list) {
        TalkVO talkVO = list.getData();
//        String chartType = String.valueOf(data.getMode());

        String chartType = "1";
        try {
            switch (chartType) {
                case "0":
                    return generateListData(talkVO, chartType);
                case "1":
                    return generateBarChartData(talkVO, chartType);
                case "2":
                    return generatePieChartData(talkVO, chartType);
                case "3":
                    return generateLineChartData(talkVO, chartType);
                default:
                    return generateListData(talkVO, chartType);
//                    throw new IllegalArgumentException("Unsupported chart type: " + chartType);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private TalkResultVO generateListData(TalkVO talkVO, String chartType) {
        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.TABLE.getName());

//        talkResultVO.setTableData();
        // 生成列表数据
        return talkResultVO;
    }

    private TalkResultVO generateBarChartData(TalkVO talkVO, String chartType) {
        //数据转化
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<List> dataList = talkVO.getDataList();
        BarVO barVO = new BarVO();
        barVO.setXName(columnList.get(0).getColumnName());
        barVO.setYName(columnList.get(1).getColumnName());
        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            xList.add((String)dataList.get(i).get(0));
            yList.add((String)dataList.get(i).get(1));
        }
        barVO.setXAxis(xList);
        barVO.setYAxis(yList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.BAR.getName());
        talkResultVO.setBarData(barVO);
        return talkResultVO;
    }

    private TalkResultVO generatePieChartData(TalkVO talkVO, String chartType) {

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.PIE.getName());
        return talkResultVO;
    }

    private TalkResultVO generateLineChartData(TalkVO talkVO, String chartType) {
        //数据转化
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<List> dataList = talkVO.getDataList();
        LineVO lineVO = new LineVO();
        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            xList.add((String)dataList.get(i).get(0));
            yList.add((String)dataList.get(i).get(1));
        }
        lineVO.setXAxis(xList);
        lineVO.setYAxis(yList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(chartType);
        talkResultVO.setChartType(ChartTypeEnum.LINE.getName());
        talkResultVO.setLineData(lineVO);
        return talkResultVO;
    }
}