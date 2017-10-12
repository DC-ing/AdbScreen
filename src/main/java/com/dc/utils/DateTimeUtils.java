package com.dc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间的操作
 * 
 * @version 1.0
 * 
 */

public class DateTimeUtils {
	
	private static Logger logger = LogManager.getLogger();

	/**
	 * 指定文件名、截图的日期格式
	 * 
	 * @return 返回时间格式，如：2016-07-22-15-15-30
	 * 
	 */
	public static String getFileDateTime() {
		return formatedTime("yyyyMMdd-HHmmss");
	}

	/**
	 * 根据自定义格式获取系统当前时间
	 *
	 * @param format
	 *            时间格式 eg: yyyy-MM-dd HH:mm:ss:SSS
	 * @return 根据自定义格式返回系统当前时间
	 *
	 */
	public static String formatedTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

}