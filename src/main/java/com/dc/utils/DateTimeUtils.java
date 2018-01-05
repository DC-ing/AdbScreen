package com.dc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间的操作
 * 
 * @version 1.0
 * 
 */

public class DateTimeUtils {
	
	/**
	 * 指定文件名、截图的日期格式
	 * 
	 * @return 返回时间格式，如：20160722-151530
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