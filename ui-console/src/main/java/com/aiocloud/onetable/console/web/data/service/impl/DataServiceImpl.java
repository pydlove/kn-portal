package com.aiocloud.onetable.console.web.data.service.impl;

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
    public TalkResultVO generateChartData(TalkVO talkVO) {
//        String chartType = "1";
        String chartType = String.valueOf(talkVO.getMode());
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
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private TalkResultVO generateListData(TalkVO talkVO, String chartType) {
        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.TABLE.getName());
        TableVO tableVO = new TableVO();
        tableVO.setHeaders(talkVO.getColumnList());
        tableVO.setRows(talkVO.getDataList());
        //talkResultVO.setTableData(talkVO);
        return talkResultVO;
    }

    private TalkResultVO generateBarChartData(TalkVO talkVO, String chartType) {
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<Map<String, String>> dataList = talkVO.getDataList();

        BarVO barVO = new BarVO();
        barVO.setXName(columnList.get(0).getColumnDesc());
        barVO.setYName(columnList.get(1).getColumnDesc());

        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        for (Map<String, String> data : dataList) {
            xList.add(data.get(columnList.get(0).getColumnName()));
            yList.add(data.get(columnList.get(1).getColumnName()));
        }

        barVO.setXAxis(xList);
        barVO.setYAxis(yList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.BAR.getName());
        talkResultVO.setBarData(barVO);
        return talkResultVO;
    }

    private TalkResultVO generatePieChartData(TalkVO talkVO, String chartType) {
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<Map<String, String>> dataList = talkVO.getDataList();

        PieVO pieVO = new PieVO();
        List<PieDataVO> pieDataList = new ArrayList<>();
        for (Map<String, String> data : dataList) {
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

    private TalkResultVO generateLineChartData(TalkVO talkVO, String chartType) {
        List<ColumnInfoVo> columnList = talkVO.getColumnList();
        List<Map<String, String>> dataList = talkVO.getDataList();

        LineVO lineVO = new LineVO();
        lineVO.setXName(columnList.get(0).getColumnDesc());
        lineVO.setYName(columnList.get(1).getColumnDesc());

        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        for (Map<String, String> data : dataList) {
            xList.add(data.get(columnList.get(0).getColumnName()));
            yList.add(data.get(columnList.get(1).getColumnName()));
        }

        lineVO.setXAxis(xList);
        lineVO.setYAxis(yList);

        TalkResultVO talkResultVO = new TalkResultVO();
        talkResultVO.setChartType(ChartTypeEnum.LINE.getName());
        talkResultVO.setLineData(lineVO);
        return talkResultVO;
    }
}
