package com.boot.mytt.core.util;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class ArrayUtils {

    private ArrayUtils() {

    }

    /**
     * array is not empty
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length > 0;
    }

    /**
     * array is empty
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
