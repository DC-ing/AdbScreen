package com.dc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * 文件封装类，输出日志类
 * 
 * @version 1.1
 * 
 */

public class FileUtils {
	
	private static Logger logger = LogManager.getLogger();

	/**
	 * 如果文件不存在，先创建
	 * 
	 * @param fileFullName 文件路径
	 * 
	 */
	public static void createFile(String fileFullName) {
		File newFile = new File(fileFullName);
		try {
			if (!newFile.exists()) {
				File parent = newFile.getParentFile();
				if (!parent.exists()) {		//判断路径是否存在，不存在，则创建路径
					parent.mkdirs();
				}
				newFile.createNewFile();	//文件不存在，创建空白文件
				logger.debug("创建文件：" + fileFullName);
			}
			else {
				logger.debug("文件已存在");
			}
		} catch (IOException e) {
			logger.catching(e);
		}
	}

}
