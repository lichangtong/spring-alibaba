package com.demo.alibaba;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Classname DateUtils
 * @Description TODO
 * @Date 2020/3/13 18:49
 * @Created by mengdesheng
 */
@Slf4j
public class DateUtils {


    /**
     * utc时间转成local时间
     *
     * @param utcTime
     * @return
     */
    public static Date utcToLocal(String utcTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = sdf.format(utcDate.getTime());
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }

    /**
     * 转换日期时间
     *
     * @param
     * @return
     */
    public static String transFrom(String time, int formatType) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat sdf = null;
        switch (formatType) {
            case 1:
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                break;
            case 2:
                sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                break;
        }
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdfNew.format(date);
    }

    public static void main(String[] args) {
        System.out.println(transFrom("20200804 09:40:52", 2));
    }

    /**
     * 判断两个日期间是否超过的年数
     *
     * @param time1
     * @param time2
     * @param numYear
     * @return
     */
    public static Boolean dateCompare(Date time1, Date time2, int numYear) {
        Date time3 = add(time1, Calendar.YEAR, numYear);
        return time3.getTime() > time2.getTime();
    }

    /**
     * 时间加减
     *
     * @param date
     * @param calendarField ：Calendar.YEAR/ Calendar.MONTH /Calendar.DAY
     * @param amount
     * @return
     */
    public static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * String转Date
     */
    public static Date stringToDate(String time, int formatType) {
        Date date = null;
        try {
            switch (formatType) {
                case 1:
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    date = sdf.parse(time);
                    break;
                case 2:
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    date = sdf2.parse(time);
                    break;
                case 3:
                    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf3.parse(time);
                    break;
                case 4:
                    SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMdd");
                    date = sdf4.parse(time);
                    break;
                case 5:
                    SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    date = sdf5.parse(time);
                    break;
                case 6:
                    SimpleDateFormat sdf6 = new SimpleDateFormat("yyyyMMdd");
                    date = sdf6.parse(time);
                    break;
            }
        } catch (ParseException e) {
            log.error("日期格式字符串转换Date失败：", e);
        }
        return date;
    }

    /**
     * String转Date
     */
    public static Date DateToDate(Date time, int formatType) {
        String stringDate = null;
        Date date = null;
        try {
            switch (formatType) {
                case 1:
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    stringDate = sdf.format(time);
                    date = sdf.parse(stringDate);
                    break;
                case 2:
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    stringDate = sdf2.format(time);
                    date = sdf2.parse(stringDate);
                    break;
                case 3:
                    SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
                    stringDate = sdf3.format(time);
                    date = sdf3.parse(stringDate);
                    break;
            }
        } catch (ParseException e) {
            log.error("日期格式字符串转换Date失败：", e);
        }
        return date;
    }


    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            return day2 - day1 + 1;
        }
    }

    /**
     * 获取
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDifferentMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }


    public static String geOrderPrefixTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatTimeStr = "";
        formatTimeStr = sdf.format(new Date());
        return formatTimeStr;
    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTimeStr = "";
        formatTimeStr = sdf.format(new Date());
        return formatTimeStr;
    }
}
