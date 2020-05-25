package com.boot.mytt.core.util.objectcopier;

import com.boot.mytt.core.util.LocalDateUtils;
import net.sf.cglib.core.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class ObjectCopierConverter implements Converter {

    /**
     * 自定义属性转换
     *
     * @param value   源对象属性类
     * @param target  目标对象里属性对应set方法名,eg.setId
     * @param context 目标对象属性类
     * @return
     */
    @Override
    public Object convert(Object value, Class target, Object context) {
        if (value instanceof LocalDateTime && target.equals(Date.class)) {
            return LocalDateUtils.localDateTime2Date((LocalDateTime) value);
        } else if (value instanceof LocalDate && target.equals(Date.class)) {
            return LocalDateUtils.localDate2Date((LocalDate) value);
        }
        return value;
    }
}
