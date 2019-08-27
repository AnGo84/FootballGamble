package ua.com.footballgamble.utils;

import org.apache.commons.lang3.StringUtils;

public class SeasonUtils {
	public static String getYearFromDate(String date) {
		if (StringUtils.isBlank(date)) {
			return date;
		}
		String year = StringUtils.substringBefore(date.trim(), "-");
		return year;
	}

	public static int getIntYearFromDate(String date) {
		String year = getYearFromDate(date);
		try {
			return Integer.valueOf(year);
		} catch (Exception e) {

		}
		return -1;
	}

}
