package com.fenbi.fbms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	/**
	 * 把时间字符串 按照 SDF的格式 转成Date对象
	 * @param timeString
	 * @return
	 */
	public static Date stringToDate(String timeString) {
		try {
			return SDF.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把Date对象 按照 SDF的格式 转成 时间字符串
	 * @param timeString
	 * @return
	 */
	public static String dateToString(Date date) {
		if(date==null) {
			return null;
		}
		return SDF.format(date);
	}
	
}
