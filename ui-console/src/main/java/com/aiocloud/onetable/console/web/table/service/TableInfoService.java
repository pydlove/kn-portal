package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.console.web.table.vo.TableInfoVO;
import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import java.util.List;

public interface TableInfoService {

    List<TableInfoPO> selectList(TableInfoPO tableInfoPO);

    List<TableInfoVO> getAllTables();
}
