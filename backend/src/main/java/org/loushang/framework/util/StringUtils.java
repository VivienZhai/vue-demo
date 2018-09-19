package org.loushang.framework.util;

public class StringUtils extends org.springframework.util.StringUtils {
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
