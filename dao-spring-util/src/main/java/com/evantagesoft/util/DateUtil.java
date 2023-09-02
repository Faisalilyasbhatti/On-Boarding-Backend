package com.evantagesoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class DateUtil {

	public static Date strToDate(String dateStr, String pattern) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date addDate(Date date, String type, int val) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			if(type.equals("DAY")) {
				c.add(Calendar.DATE, val);
			} else if(type.equals("MONTH")) {
				c.add(Calendar.MONTH, val);
			} else if(type.equals("YEAR")) {
				c.add(Calendar.YEAR, val);
			}
			date = c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {
        {
            put("^\\d{8}$", "yyyyMMdd");
            put("^\\d{12}$", "yyyyMMddHHmm");
            put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
            put("^\\d{14}$", "yyyyMMddHHmmss");
            put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
            put("^\\d{1,2}.\\d{1,2}.\\d{4}$", "dd.MM.yyyy");
            put("^\\d{4}.\\d{1,2}.\\d{1,2}$", "yyyy.MM.dd");
            put("^\\d{1,2}-\\d{1,2}-\\d{2}$", "dd-MM-yy");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "dd/MM/yyyy");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}\\.\\d{2}[-+]\\d{2}:\\d{2}$", "yyyy-MM-dd'T'HH:mm:ss.SSS");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}\\.\\d{1,3}$", "yyyy-MM-dd HH:mm:ss.SSS");
        }
    };
    
    public static String getDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            try {
            	if (dateString.matches(regexp) || dateString.toLowerCase().matches(regexp)) {
                    return DATE_FORMAT_REGEXPS.get(regexp);
                }
			} catch (Exception e) {
			}
        }
        return null;
    }
}
