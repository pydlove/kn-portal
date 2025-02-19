package com.aiocloud.onetable.console.nlp.cache;

import com.hankcs.hanlp.dictionary.CustomDictionary;

/**
 * @auther ybin
 */
public class Dictionary {

    public static void init(){
        CustomDictionary.add("select","select");
        CustomDictionary.add("查询","select");
        CustomDictionary.add("查询出","select");
        CustomDictionary.add("显示","select");
        CustomDictionary.add("显示出","select");
        CustomDictionary.add("展示","select");
        CustomDictionary.add("展示出","select");
        CustomDictionary.add("查找","select");
        CustomDictionary.add("查找出","select");
        CustomDictionary.add("查出","select");
        CustomDictionary.add("找出","select");
        CustomDictionary.add("搜出","select");
        CustomDictionary.add("检出","select");
        CustomDictionary.add("搜索","select");
        CustomDictionary.add("搜索出","select");
        CustomDictionary.add("检索","select");
        CustomDictionary.add("检索出","select");
        CustomDictionary.add("帅选","select");
        CustomDictionary.add("帅选出","select");

        CustomDictionary.add("where","where");
        CustomDictionary.add("跟据","where");
        CustomDictionary.add("依据","where");
        CustomDictionary.add("条件","where");
        CustomDictionary.add("条件是","where");
        CustomDictionary.add("条件为","where");
        CustomDictionary.add("当","where");

        CustomDictionary.add("=","operator");
        CustomDictionary.add("为","operator");
        CustomDictionary.add("是","operator");
        CustomDictionary.add("等于","operator");
        CustomDictionary.add("!=","operator");
        CustomDictionary.add("不等于","operator");
        CustomDictionary.add("不为","operator");
        CustomDictionary.add("不是","operator");
        CustomDictionary.add(">","operator");
        CustomDictionary.add("大于","operator");
        CustomDictionary.add("大过","operator");
        CustomDictionary.add("之后","operator");
        CustomDictionary.add("<","operator");
        CustomDictionary.add("小于","operator");
        CustomDictionary.add("小过","operator");
        CustomDictionary.add("之前","operator");
        CustomDictionary.add(">=","operator");
        CustomDictionary.add("大于等于","operator");
        CustomDictionary.add("大于或等于","operator");
        CustomDictionary.add("<=","operator");
        CustomDictionary.add("小于等于","operator");
        CustomDictionary.add("小于或等于","operator");

        CustomDictionary.add("and","and");
        CustomDictionary.add("且","and");
        CustomDictionary.add("并且","and");
        CustomDictionary.add("以及","and");

        CustomDictionary.add("group by","group");
        CustomDictionary.add("分组","group");
        CustomDictionary.add("统计","group");

        CustomDictionary.add("order by","sort");
        CustomDictionary.add("升序","sort");
        CustomDictionary.add("降序","sort");
        CustomDictionary.add("排序","sort");

        CustomDictionary.add("or","or");
        CustomDictionary.add("或","or");
        CustomDictionary.add("或者","or");
    }
}
