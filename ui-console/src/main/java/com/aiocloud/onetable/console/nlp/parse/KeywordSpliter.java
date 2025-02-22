package com.aiocloud.onetable.console.nlp.parse;

import com.aiocloud.onetable.console.utils.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther ybin
 * 关键信息分离器 分离排序语句、分组语句、条数限制语句
 */
public class KeywordSpliter {

    public static final String[][] groupSplitRanges = new String[][]{
            {"通过", "分组"},{"根据", "分组"},{"使用", "分组"},{"使", "分组"},{"让", "分组"}
    };

    public static final String[][] sortSplitRanges = new String[][]{
            {"通过", "排序"},{"根据", "排序"},{"使用", "排序"},{"使", "排序"},{"让", "排序"}
    };

    public static final String limitRegex = "(显示|需要|展示|查询|取|获取|给我|找出|找|查出|查)+[0-9]+[条行]";

    /**
     * 截取分组部分的文本
     * @param content
     * @return
     */
    public static String splitGroupText(String content){
        return split(content, groupSplitRanges);
    }

    /**
     * 截取排序部分的文本
     * @param content
     * @return
     */
    public static String splitSortText(String content){
        return split(content, sortSplitRanges);
    }

    /**
     * 截取limit部分的文本
     * @param content
     * @return
     */
    public static String splitLimitText(String content){
        if (StringUtil.isBlank(content)){
            return "";
        }
        Pattern pattern = Pattern.compile(limitRegex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()){
            return matcher.group().replaceAll("\\D", "");
        }
        return "";
    }

    public static String split(String content, String[][] ranges){
        if (StringUtil.isBlank(content) || ranges == null || ranges.length == 0){
            return "";
        }
        for (String[] range : ranges) {
            if (content.contains(range[0]) && content.contains(range[1])){
                return content.substring(content.indexOf(range[0]), content.lastIndexOf(range[1]) + range[1].length());
            }
        }
        return "";
    }
}
