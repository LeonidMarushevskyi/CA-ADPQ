package com.agreeya.chhs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Date Formatting utility class
 * @author AgreeYa Solutions
 *
 */
public class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class);

	/**
	 * @purpose get a date from string
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date getDateFromString(String date, String format) {
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date dt = null;
		try {
			dt = formatter.parse(date);
		} catch (ParseException e) {
			log.error(CHHSErrorCodes.CHANGE_DATE_FROM_STRING_FAILED_MESSAGE, e);
		}
		return dt;
	}

}
