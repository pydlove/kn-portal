package com.aiocloud.onetable.console.nlp.parse;

import com.aiocloud.onetable.console.base.exception.BadRequestException;
import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.aiocloud.onetable.console.nlp.cache.TableInfoCache;
import com.aiocloud.onetable.console.nlp.model.Condition;
import com.aiocloud.onetable.console.nlp.model.Sort;
import com.aiocloud.onetable.console.utils.StringUtil;
import com.aiocloud.onetable.mysql.table.po.ColumnInfoPO;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ybin
 * sql提取器
 */
public class SQLExtractor {

    /**
     * 条件提取
     * @param content
     * @return
     */
    public static List<Condition> extractConditon(String tableName, String content){
        List<Condition> conditionList = new ArrayList<>();
        if (StringUtil.isBlank(content)){
            return conditionList;
        }
        Condition condition = new Condition();
        // todo 用于实现or的条件
        String[] strings = checkOr(content);

        CoNLLSentence coNLLWords = HanLP.parseDependency(content);
        CoNLLWord[] wordArray = coNLLWords.getWordArray();
        for (int i = 0; i < wordArray.length; i++) {
            CoNLLWord word = wordArray[i];
//            System.out.println(word.LEMMA + "   " + word.POSTAG);
            if (word.POSTAG.equals("or")){
                if (i < wordArray.length){// 设置右边比较值
                    CoNLLWord nextWord = wordArray[i + 1];
                    CoNLLWord nextTowWord = wordArray[i + 2];
                    if (nextTowWord.POSTAG.equals("operator") && nextWord.POSTAG.equals("column")){
                        condition.setRelation(" or ");
                    }
//                    else if (nextWord.POSTAG.equals("column")){
//
//                    }
                }
            }
            if (word.POSTAG.equals("operator")){
                condition.setOperator(extractOperator(word.LEMMA));
                boolean lastColumn = false;//左边是否是表字段
                boolean nextColumn = false;//右边边是否是表字段
                if (i > 0){// 设置左边比较值
                    CoNLLWord lastWord = wordArray[i - 1];
                    if (lastWord.POSTAG.equals("column")){
                        lastColumn = true;
                        condition.setLeftValue(TableInfoCache.get(tableName, lastWord.LEMMA).getColumnName());
                    } else {
                        condition.setLeftValue(String.join("", "'",  lastWord.LEMMA, "'"));
                    }
                }
                if (i < wordArray.length){// 设置右边比较值
                    CoNLLWord nextWord = wordArray[i + 1];
                    if (nextWord.POSTAG.equals("column")){
                        nextColumn = true;
                        condition.setRightValue(TableInfoCache.get(tableName, nextWord.LEMMA).getColumnName());
                    } else {
                        condition.setRightValue(String.join("", "'",  nextWord.LEMMA, "'"));
                    }
                }
                if (condition.fillComplated() && (lastColumn || nextColumn)){//条件已填充，并且至少有一个是表字段
                    conditionList.add(condition);
                    condition = new Condition();
                }
            }
        }
        return conditionList;
    }

    /**
     * 提取分组字段
     * @param content
     * @return
     */
    public static String extractGroupField(String tableName, String content){
        List<String> groupColumns = new ArrayList<>();
        if (StringUtil.isBlank(content)){
            return "";
        }
        content = KeywordSpliter.splitGroupText(content);
        if (StringUtil.isBlank(content)){
            return "";
        }
        CoNLLSentence coNLLWords = HanLP.parseDependency(content);
        CoNLLWord[] wordArray = coNLLWords.getWordArray();
        int sum = 0;
        for (int i = 0; i < wordArray.length; i++) {
            CoNLLWord word = wordArray[i];
            if (word.POSTAG.equals("column")){
                ColumnInfoPO columnInfoPO = TableInfoCache.get(tableName, word.LEMMA);
                if (columnInfoPO == null){
                    throw new BadRequestException(ErrorCode.NOTFOUNDCOLUMN, tableName + "表没有" + word.LEMMA + "相关属性");
                }
                String column = TableInfoCache.get(tableName, word.LEMMA).getColumnName();
                if (!StringUtil.isBlank(column)){
                    groupColumns.add(column);
                    continue;
                }
            }
            // 判断是否是分组结束
            for (String[] groupSplitRange : KeywordSpliter.groupSplitRanges) {
                if (groupSplitRange[0].contains(word.LEMMA)){
                    groupColumns.clear();
                    break;
                }
                if (groupSplitRange[1].contains(word.LEMMA)){
                    if (++ sum > 1){
                        return String.join(",", groupColumns);
                    }
                    break;
                }
            }
        }
        return String.join(",", groupColumns);
    }

    /**
     * 提取排序字段
     * @param content
     * @return
     */
    public static List<Sort> extractSortField(String tableName, String content){
        List<Sort> sortList = new ArrayList<>();
        if (StringUtil.isBlank(content)){
            return sortList;
        }
        content = KeywordSpliter.splitSortText(content);
        if (StringUtil.isBlank(content)){
            return sortList;
        }
        Sort sort = new Sort();
        CoNLLSentence coNLLWords = HanLP.parseDependency(content);
        CoNLLWord[] wordArray = coNLLWords.getWordArray();
        for (int i = 0; i < wordArray.length; i++) {
            CoNLLWord word = wordArray[i];
            if (word.POSTAG.equals("column")){
                if (sort.getColumnName() != null && sort.getColumnName().trim().length() > 0){
                    sortList.add(sort);
                    sort = new Sort();
                }
                sort.setColumnName(TableInfoCache.get(tableName, word.LEMMA).getColumnName());
            }
            if (word.LEMMA.contains("降") || word.LEMMA.contains("倒")){
                sort.setSortType("desc");
            }
        }
        if (sort.getColumnName() != null && sort.getColumnName().trim().length() > 0){
            sortList.add(sort);
            sort = new Sort();
        }
        return sortList;
    }

    /**
     * 提取取数限制
     * @param content
     * @return
     */
    public static String extractLimit(String content){
        if (StringUtil.isBlank(content)){
            return "";
        }
        String limitText = KeywordSpliter.splitLimitText(content);
        if (StringUtil.isNumeric(limitText)){
            return limitText;
        }
        return "";
    }

    /**
     * 提取运算符
     * @param text
     * @return
     */
    public static String extractOperator(String text){
        switch (text){
            case "=" : return "=";
            case "为": return "=";
            case "是": return "=";
            case "等于": return "=";
            case "!=": return "!=";
            case "不等于": return "!=";
            case "不为": return "!=";
            case "不是": return "!=";
            case ">": return ">";
            case "大于": return ">";
            case "大过": return ">";
            case "之后": return ">";
            case "<": return "<";
            case "小于": return "<";
            case "小过": return "<";
            case "之前": return "<";
            case ">=": return ">=";
            case "大于等于": return ">=";
            case "大于或等于": return ">=";
            case "<=": return "<=";
            case "小于等于": return "<=";
            case "小于或等于": return "<=";
            default: return "";
        }
    }

    private static String[] checkOr(String content){
        String[] contentArr = new String[]{};
        if (StringUtil.isBlank(content)){
            return contentArr;
        }
        if (content.contains("或者")){
            contentArr = content.split("或者");
        }
        if (content.contains("或")){
            contentArr = content.split("或");
        }
        return contentArr;
    }
}
