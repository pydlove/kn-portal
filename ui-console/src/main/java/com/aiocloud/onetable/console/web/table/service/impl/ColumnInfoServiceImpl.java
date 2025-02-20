package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.constant.DeleteConstat;
import com.aiocloud.onetable.console.utils.StringUtil;
import com.aiocloud.onetable.console.web.table.service.ColumnInfoService;
import com.aiocloud.onetable.mysql.table.mapper.ColumnInfoMapper;
import com.aiocloud.onetable.mysql.table.po.ColumnInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ColumnInfoServiceImpl implements ColumnInfoService {

    private static Logger logger = LoggerFactory.getLogger(ColumnInfoServiceImpl.class);
    @Resource
    private ColumnInfoMapper columnInfoMapper;

    @Override
    public List selectList(String tableName, String columnName, String columnComment) {
        LambdaQueryWrapper<ColumnInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtil.isBlank(tableName)){
            queryWrapper.eq(ColumnInfoPO::getDeleteFlag, DeleteConstat.NO_DELETE);
        }
        if (!StringUtil.isBlank(columnName)){
            queryWrapper.eq(ColumnInfoPO::getColumnName, columnName);
        }
        if (!StringUtil.isBlank(columnComment)){
            queryWrapper.like(ColumnInfoPO::getColumnComment, columnComment);
        }
        List<ColumnInfoPO> tableInfoPOList = columnInfoMapper.selectList(queryWrapper);
        return tableInfoPOList;
    }
}
