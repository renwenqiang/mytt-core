package com.boot.mytt.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author renwq
 * @date 2020/5/25
 */
public class RegexUtils {

    /**
     * 编译一个正则表达式
     *
     * @param regex 规则
     * @return
     */
    public static Pattern compile(String regex, boolean isInsensitive) {
        if (true == isInsensitive) {
            return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            return Pattern.compile(regex);
        }
    }

    /**
     * 返回一个mathcer
     *
     * @param regex 规则
     * @param str   字符串
     * @return
     */
    public static Matcher matcher(String regex, String str) {
        return compile(regex, true).matcher(str);
    }

    /**
     * 匹配第一个
     *
     * @param regex 规则
     * @param str   字符串
     * @return
     */
    public static String match(String regex, String str) {
        String match = null;
        Matcher m = matcher(regex, str);
        while (m.find()) {
            match = m.group().trim();
            break;
        }
        return match;
    }

    /**
     * 匹配返回所有
     *
     * @param regex 规则
     * @param str   字符串
     * @return
     */
    public static List<String> matchAll(String regex, String str) {
        List<String> list = new ArrayList<String>();
        Matcher m = matcher(regex, str);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 判断是否匹配，返回布尔值
     *
     * @param regex 规则
     * @param str   字符串
     * @return true:匹配 false:不匹配
     */
    public static boolean matches(String regex, String str) {
        Matcher matcher = matcher(regex, str);
        return matcher.matches();
    }

    /**
     * 替换所有匹配到的字符串
     *
     * @param regex       规则
     * @param str         字符串
     * @param replacement 替换字符串
     * @return 替换之后字符串
     */
    public static String replaceAll(String regex, String str, String replacement) {
        Matcher matcher = matcher(regex, str);
        return matcher.replaceAll(replacement);
    }

    /**
     * 替换第一个匹配到的字符串
     *
     * @param regex       规则
     * @param str         字符串
     * @param replacement 替换字符串
     * @return 替换之后字符串
     */
    public static String replaceFirst(String regex, String str, String replacement) {
        Matcher matcher = matcher(regex, str);
        return matcher.replaceFirst(replacement);
    }
}
