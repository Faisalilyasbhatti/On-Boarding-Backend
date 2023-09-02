package com.evantagesoft.util;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.jackrabbit.core.fs.local.FileUtil;
import org.springframework.util.StringUtils;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class SystemUtil {

	/**
	 * method to check if input string is null empty trim().lenght() = 0, or undefined as value
	 * @param str
	 * @return true if str is null or empty or undefined in it or trim().length = 0
	 * else return false
	 */
	public static boolean isEmpty(String str) {
		boolean isValid = false;
		if(str == null || str.equals("") || str.trim().length() == 0 || str.equals("undefined")) {
			isValid = true;
		}
		return isValid;
	}

	public static String getFileName(String filePath) {
		if (!StringUtils.isEmpty(filePath)) {
			return FilenameUtils.getBaseName(filePath);
		}
		return null;
	}
}
