package com.boot.mytt.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author renwq
 * @date 2020/5/25
 */
@Deprecated
public class FastJsonUtils {

    /**
     * json字符串转map集合
     *
     * @param jsonStr json字符串
     * @return map
     */
    public static HashMap<String, String> json2Map(String jsonStr) {
        return JSON.parseObject(jsonStr, new HashMap<String, String>().getClass());
    }

    /**
     * map转json字符串
     *
     * @param map map
     * @return json字符串
     */
    public static String map2Json(Map<String, String> map) {
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }

    /**
     * json字符串转换成对象
     *
     * @param jsonStr json字符串
     * @param cls     对象 class
     * @param <T>     泛型
     * @return 对象
     */
    public static <T> T json2Bean(String jsonStr, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonStr, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转换成List集合
     *
     * @param jsonStr json字符串
     * @param cls     对象class
     * @param <T>     泛型
     * @return list对象
     */
    public static <T> List<T> json2List(String jsonStr, Class cls) {
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonStr, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json字符串转换成ArrayList集合
     *
     * @param jsonStr json字符串
     * @param cls     对象class
     * @param <T>     泛型
     * @return list对象
     */
    public static <T> ArrayList<T> json2ArrayList(String jsonStr, Class cls) {
        ArrayList<T> list = null;
        try {
            list = (ArrayList<T>) JSON.parseArray(jsonStr, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * List集合转换成json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String list2Json(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }

    /**
     * json转List
     *
     * @param jsonStr json 字符串
     * @return JSONArray
     */
    public static JSONArray json2List(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }

}

