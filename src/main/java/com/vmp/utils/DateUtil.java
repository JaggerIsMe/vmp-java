package com.vmp.utils;


import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.DateTimePatternEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    private static final Object lockObj = new Object();
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) {
        try {
            return getSdf(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date getAfterDate(Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static Date offsetDay(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return calendar.getTime();
    }

    public static Date offsetDay(Date date, int offset, boolean clearTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (clearTime) {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return calendar.getTime();
    }

    // 根据起止日期获取日期列表
    public static List<String> getDateList(String startDateStr, String endDateStr) {
        Date startDate = parse(startDateStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());
        Date endDate = parse(endDateStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());

        List<String> dateList = new ArrayList<>();
        Date currentDate = startDate;
        while (currentDate.compareTo(endDate) <= 0) {
            dateList.add(format(currentDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
            currentDate = offsetDay(currentDate, Constants.ONE);
        }

        return dateList;
    }

}
