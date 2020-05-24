package com.boot.mytt.core.util;

public class ArrayUtils {

    private ArrayUtils() {

    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return  !isEmpty(array);
    }
}
