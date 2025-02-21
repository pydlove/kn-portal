package com.aiocloud.onetable.console.nlp.parse;

import com.aiocloud.onetable.console.nlp.model.Condition;
import com.aiocloud.onetable.console.nlp.model.Sort;
import com.aiocloud.onetable.console.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ybin
 * sql生成器
 */
public class SQLGenerator {

    public static String generate(String tableName, String content){
        String regex = "(?<!\\d:)\\s+(?!\\d+:)";//时间格式中的空格不能去掉
        content = content.replaceAll(regex, "");
        List<Condition> conditionList = SQLExtractor.extractConditon(tableName, content);
        String groupColumns = SQLExtractor.extractGroupField(tableName, content);
        List<Sort> sortList = SQLExtractor.extractSortField(tableName, content);
        String limitNum = SQLExtractor.extractLimit(content);

        String select = "";
        if (StringUtil.isBlank(groupColumns)){
            select = String.join("","select * from ", tableName);
        } else {
            select = String.join("","select ", groupColumns, ",count(1) from ", tableName);
        }
        String where = generateWhere(conditionList);
        String groupBy = generateGroupBy(groupColumns);
        String orderBy = generateOrderBy(sortList);
        String limit = generateLimit(limitNum);
        return String.join("", select, where, groupBy, orderBy, limit);
    }

    /**
     * 生成where子句
     * @param conditionList
     * @return
     */
    private static String generateWhere(List<Condition> conditionList){
        String where = "";
        if (conditionList != null && conditionList.size() != 0){
            where = " where ";
            for (int i = 0; i < conditionList.size(); i++) {
                if (i == 0){
                    where = where + conditionList.get(i).generateCondition();
                } else {
                    where = where + String.join("", conditionList.get(i).getRelation(), conditionList.get(i).generateCondition());
                }
            }
        }
        return where;
    }

    /**
     * 生成group by子句
     * @param groupColumns
     * @return
     */
    private static String generateGroupBy(String groupColumns){
        if (StringUtil.isBlank(groupColumns)){
            return "";
        }
        return String.join("", " group by ", groupColumns);
    }

    /**
     * 生成where子句
     * @param sortList
     * @return
     */
    private static String generateOrderBy(List<Sort> sortList){
        String orderBy = "";
        if (sortList != null && sortList.size() != 0){
            orderBy = " order by ";
            List<String> sortTextList = new ArrayList<>();
            for (Sort sort : sortList) {
                if (!StringUtil.isBlank(sort.getColumnName())){
                    sortTextList.add(sort.generateSort());
                }
            }
            orderBy = orderBy + String.join(", ", sortTextList);
        }
        return orderBy;
    }

    /**
     * 生成limit子句
     * @param limitNum
     * @return
     */
    private static String generateLimit(String limitNum){
        String limit = "";
        if (!StringUtil.isBlank(limitNum)){
            limit = String.join("", " limit ", limitNum);
        }
        return limit;
    }
}
