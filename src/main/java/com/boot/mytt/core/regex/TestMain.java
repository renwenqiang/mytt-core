package com.boot.mytt.core.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renwq
 * @since 2020/7/10 19:52
 */
public class TestMain {
    public static void main(String[] args) {
        String str = "java is very easy!";
        System.out.printf("目标字符串是 %s %n", str);
        Matcher m = Pattern.compile("\\w+").matcher(str);
        while (m.find()) {
            System.out.printf("子串是 %s 起始位置 %s 结束位置 %s %n", m.group(), m.start(), m.end());
        }
    }
}
