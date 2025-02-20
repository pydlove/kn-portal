package com.aiocloud.onetable.console.web.table.service;

import java.util.List;

public interface ColumnInfoService {

    List selectList(String tableName, String columnName, String columnComment);
}
