package com.wxs.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义String工具类
 *
 * @author wxs
 * @date 2019/5/29 9:27
 */
public class StringUtil extends StringUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public StringUtil() {
        super();
    }

    /**
     * 下划线转驼峰
     *
     * @param str 下划线分隔的变量
     * @return
     */
    public static String underlineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 驼峰格式的变量
     * @return
     */
    public static String humpToUnderline(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 忽略like查询是条件里面的%和_
     *
     * @param str 查询条件
     * @return 过滤掉%和_
     */
    public static String escapeLikeString(String str) {
        if (isNotBlank(str)) {
            str = str.trim().replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
        }
        return str;
    }

    /**
     * 把Object转化为String类型。
     * 如果Object为空返回空字符串。
     *
     * @param obj 需要转换的Object
     * @return 如果Object为空返回空字符串，否则直接返回trim之后的字符串。
     */
    public static String castToStr(Object obj) {
        if (null == obj) {
            return "";
        } else {
            return String.valueOf(obj).trim();
        }
    }

    /**
     * 清除字符串的中括号
     *
     * @param str
     * @return
     */
    public static String clearSquareBrackets(String str) {
        if (isNotBlank(str)) {
            str = str.trim().replace("[", "").replace("]", "");
        }
        return str.trim();
    }

    /**
     * 把逗号分隔的字符串转化为List
     *
     * @param str
     * @return
     */
    public static List<String> castToList(String str) {
        List<String> list = new ArrayList<>();
        if (!isEmpty(str) && str.length() > 0 && str.contains(",")) {
            String[] strArr = str.split(",");
            if (strArr != null && strArr.length > 0) {
                for (String shortStr : strArr) {
                    list.add(shortStr.trim());
                }
            }
        }

        if (!isEmpty(str) && !list.isEmpty()) {
            list.add(str);
        }
        return list;
    }
}
