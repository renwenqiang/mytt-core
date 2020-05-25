package com.boot.mytt.core.util.objectcopier;

import com.boot.mytt.core.exception.UtilException;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class ObjectCopierUtils {

    /**
     * BeanCopier的缓存
     */
    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * 对象转换
     */
    static final ObjectCopierConverter converter = new ObjectCopierConverter();

    /**
     * 对象copy
     *
     * @param source 源文件的
     * @param target 目标文件
     */
    public static <T> T copy(Object source, Class<T> target) {
        try {
            if (!Objects.isNull(source) && !Objects.isNull(target)) {
                T t = target.newInstance();
                String key = genKey(source.getClass(), target);
                BeanCopier beanCopier;
                if (BEAN_COPIER_CACHE.containsKey(key)) {
                    beanCopier = BEAN_COPIER_CACHE.get(key);
                } else {
                    beanCopier = BeanCopier.create(source.getClass(), target, true);
                    BEAN_COPIER_CACHE.put(key, beanCopier);
                }
                beanCopier.copy(source, t, converter);
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
     * 生成key
     *
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     * @return string
     */
    private static String genKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + tgtClazz.getName();
    }
}
