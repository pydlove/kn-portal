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
            {"通过", "分组"},{"根据", "分组"},{"使用", "分组"},{"使", "分组"},{"让", "分组"},
            {"通过", "统计"},{"根据", "统计"},{"使用", "统计"},{"使", "统计"},{"让", "统计"},
            {"统计", "趋势"},{"统计", "走势"},{"统计", "比列"},{"统计", "比例"},{"统计", "占比"},
            {"统计", "情况"}
    };

    public static final String[][] sortSplitRanges = new String[][]{
            {"通过", "排序"},{"根据", "排序"},{"使用", "排序"},{"使", "排序"},{"让", "排序"}
    };

    public static final String limitRegex = "(显示|显示前|显示后|显示最后|需要|需要前|需要后|需要最后|展示|展示前|展示后|展示最后|查询|查询前|查询后|查询最后|取|取前|取后|取最后|获取|获取前|获取后|获取最后|给我|给我前|给我后|给我最后|找出|找出前|找出后|找出最后|找|找前|找后|找最后|查出|查出前|查出后|查出最后|查|查前|查后|查最后)+[0-9]+[条行]";
    private static final String ageRegex = "[0-9零一二贰两三叁四肆五伍六陆七柒八捌九玖十拾百佰千仟万萬亿]+[岁]";
    private static final String zhcnNumRegex = "[零一壹二贰两三叁四肆五伍六陆七柒八捌九玖十拾百佰千仟万萬亿]+";

    public static void main(String[] args) {
        String text = "查询十八岁,二十七岁";
        Pattern pattern = Pattern.compile(zhcnNumRegex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(NumberConverter.convert("十二"));
        }
    }

    /**
     * 处理年龄字段
     * @param content
     * @return
     */
    public static String handleAgeText(String content){
        if (content.contains("年龄")){
            return content;
        }
        Pattern pattern = Pattern.compile(ageRegex);
        Matcher matcher = pattern.matcher(content);
        if (!matcher.find()){
            return content;
        }
        String ageGroup = matcher.group();
        Pattern numPattern = Pattern.compile(zhcnNumRegex);
        Matcher numMatcher = numPattern.matcher(ageGroup);
        String ageText = ageGroup;
        if (numMatcher.find()){
            long number = NumberConverter.convert(numMatcher.group());
            ageText = String.valueOf(number);
        }
        if (content.contains("大于" + ageGroup) || content.contains(ageGroup + "以上")
                || content.contains(ageGroup + "以后") || content.contains(ageGroup + "后")){
            content = content.replace("大于" + ageGroup, "年龄大于" + ageText)
                    .replace(ageGroup + "以上", "年龄大于" + ageText)
                    .replace(ageGroup + "以后", "年龄大于" + ageText)
                    .replace(ageGroup + "后", "年龄大于" + ageText);
        } else if (content.contains("小于" + ageGroup) || content.contains(ageGroup + "以下")
                || content.contains(ageGroup + "以前") || content.contains(ageGroup + "前")){
            content = content.replace("小于" + ageGroup, "年龄小于" + ageText)
                    .replace(ageGroup + "以下", "年龄小于" + ageText)
                    .replace(ageGroup + "以前", "年龄小于" + ageText)
                    .replace(ageGroup + "前", "年龄小于" + ageText);
        } else {
            content = content.replace(ageGroup, "年龄等于" + ageText);
        }
        return content;
    }

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
