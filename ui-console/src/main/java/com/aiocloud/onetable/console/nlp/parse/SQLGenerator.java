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
        List<Sort> sortList = SQLExtractor.extractSortField(tableName, content);
        String sql = generate(tableName, conditionList, sortList);
        return sql;
    }

    public static String generate(String tableName, List<Condition> conditionList, List<Sort> sortList){
        String select = String.join("","select * from ", tableName);
        String where = "";
        String orderBy = "";
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
        return String.join("", select, where, orderBy);
    }
}
