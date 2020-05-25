package com.boot.mytt.core.util;

import java.util.UUID;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class UUIDUtils {

    private UUIDUtils() {

    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取全字母UUID
     *
     * @return
     */
    public static String getLetterUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
