package com.boot.mytt.core.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class LocalDateUtils {

    private void DateTimeUtil() {

    }

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");

    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter SHORT_YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter LONG_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    public static final DateTimeFormatter DATETIME_FORMATTER1 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    /**
     * 返回当前的日期
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 格式化 LocalDate String
     *
     * @param localDate date
     * @param formatter formatter
     * @return String
     */
    public static String formatLocalDate(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * 格式化 LocalDateTime String
     *
     * @param localDateTime localDateTime
     * @param formatter     formatter
     * @return String
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    /**
     * 格式化 LocalTime String
     *
     * @param localTime localTime
     * @param formatter formatter
     * @return String
     */
    public static String formatLocalTime(LocalTime localTime, DateTimeFormatter formatter) {
        return localTime.format(formatter);
    }

    /**
     * 格式化  LocalDate
     *
     * @param localDateStr localDateStr
     * @param formatter    formatter
     * @return LocalDate
     */
    public static LocalDate parseLocalDate(String localDateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(localDateStr, formatter);
    }

    /**
     * 格式化 LocalDateTime
     *
     * @param localDateTimeStr localDateTimeStr
     * @param formatter        formatter
     * @return localDateTime
     */
    public static LocalDateTime parseLocalDateTime(String localDateTimeStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(localDateTimeStr, formatter);
    }

    /**
     * 格式化 LocalTime
     *
     * @param localTimeStr localTimeStr
     * @param formatter    formatter
     * @return
     */
    public static LocalTime parseLocalTime(String localTimeStr, DateTimeFormatter formatter) {
        return LocalTime.parse(localTimeStr, formatter);
    }

    /**
     * 日期相隔天数
     */
    public static long periodDays(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 日期相隔周数
     */
    public static long periodWeeks(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.WEEKS);
    }

    /**
     * 日期相隔月数
     */
    public static long periodMonths(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.MONTHS);
    }

    /**
     * 日期相隔年数
     */
    public static long periodYears(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.YEARS);
    }

    /**
     * 是否当天
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    /**
     * 获取毫秒数
     */
    public static Long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取毫秒数
     */
    public static Long toEpochMilli(LocalDate date) {
        return date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * Date转换为LocalTime
     *
     * @param date
     */
    public static LocalTime date2LocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }

    /**
     * LocalTime转换为Date
     *
     * @param localDate
     * @param localTime
     * @return
     */
    public static Date LocalTime2Date(LocalDate localDate, LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * Date转换为LocalDate
     *
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * LocalDate转换为Date
     *
     * @param localDate
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
}
