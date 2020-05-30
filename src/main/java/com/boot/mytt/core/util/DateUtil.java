package com.boot.mytt.core.util;

public class DateUtil {
    public static void main(String[] args) {

//        LocalDateTime date1 = LocalDateTime.now();
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
//        String date2Str = formatter2.format(date1);
//        System.out.println(date2Str);
//
//        //使用jdk自身配置好的日期格式
//        DateTimeFormatter formatter1 = DateTimeFormatter.ISO_DATE_TIME;
//        LocalDateTime date1 = LocalDateTime.now();
////反过来调用也可以 : date1.format(formatter1);
//        String date1Str = formatter1.format(date1);
//        System.out.println(date1Str);

//        Instant ins1 = Instant.now();
//        for (int i = 0; i < 10000000; i++) {
//            //循环一百万次
//        }
//        Instant ins2 = Instant.now();
//        Duration duration = Duration.between(ins1, ins2);
//        System.out.println("程序运行耗时为 ： " + duration.toMillis() + "毫秒");

//        //计算两个日期的日期间隔-年月日
//        LocalDate date1 = LocalDate.of(2018, 2, 13);
//        LocalDate date2 = LocalDate.of(2017, 3, 12);
////内部是用date2-date1，所以得到的结果是负数
//        Period period = Period.between(date1, date2);
//        System.out.println("相差年数 ： " + period.getYears());
//        System.out.println("相差月数 ： " + period.getMonths());
//        System.out.println("相差日数 ： " + period.getDays());
////还可以这样获取相差的年月日
//        System.out.println("-------------------------------");
//        long years = period.get(ChronoUnit.YEARS);
//        long months = period.get(ChronoUnit.MONTHS);
//        long days = period.get(ChronoUnit.DAYS);
//        System.out.println("相差的年月日分别为 ： " + years + "," + months + "," + days);
////注意，当获取两个日期的间隔时，并不是单纯的年月日对应的数字相加减，而是会先算出具体差多少天，在折算成相差几年几月几日的
//
////计算两个时间的间隔
//        System.out.println("-------------------------------");
//        LocalDateTime date3 = LocalDateTime.now();
//        LocalDateTime date4 = LocalDateTime.of(2018, 1, 13, 22, 30, 10);
//        Duration duration = Duration.between(date3, date4);
//        System.out.println(date3 + " 与 " + date4 + " 间隔  " + "\n"
//                + " 天 :" + duration.toDays() + "\n"
//                + " 时 :" + duration.toHours() + "\n"
//                + " 分 :" + duration.toMinutes() + "\n"
//                + " 毫秒 :" + duration.toMillis() + "\n"
//                + " 纳秒 :" + duration.toNanos() + "\n"
//        );
//注意，并没有获得秒差的，但既然可以获得毫秒，秒就可以自行获取了

//        Instant instant = Instant.now();
////2019-06-08T16:50:19.174Z
//        System.out.println(instant);
//        Date date = Date.from(instant);
//        Instant instant2 = date.toInstant();
////Sun Jun 09 00:50:19 CST 2019
//        System.out.println(date);
////2019-06-08T16:50:19.174Z
//        System.out.println(instant2);

//        //返回当前时间，根据系统时间和UTC
//        Clock clock = Clock.systemUTC();
//// 运行结果： SystemClock[Z]
//        System.out.println(clock);

//        LocalDate now = LocalDate.now();
//        System.out.println("now : " + now + ", is leap year ? " + now.isLeapYear());

        //判断两个时间点的前后
//        LocalDate localDate1 = LocalDate.of(2017, 8, 8);
//        LocalDate localDate2 = LocalDate.of(2018, 8, 8);
//        boolean date1IsBeforeDate2 = localDate1.isBefore(localDate2);
//        System.out.println("date1IsBeforeDate2 : " + date1IsBeforeDate2);
// date1IsBeforeDate2 == true

//        LocalDateTime localDateTime = LocalDateTime.now();
//        int dayOfYear = localDateTime.getDayOfYear();
//        int dayOfMonth = localDateTime.getDayOfMonth();
//        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
//        System.out.println("今天是" + localDateTime + "\n"
//                + "本年当中第" + dayOfYear + "天" + "\n"
//                + "本月当中第" + dayOfMonth + "天" + "\n"
//                + "本周中星期" + dayOfWeek.getValue() + "-即" + dayOfWeek + "\n");
//
////获取当天时间的年月日时分秒
//        int year = localDateTime.getYear();
//        Month month = localDateTime.getMonth();
//        int day = localDateTime.getDayOfMonth();
//        int hour = localDateTime.getHour();
//        int minute = localDateTime.getMinute();
//        int second = localDateTime.getSecond();
//        System.out.println("今天是" + localDateTime + "\n"
//                + "年 ： " + year + "\n"
//                + "月 ： " + month.getValue() + "-即 "+ month + "\n"
//                + "日 ： " + day + "\n"
//                + "时 ： " + hour + "\n"
//                + "分 ： " + minute + "\n"
//                + "秒 ： " + second + "\n"
//        );

//        LocalDate localDate = LocalDate.now();
////当前时间基础上，指定本年当中的第几天，取值范围为1-365,366
//        LocalDate withDayOfYearResult = localDate.withDayOfYear(200);
////当前时间基础上，指定本月当中的第几天，取值范围为1-29,30,31
//        LocalDate withDayOfMonthResult = localDate.withDayOfMonth(5);
////当前时间基础上，直接指定年份
//        LocalDate withYearResult = localDate.withYear(2017);
////当前时间基础上，直接指定月份
//        LocalDate withMonthResult = localDate.withMonth(5);
//        System.out.println("当前时间是 : " + localDate + "\n"
//                + "指定本年当中的第200天 : " + withDayOfYearResult + "\n"
//                + "指定本月当中的第5天 : " + withDayOfMonthResult + "\n"
//                + "直接指定年份为2017 : " + withYearResult + "\n"
//                + "直接指定月份为5月 : " + withMonthResult + "\n"
//        );

//        LocalDateTime localDateTime = LocalDateTime.now();
////以下方法的参数都是long型，返回值都是LocalDateTime
//        LocalDateTime plusYearsResult = localDateTime.plusYears(2L);
//        LocalDateTime plusMonthsResult = localDateTime.plusMonths(3L);
//        LocalDateTime plusDaysResult = localDateTime.plusDays(7L);
//        LocalDateTime plusHoursResult = localDateTime.plusHours(2L);
//        LocalDateTime plusMinutesResult = localDateTime.plusMinutes(10L);
//        LocalDateTime plusSecondsResult = localDateTime.plusSeconds(10L);
//
//        System.out.println("当前时间是 : " + localDateTime + "\n"
//                + "当前时间加2年后为 : " + plusYearsResult + "\n"
//                + "当前时间加3个月后为 : " + plusMonthsResult + "\n"
//                + "当前时间加7日后为 : " + plusDaysResult + "\n"
//                + "当前时间加2小时后为 : " + plusHoursResult + "\n"
//                + "当前时间加10分钟后为 : " + plusMinutesResult + "\n"
//                + "当前时间加10秒后为 : " + plusSecondsResult + "\n"
//        );
//
////也可以以另一种方式来相加减日期，即plus(long amountToAdd, TemporalUnit unit)
////                  参数1 ： 相加的数量， 参数2 ： 相加的单位
//        LocalDateTime nextMonth = localDateTime.plus(1, ChronoUnit.MONTHS);
//        LocalDateTime nextYear = localDateTime.plus(1, ChronoUnit.YEARS);
//        LocalDateTime nextWeek = localDateTime.plus(1, ChronoUnit.WEEKS);
//
//        System.out.println("now : " + localDateTime + "\n"
//                + "nextYear : " + nextYear + "\n"
//                + "nextMonth : " + nextMonth + "\n"
//                + "nextWeek :" + nextWeek + "\n"
//        );

//日期的减法用法一样，在此不再举例

//        LocalDate localDate = LocalDate.of(2018, 1, 13);
//        LocalTime localTime = LocalTime.of(9, 43, 20);
//        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 13, 9, 43, 20);
//        System.out.println(localDate);
//        System.out.println(localTime);
//        System.out.println(localDateTime);

//        LocalDate localDate = LocalDate.now();
//        LocalTime localTime = LocalTime.now();
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDate);
//        System.out.println(localTime);
//        System.out.println(localDateTime);
    }
}
