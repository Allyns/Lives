package com.allyn.lives.view.widgets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * Created by liukun on 15/3/24.
 */
public class CommonDateUtils {
    private static SimpleDateFormat simpleDateFormatDefault;

    /**
     * 获取yyyy-MM-dd HH:mm:ss  格式的simpleDateFormat
     *
     * @return
     */
    public synchronized static SimpleDateFormat getSimpleDateFormatDefault() {
        if (simpleDateFormatDefault == null) {
            simpleDateFormatDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return simpleDateFormatDefault;
    }


    public synchronized static String getDate(String baseDateStr, Date nowDate) {
        String result = "刚刚";
        if (baseDateStr == null || baseDateStr.trim().length() == 0) {
            return result;
        }

        try {
            Date baseDate = getSimpleDateFormatDefault().parse(baseDateStr);

            long baseDateLong = baseDate.getTime();
            long nowDateLong = nowDate.getTime();
            long temp = nowDateLong - baseDateLong;

            if (temp >= aDayLong) { // 大于了一天 返回yyyy-MM-dd
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                result = simpleDateFormat.format(baseDate);
                simpleDateFormat = null;

            } else if (sixHourLong <= temp && temp <= aDayLong) { // 大于六小时, 并小于二十四小时 返回hh : mm : ss
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                result = simpleDateFormat.format(baseDate);
                simpleDateFormat = null;

            } else if (aHourLong <= temp && temp >= sixHourLong) { // 大于一小时, 并小于六小时 返回 X小时
                for (int i = 1; i < 7; i++) {
                    if (aHourLong * i > temp) {
                        result = i + "小时前";
                        break;
                    }
                }
            } else if (halfHourLong <= temp && temp <= aHourLong) { // 大于三十分钟, 并小于六十分钟 返回 X分钟
                for (int i = 3; i < 7; i++) {
                    long xMinuteLongTemp = tenMinuteLong * i;
                    if (xMinuteLongTemp > temp) {
                        result = i * 10 + "分钟前";
                        break;
                    }
                }
            } else if (tenMinuteLong <= temp && temp <= halfHourLong) { // 大于十分钟, 并小于三十分钟 返回 X分钟
                for (int i = 2; i < 7; i++) {
                    long xFiveMinuteLongTemp = fiveMinuteLong * i;
                    if (xFiveMinuteLongTemp > temp) {
                        result = xFiveMinuteLongTemp / aMinuteLong + "分钟前";
                        break;
                    }
                }
            } else if (aMinuteLong <= temp && temp <= tenMinuteLong) { // 大于一分钟, 并小于十分钟 返回 X分钟
                for (int i = 1; i < 11; i++) {
                    long xMinuteLongTemp = aMinuteLong * i;
                    if (xMinuteLongTemp > temp) {
                        result = xMinuteLongTemp / 1000 / 60 + "分钟前";
                        break;
                    }
                }
            } else if (temp <= aMinuteLong) {
                return result;
            } else {
                return result;
            }
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
    }


    // 一分钟
    private static long aMinuteLong = 1 * 60 * 1000;

    // 五分钟
    private static long fiveMinuteLong = 5 * 60 * 1000;

    // 十分钟
    private static long tenMinuteLong = 10 * 60 * 1000;

    // 半小时
    private static long halfHourLong = 30 * 60 * 1000;

    // 一小时
    private static long aHourLong = 1 * 60 * 60 * 1000;

    // 六小时
    private static long sixHourLong = 6 * 60 * 60 * 1000;

    // 二十四小时
    private static long aDayLong = 24 * 60 * 60 * 1000;

}
