package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.constant.DeleteConstat;
import com.aiocloud.onetable.console.utils.StringUtil;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.mysql.table.mapper.TableInfoMapper;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Resource
    private TableInfoMapper tableInfoMapper;

    @Override
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
}
