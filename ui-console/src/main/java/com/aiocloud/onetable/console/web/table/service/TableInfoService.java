package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.mysql.table.po.TableInfoPO;
import java.util.List;

public interface TableInfoService {

    List<TableInfoPO> selectList(TableInfoPO tableInfoPO);
}
