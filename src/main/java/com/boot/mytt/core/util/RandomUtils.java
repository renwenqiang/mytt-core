package com.boot.mytt.core.util;

import java.util.Random;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class RandomUtils {

    private final static String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final static String member = "0123456789";

    private RandomUtils() {

    }

    /**
     * 产生指定长度的随机字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 产生指定长度内的随机长度随机字符串
     *
     * @param min 最小
     * @param max 最大
     * @return 随机字符串
     */
    public static String getRandomString(int min, int max) {
        Random random = new Random();
        return getRandomString(random.nextInt(max - min) + min);
    }

    /**
     * 产生指定长度的随机数字
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String getRandomMember(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(member.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 产生指定长度内的随机长度随机字符串
     *
     * @param min 最小
     * @param max 最大
     * @return 随机字符串
     */
    public static String getRandomMember(int min, int max) {
        Random random = new Random();
        return getRandomString(random.nextInt(max - min) + min);
    }
}
