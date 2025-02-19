package com.aiocloud.onetable.console.nlp.parse;

import com.aiocloud.onetable.console.nlp.model.Condition;
import com.aiocloud.onetable.console.nlp.model.Sort;
import com.aiocloud.onetable.console.nlp.cache.TableMap;
import com.aiocloud.onetable.console.utils.StringUtil;
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
    public static List<Condition> extractConditon(String content){
        if (StringUtil.isBlank(content)){
            return null;
        }
        List<Condition> conditionList = new ArrayList<>();
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
                        condition.setLeftValue(TableMap.map.get(lastWord.LEMMA));
                    } else {
                        condition.setLeftValue(String.join("", "'",  lastWord.LEMMA, "'"));
                    }
                }
                if (i < wordArray.length){// 设置右边比较值
                    CoNLLWord nextWord = wordArray[i + 1];
                    if (nextWord.POSTAG.equals("column")){
                        nextColumn = true;
                        condition.setRightValue(TableMap.map.get(nextWord.LEMMA));
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
    public static String extractGroupField(String content){
        if (StringUtil.isBlank(content)){
            return null;
        }
        String groupField = "";

        if (content.contains("分组") && (content.contains("通过") || content.contains("根据") || content.contains("使用"))){

        }
        return groupField;
    }

    /**
     * 提取排序字段
     * @param content
     * @return
     */
    public static List<Sort> extractSortField(String content){
        if (StringUtil.isBlank(content)){
            return null;
        }
        content = checkSort(content);
        List<Sort> sortList = new ArrayList<>();
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
                sort.setColumnName(TableMap.map.get(word.LEMMA));
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

    private static String checkGroup(String content){
        if (StringUtil.isBlank(content)){
            return "";
        }
        if (content.contains("通过") && content.contains("分组")){
            content = content.substring(content.indexOf("通过"), content.lastIndexOf("分组") + 2);
        } else if (content.contains("根据") && content.contains("分组")){
            content = content.substring(content.indexOf("根据"), content.lastIndexOf("分组") + 2);
        } else if (content.contains("使用") && content.contains("分组")){
            content = content.substring(content.indexOf("使用"), content.lastIndexOf("分组") + 2);
        } else if (content.contains("使") && content.contains("分组")){
            content = content.substring(content.indexOf("使"), content.lastIndexOf("分组") + 2);
        } else if (content.contains("让") && content.contains("分组")){
            content = content.substring(content.indexOf("让"), content.lastIndexOf("分组") + 2);
        }

        return content;
    }

     private static String checkSort(String content){
         if (StringUtil.isBlank(content)){
             return "";
         }
         if (content.contains("通过") && content.contains("排序")){
             content = content.substring(content.indexOf("通过"), content.lastIndexOf("排序") + 2);
         } else if (content.contains("根据") && content.contains("排序")){
             content = content.substring(content.indexOf("根据"), content.lastIndexOf("排序") + 2);
         } else if (content.contains("使用") && content.contains("排序")){
             content = content.substring(content.indexOf("使用"), content.lastIndexOf("排序") + 2);
         } else if (content.contains("使")){
             content = content.substring(content.indexOf("使"), content.lastIndexOf("排序") + 2);
         } else if (content.contains("让")){
             content = content.substring(content.indexOf("让"), content.lastIndexOf("排序") + 2);
         }
         return content;
     }

    private static String[] checkOr(String content){
        String[] contentArr = new String[]{};
        if (StringUtil.isBlank(content)){
            return null;
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
