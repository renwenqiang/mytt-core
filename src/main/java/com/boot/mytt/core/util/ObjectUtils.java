package com.boot.mytt.core.util;

import com.boot.mytt.core.exception.UtilException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class ObjectUtils {

    private ObjectUtils() {

    }

    /**
     * 对象copy
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    泛型
     * @return 返回对象
     */
    public static <T> T copy(Object source, Class<T> target) {
        try {
            if (!Objects.isNull(source) && !Objects.isNull(target)) {
                T t = target.newInstance();
                BeanUtils.copyProperties(t, source);
                return t;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UtilException("object copy fail", e);
        }
    }

    /**
     * 对象copy
     *
     * @param list   源对象
     * @param target 目标对象
     * @param <S>    键泛型
     * @param <T>    值泛型
     * @return 返回对象
     */
    public static <S, T> List<T> copy(List<S> list, Class<T> target) {
        try {
            if (CollectionUtils.isNotEmpty(list) && !Objects.isNull(target)) {
                List<T> returnList = new ArrayList<>();
                for (Object source : list) {
                    if (null != source) {
                        T t = copy(source, target);
                        if (t != null) {
                            returnList.add(t);
                        }
                    }
                }
                return returnList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new UtilException("object copy fail", e);
        }
    }

    /**
     * map转换成object
     *
     * @param map map对象
     * @param cla 目标对象class
     * @return 返回对象
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> cla) {
        try {
            if (Objects.isNull(map)) {
                return null;
            }
            Object obj = cla.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
            return obj;
        } catch (Exception e) {
            throw new UtilException("map to object fail", e);
        }
    }

    /**
     * object转换成map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            Map<String, Object> map = new HashMap<>();
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
            return map;
        } catch (Exception e) {
            throw new UtilException("object to map fail", e);
        }
    }

    /**
     * 调用对象get方法
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getValue(Object object, String fieldName) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
            Method method = object.getClass().getMethod(sb.toString());
            method.setAccessible(false);
            return method.invoke(object);
        } catch (Exception e) {
            throw new UtilException("object get fail", e);
        }
    }

    /**
     * 调用对象set方法
     *
     * @param object
     * @param fieldName
     * @param value
     * @return
     */
    public static void setValue(Object object, String fieldName, Object value) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
            String str = sb.toString();
            Method method = object.getClass().getMethod(str, value.getClass());
            method.setAccessible(false);
            method.invoke(object, value);
        } catch (Exception e) {
            throw new UtilException("object set fail", e);
        }
    }
}
