package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.console.web.table.vo.ColumnInfoVo;
import com.aiocloud.onetable.console.web.table.vo.TableInfoVO;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import java.util.List;

public interface TableInfoService {

    TableInfoVO tableInfo(TableInfoPO tableInfoPO);

    List<TableInfoPO> selectList(TableInfoPO tableInfoPO);

    List<TableInfoVO> getAllTables();
}
