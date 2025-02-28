package com.aiocloud.onetable.console.web.data.service.impl;

import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.enums.ChartTypeEnum;
import com.aiocloud.onetable.console.web.data.service.DataService;
import com.aiocloud.onetable.console.web.table.vo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public TalkResultVO generateChartData(CommonResponse<TalkVO> list) {
        TalkVO talkVO = list.getData();
//        String chartType = "1";
        String chartType = String.valueOf(talkVO.getMode());
        try {
            switch (chartType) {
                case "0":
                    return generateListData(list, chartType);
                case "1":
                    return generateBarChartData(list, chartType);
                case "2":
                    return generatePieChartData(list, chartType);
                case "3":
                    return generateLineChartData(list, chartType);
                default:
                    return generateListData(list, chartType);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private TalkResultVO generateListData(CommonResponse<TalkVO> list, String chartType) {
        TalkVO talkVO = list.getData();
        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.TABLE.getName());

        //talkResultVO.setTableData(talkVO);
        return talkResultVO;
    }

    private TalkResultVO generateBarChartData(CommonResponse<TalkVO> list, String chartType) {
        TalkVO talkVO = list.getData();
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<Map> dataList = talkVO.getDataList();

        BarVO barVO = new BarVO();
        barVO.setXName(columnList.get(0).getColumnName());
        barVO.setYName(columnList.get(1).getColumnName());

        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        for (Map data : dataList) {
            xList.add((String) data.get(columnList.get(0).getColumnName()));
            yList.add((String) data.get(columnList.get(1).getColumnName()));
        }

        barVO.setXAxis(xList);
        barVO.setYAxis(yList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.BAR.getName());
        talkResultVO.setBarData(barVO);
        return talkResultVO;
    }

    private TalkResultVO generatePieChartData(CommonResponse<TalkVO> list, String chartType) {
        TalkVO talkVO = list.getData();
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<Map> dataList = talkVO.getDataList();

        PieVO pieVO = new PieVO();
        List<PieDataVO> pieDataList = new ArrayList<>();
        for (Map data : dataList) {
            PieDataVO pieDataVO = new PieDataVO();
            pieDataVO.setName(data.get(columnList.get(0).getColumnName()).toString());
            pieDataVO.setValue(data.get(columnList.get(1).getColumnName()).toString());
            pieDataList.add(pieDataVO);
        }
        pieVO.setData(pieDataList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.PIE.getName());
        talkResultVO.setPieData(pieVO);
        return talkResultVO;
    }

    private TalkResultVO generateLineChartData(CommonResponse<TalkVO> list, String chartType) {
        TalkVO talkVO = list.getData();
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<Map> dataList = talkVO.getDataList();

        LineVO lineVO = new LineVO();
        lineVO.setXName(columnList.get(0).getColumnDesc());
        lineVO.setYName(columnList.get(1).getColumnDesc());

        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        for (Map data : dataList) {
            xList.add((String) data.get(columnList.get(0).getColumnName()));
            yList.add((String) data.get(columnList.get(1).getColumnName()));
        }

        lineVO.setXAxis(xList);
        lineVO.setYAxis(yList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.LINE.getName());
        talkResultVO.setLineData(lineVO);
        return talkResultVO;
    }
}
