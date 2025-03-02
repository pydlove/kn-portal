package com.aiocloud.onetable.console.nlp.parse;

import java.util.HashMap;
import java.util.Map;

public class NumberConverter {

    private static final Map<Character, Integer> digitMap = new HashMap<>();
    private static final Map<Character, Long> unitMap = new HashMap<>();

    private static char[] cnArr_a = new char [] {'零','一','二','三','四','五','六','七','八','九'};
    private static char[] cnArr_A = new char [] {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
    private static char two = '两';
    private static char[] unit_a = new char [] {'十','百','千','万','亿'};
    private static char[] unit_A = new char [] {'拾','佰','仟','萬','亿'};
    
    static {
        // 初始化数字映射
        digitMap.put(two, 2);
        for (int i = 0; i < 9; i++) {//digitMap.put('九', 9);
            digitMap.put(cnArr_a[i], i);
            digitMap.put(cnArr_A[i], i);
        }
        for (int i = 0; i < 4; i++) {//unitMap.put('十', 10L);
            unitMap.put(unit_a[i], (long)Math.pow(10L, i + 1));
        }
    }
    public static long convert(String chineseNumber) {
        if (chineseNumber == null || chineseNumber.isEmpty()) {
            return 0;
        }
        // 处理“亿”单位
        int yiIndex = chineseNumber.indexOf('亿');
        if (yiIndex != -1) {
            String beforeYi = chineseNumber.substring(0, yiIndex);
            String afterYi = chineseNumber.substring(yiIndex + 1);
            return convert(beforeYi) * 100000000L + convert(afterYi);
        }
        // 处理“万”单位
        int wanIndex = chineseNumber.indexOf('万');
        if (wanIndex != -1) {
            String beforeWan = chineseNumber.substring(0, wanIndex);
            String afterWan = chineseNumber.substring(wanIndex + 1);
            return convert(beforeWan) * 10000L + convert(afterWan);
        }
        // 处理十、百、千等单位
        return parseSection(chineseNumber);
    }

    private static long parseSection(String section) {
        long currentValue = 0;
        long currentNumber = 0;

        for (int i = 0; i < section.length(); i++) {
            char c = section.charAt(i);
            if (digitMap.containsKey(c)) {
                currentNumber = digitMap.get(c);
            } else if (unitMap.containsKey(c)) {
                long unit = unitMap.get(c);
                currentValue += (currentNumber == 0 ? 1 : currentNumber) * unit;
                currentNumber = 0;
            } else if (c == '零') {
                currentNumber = 0; // 处理零，后面的数字会覆盖
            }
        }
        currentValue += currentNumber; // 添加最后的数字
        return currentValue;
    }

    public static void main(String[] args) {
        NumberConverter converter = new NumberConverter();
        String[] testCases = {
                "三千三百三十二亿二千一百三十二万八千四百九十五","两千一百三十二万八千四百九十五"
        };

        for (String test : testCases) {
            System.out.printf("%s → %d\n", test, converter.convert(test));
        }
    }
}
