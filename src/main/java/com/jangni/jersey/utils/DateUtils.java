package com.jangni.jersey.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * Patterns
     */
    public static final String DAY_YYYYMMDD = "yyyyMMdd";
    public static final String DAY_HHMMSS = "HHmmss";
    public static final String DAY_YYYYMMDDHHMMSS = "yyyyMMdd HHmmss";
    public static final String DAY_FAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * Parse date by 'yyyy-MM-dd' pattern
     *
     * @param str
     * @return
     */
    public static Date parseByDayPattern(String str,String format) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
    		return sdf.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Format date by 'yyyy-MM-dd HH:mm:ss' pattern
     *
     * @param date
     * @return
     */
    public static String formatByDateTimePattern(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
    }
}