package com.aiocloud.onetable.console.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther ybin
 */
public class StringUtil {

	private static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

	/**
	 * isNotEmpty:判断字符串不能为空
	 * @param args 要检查的字符串参数
	 */
	public static boolean isNotEmpty(String args) {
		if (args != null && args != "" && args.trim().length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * isAllNotEmpty:判断所有传入参数都不能为空
	 * @param args 要判断的参数数组
	 */
	public static boolean isAllNotEmpty(String... args) {
		boolean check = true;
		if (args != null) {
			for (String arg : args) {
				if (arg == null || arg == "" || arg.trim().length() <= 0) {
					check = false;
					return false;
				}
			}
		}
		return check;
	}

	/**
	 * isEmpty:判断字符串为空
	 * @param s 要判断的字符串
	 */
	public static boolean isEmpty(String s) {
		if (s == null || "".equals(s) || s.length() == 0){
			return true;
		}
		return false;
	}

	/**
	 * isEmpty:判断字符串为空
	 * @param str 要判断的字符串
	 */
	public static boolean isBlank(CharSequence str) {
		int strLen;
		if (str != null && (strLen = str.length()) != 0) {
			for(int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	/**
	 * 判断是否包含中文
	 * @param value 内容
	 * @return 结果
	 */
	public static boolean containChinese(String value) {
		if (isBlank(value)) {
			return false;
		}
		Matcher matcher = CHINESE_PATTERN.matcher(value);
		return matcher.find();
	}

	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-","").toUpperCase();
	}

	/**
	 * 字符串左补齐（补齐符为单字符）
	 * @param length
	 * @param target
	 * @param pad
	 */
	public static String lpad(int length, String target, String pad){
		char[] chars = target.toCharArray();
		char cPad = pad.charAt(0);
		if (pad == null || "".equals(pad) || pad.length() != 1){
			throw new RuntimeException("无效的填充字符");
		}
		if (chars.length >= length){
			return target;
		}
		char[] result = new char[length];
		int num = length - chars.length;
		int j = 0;
		for (int i = 0; i < length; i++) {
			if (i < num){
				result[i] = cPad;
			} else {
				result[i] = chars[j];
				j ++;
			}
		}
		return String.valueOf(result);
	}

	/**
	 * 字符串右补齐（补齐符为单字符）
	 * @param length
	 * @param target
	 * @param pad
	 */
	public static String rpad(int length, String target, String pad){
		char[] chars = target.toCharArray();
		char cPad = pad.charAt(0);
		if (pad == null || "".equals(pad) || pad.length() != 1){
			throw new RuntimeException("无效的填充字符");
		}
		if (chars.length >= length){
			return target;
		}
		char[] result = new char[length];
		int j = 0;
		for (int i = 0; i < length; i++) {
			if (i < chars.length){
				result[i] = chars[j];
				j ++;
			} else if (chars.length < length){
				result[i] = cPad;
			}
		}
		return String.valueOf(result);
	}

	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{}
	 * \\需要第一个替换，否则replace方法替换时会有逻辑bug
	 */
	public static String escape(String str) {
	    if(StringUtil.isBlank(str)){
			   return str;
	    }

	    return str.replace("\\", "\\\\").replace("*", "\\*")
			.replace("+", "\\+").replace("|", "\\|")
			.replace("{", "\\{").replace("}", "\\}")
			.replace("(", "\\(").replace(")", "\\)")
			.replace("^", "\\^").replace("$", "\\$")
			.replace("[", "\\[").replace("]", "\\]")
			.replace("?", "\\?").replace(",", "\\,")
			.replace(".", "\\.").replace("&", "\\&");
	}

	/**
	 * 替换最后一个字符串
	 * @param str
	 * @param strToReplace
	 * @param replaceWithThis
	 * @return
	 */
	public static String replaceLast(String str, String strToReplace, String replaceWithThis) {
		return str.replaceFirst("(?s)" + strToReplace + "(?!.*?" + strToReplace + ")", replaceWithThis);
	}

	/**
	 * 截取数字
	 * @param str
	 */
	public static String getNumbers(String str) {
		if (StringUtil.isBlank(str)){
			return null;
		}
		Matcher matcher = Pattern.compile("[^0-9]").matcher(str);
		return matcher.replaceAll("").trim();
	}

	/**
	 * 字符串是否是纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		if (isBlank(str)){
			return false;
		}
		return str.matches("[0-9]+");
	}
}