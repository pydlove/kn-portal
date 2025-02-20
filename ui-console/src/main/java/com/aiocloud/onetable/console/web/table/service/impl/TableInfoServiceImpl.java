package com.aiocloud.onetable.console.web.table.service.impl;

import com.aiocloud.onetable.console.constant.DeleteConstat;
import com.aiocloud.onetable.console.utils.Result;
import com.aiocloud.onetable.console.web.table.service.TableInfoService;
import com.aiocloud.onetable.mysql.table.mapper.TableInfoMapper;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TableInfoServiceImpl implements TableInfoService {

    private static Logger logger = LoggerFactory.getLogger(TableInfoServiceImpl.class);
    @Resource
    private TableInfoMapper tableInfoMapper;

    @Override
    public Result selectList() {
        try {
            // todo 1权限校验标识  2是否分页查询
            List<TableInfoPO> tableInfoPOS = tableInfoMapper.selectList(new LambdaQueryWrapper<TableInfoPO>().eq(TableInfoPO::getDeleteFlag, DeleteConstat.NO_DELETE));
            return Result.success("", tableInfoPOS);
        } catch (Exception e) {
            logger.error("查询表配置信息异常", e);
            return Result.fail("查询表配置信息异常", e);
        }
    }
}
