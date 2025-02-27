package com.aiocloud.onetable.console.nlp;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ybin
 */
@Service
public class SQLExecutor {
    private static Logger logger = LoggerFactory.getLogger(SQLExecutor.class);

    @Autowired
    private SqlSessionFactory sessionFactory;

    /**
     * 执行sql
     * @param sql
     * @param columnList
     * @return
     * @throws SQLException
     */
    public List executeSql(String sql, List<String> columnList) throws SQLException {
        List<Map<String,String>> resultList = new ArrayList<>();
        try(
            PreparedStatement preparedStatement = sessionFactory.openSession().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
        ){
            while (resultSet.next()){
                Map<String,String> data = new HashMap<>();
                for (int i = 1; i <= columnList.size(); i++) {
                    data.put(columnList.get(i - 1),resultSet.getString(i));
                }
                resultList.add(data);
            }
        } catch (Exception e){
            throw e;
        }
        return resultList;
    }

    /**
     * 获取表所有列名
     * @param metaData
     * @return
     * @throws SQLException
     */
    private String getColumns(ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        String columns = "";
        for (int i = 1; i <= columnCount; i++) {
            if (i == 1){
                columns = columns + metaData.getColumnName(i);
            } else {
                columns = columns + ", " +metaData.getColumnName(i);
            }
        }
        return columns;
    }
}
