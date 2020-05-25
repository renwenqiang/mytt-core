package com.boot.mytt.core.util;


import com.boot.mytt.core.exception.UtilException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Deprecated
public class DateUtils {

    public static final String formdate = "yyyy-MM-dd";

    public static final String formdatetime1 = "yyyy-MM-dd HH:mm:ss";

    public static final String formdatetime2 = "yyyyMMddHHmmss";

    public static final String formdatetime3 = "yyyyMMdd";

    public static final String formdatetime4 = "yyyyMM";

    public static final String formdatetime5 = "HHmmss";

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取当前日期
     *
     * @param type 格式
     * @return 当前日期字符串
     */
    public static String getCurrentDate(String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(new Date());
    }

    /**
     * 日期转换成字符串
     *
     * @param date 日期
     * @param type 格式
     * @return 日期字符串
     */
    public static String dateToString(Date date, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    /**
     * 字符串转换日期
     *
     * @param str  日期字符串
     * @param type 格式
     * @return 日期
     */
    public static Date stringToDate(String str, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        if (!str.equals("") && null != str) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                throw new UtilException("date format fail");
            }
        }
        return null;
    }

    /**
     * 获取前几天的日期
     *
     * @param count 天数
     * @return 日期
     */
    public static String getPrefixDate(String count) {
        Calendar cal = Calendar.getInstance();
        int day = 0 - Integer.parseInt(count);
        cal.add(Calendar.DATE, day);   // int amount   代表天数
        Date datNew = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formdate);
        return format.format(datNew);
    }

    /**
     * 比较日期大小
     *
     * @param date1 日期
     * @param date2 日期
     * @return 大于返回1 相等返回0 小于返回-1
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 日期
     * @param date2 日期
     * @return 相差天数
     */
    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 增加天数
     *
     * @param date 日期
     * @param num  变动天数
     * @return 变动后的日期
     */
    public static Date addDate(Date date, int num) {
        if (null == date) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        //设置当前日期
        c.setTime(date);
        //日期加num天
        c.add(Calendar.DATE, num);
        date = c.getTime();
        return date;
    }

    /**
     * 增加月数
     *
     * @param date 日期
     * @param num  变动月数
     * @return 变动后的日期
     */
    public static Date addMonth(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, num);
        date = c.getTime();
        return date;
    }


    /**
     * 增加分钟数
     *
     * @param date 日期
     * @param num  变动分钟数
     * @return 变动后的日期
     */
    public static Date addMinute(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, num);
        date = c.getTime();
        return date;
    }

    /**
     * 获取年
     *
     * @param date 日期
     * @return 年
     */
    public static int year(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @param date 日期
     * @return 月份
     */
    public static int month(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }


    /**
     * 获取日
     *
     * @param date 日期
     * @return 日
     */
    public static int day(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }
}

