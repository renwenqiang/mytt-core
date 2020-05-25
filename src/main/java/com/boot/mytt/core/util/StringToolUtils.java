package com.boot.mytt.core.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class StringToolUtils {


    private static final String letter = ".*[a-zA-Z].*";

    /**
     * 判断一个字符串去掉前后空格之后为空
     *
     * @param s
     * @return
     */
    public static boolean isBlankTrim(String s) {
        return !isNotBlankTrim(s);
    }

    /**
     * s 为空时返回"",不为空返回 s
     * @param s
     * @return
     */
    public static String getBlankStr(String s){
        if(isBlankTrim(s)){
            return "";
        }else{
            return s;
        }
    }

    /**
     * 判断一个字符串去掉前后空格之后为非空
     *
     * @param s
     * @return
     */
    public static boolean isNotBlankTrim(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 是否包含字母
     *
     * @param value
     * @return
     */
    public static boolean hasLetter(String value) {
        return Pattern.matches(letter, value);
    }

    /**
     * 手机号打码, 如 136****1234
     *
     * @param mobile
     * @return
     */
    public static String mosaicMobile(String mobile) {
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证打码, 如 2208****3304
     *
     * @param idCard
     * @return
     */
    public static String mosaicIdCard(String idCard) {
        return idCard.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 是否有中文字符
     *
     * @param s
     * @return
     */
    public static boolean hasCn(String s) {
        if (s == null) {
            return false;
        }
        return countCn(s) > s.length();
    }

    /**
     * 计算GBK编码的字符串的字节数
     *
     * @param s
     * @return
     */
    public static int countCn(String s) {
        if (s == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.codePointAt(i) < 256) {
                count++;
            } else {
                count += 2;
            }
        }
        return count;
    }

    /**
     * 获得字符。符合中文习惯。如果字符串超出长度则用...代替
     *
     * @param s      字符串
     * @param len 长度限制
     * @return
     */
    public static String getCn(String s, int len) {
        if (s == null) {
            return s;
        }
        int sl = s.length();
        if (sl <= len) {
            return s;
        }
        // 留出一个位置用于…
        len -= 1;
        int maxCount = len * 2;
        int count = 0;
        int i = 0;
        while (count < maxCount && i < sl) {
            if (s.codePointAt(i) < 256) {
                count++;
            } else {
                count += 2;
            }
            i++;
        }
        if (count > maxCount) {
            i--;
        }
        return s.substring(0, i) + "…";
    }

    /**
     * 替换字符串，全部替换
     *
     * @param subject 源串
     * @param find    要替换掉的内容
     * @param replace 替换内容
     * @return
     */
    public static String replace(String subject, String find, String replace) {

        StringBuffer buf = new StringBuffer();
        int l = find.length();
        int s = 0;
        int i = subject.indexOf(find);
        while (i != -1) {
            buf.append(subject.substring(s, i));
            buf.append(replace);
            s = i + l;
            i = subject.indexOf(find, s);
        }
        buf.append(subject.substring(s));
        return buf.toString();
    }

    /**
     * 将object对象转换成string
     *
     * @param obj
     * @return
     */
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String stripHtml(String html) {
        if (isBlankTrim(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }



}
