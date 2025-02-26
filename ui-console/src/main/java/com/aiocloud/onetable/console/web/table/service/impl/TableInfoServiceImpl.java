package com.aiocloud.onetable.console.web.table.service.impl;

import cn.hutool.core.lang.Opt;
import com.aiocloud.onetable.console.constant.DeleteConstat;
import com.aiocloud.onetable.console.utils.StringUtil;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.console.web.table.vo.ColumnInfoVo;
import com.aiocloud.onetable.console.web.table.vo.TableInfoVO;
import com.aiocloud.onetable.mysql.table.mapper.ColumnInfoMapper;
import com.aiocloud.onetable.mysql.table.mapper.TableInfoMapper;
import com.aiocloud.onetable.mysql.table.po.ColumnInfoPO;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Resource
    private TableInfoMapper tableInfoMapper;
    @Resource
    private ColumnInfoMapper columnInfoMapper;

    @Override
    public TableInfoVO tableInfo(TableInfoPO tableInfoPO) {
        LambdaQueryWrapper<TableInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TableInfoPO::getDeleteFlag, DeleteConstat.NO_DELETE);
        if (tableInfoPO != null && !StringUtil.isBlank(tableInfoPO.getTableName())){
            queryWrapper.eq(TableInfoPO::getTableName, tableInfoPO.getTableName());
        }
        if (tableInfoPO != null && tableInfoPO.getId() != null){
            queryWrapper.eq(TableInfoPO::getId, tableInfoPO.getId());
        }
        List<TableInfoPO> tableInfoPOS = tableInfoMapper.selectList(queryWrapper);
        if (tableInfoPOS == null || tableInfoPOS.size() == 0){
            throw new RuntimeException("没有查询到表记录");
        }
        TableInfoVO tableInfoVO = new TableInfoVO(tableInfoPOS.get(0).getId(),tableInfoPOS.get(0).getTableName(),tableInfoPOS.get(0).getTableComment());
        List<ColumnInfoPO> columnInfoPOList = columnInfoMapper.selectList(new LambdaQueryWrapper<ColumnInfoPO>()
                .eq(ColumnInfoPO::getTableName, tableInfoPOS.get(0).getTableName())
                .eq(ColumnInfoPO::getDeleteFlag, DeleteConstat.NO_DELETE));
        List<ColumnInfoVo> columnInfoVoList = new ArrayList<>();
        if (columnInfoPOList != null && columnInfoPOList.size() > 0){
            for (ColumnInfoPO columnInfoPO : columnInfoPOList) {
                columnInfoVoList.add(new ColumnInfoVo(columnInfoPO.getColumnName(), columnInfoPO.getColumnComment()));
            }
        }
        tableInfoVO.setColumnList(columnInfoVoList);
        return tableInfoVO;
    }

    public List<TableInfoPO> selectList(TableInfoPO tableInfoPO) {
        LambdaQueryWrapper<TableInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TableInfoPO::getDeleteFlag, DeleteConstat.NO_DELETE);
        if (tableInfoPO != null && !StringUtil.isBlank(tableInfoPO.getTableName())){
            queryWrapper.eq(TableInfoPO::getTableName, tableInfoPO.getTableName());
        }
        if (tableInfoPO != null && tableInfoPO.getId() != null){
            queryWrapper.eq(TableInfoPO::getId, tableInfoPO.getId());
        }
        return tableInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<TableInfoVO> getAllTables() {

        List<TableInfoPO> tableInfoPOS = Optional.ofNullable(tableInfoMapper.selectAll()).orElse(new ArrayList<>());
        return tableInfoPOS.stream().map(tableInfoPO -> new TableInfoVO(tableInfoPO.getId(), tableInfoPO.getTableName(), tableInfoPO.getTableComment())).collect(Collectors.toList());
    }
}
