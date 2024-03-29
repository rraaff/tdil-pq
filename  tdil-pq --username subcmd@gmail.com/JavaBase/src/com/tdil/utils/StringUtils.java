package com.tdil.utils;

import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;

public class StringUtils {
	
	private static final String origin = " ������������";
	private static final String dest = "_aeiouAEIOUnN";

	public static String notNullValueOf(String st) {
		if (st == null) {
			return "";
		} else {
			return st;
		}
	}
	
	public static String getStringOfLen(String st, int len, String complete) {
		if (org.apache.commons.lang.StringUtils.isEmpty(st)) {
			return "";
		}
		if (st.length() < len) {
			return st;
		}
		return st.substring(0, len - complete.length()) + complete;
	}
	
	public static String asURL(String st) {
		if (!st.startsWith("http")) {
			return "http://" + st;
		} else {
			return st;
		}
	}
	
	public static String nullValueOf(String string) {
		if (org.apache.commons.lang.StringUtils.isEmpty(string)) {
			return null;
		}
		return string;
	}
	
	public static String nvl(String first, String second) {
		if (org.apache.commons.lang.StringUtils.isEmpty(first)) {
			return second;
		} else {
			return first;
		}
	}
	
	public static String nvl(String first, String prefix, String second) {
		if (org.apache.commons.lang.StringUtils.isEmpty(first)) {
			return second;
		} else {
			return prefix + first;
		}
	}
	
	public static Date nvl(Date first, Date second) {
		if (first == null) {
			return second;
		} else {
			return first;
		}
	}
	
	public static Integer nvl(Integer first, Integer second) {
		if (first == null) {
			return second;
		} else {
			if (first == 0) {
				return second;
			} else {
				return first;
			}
		}
	}
	
	public static int nvl(int first, int second) {
		if (first == 0) {
			return second;
		} else {
			return first;
		}
	}
	
	public static String getDataForChange(String first, String second) {
		if (org.apache.commons.lang.StringUtils.isEmpty(first)) {
			return null;
		} else {
			if (first.equals(second)) {
				return null;
			} else {
				return first;
			}
		}
	}
	
	public static String getDataForChange(String first, String second, String nullValue) {
		if (org.apache.commons.lang.StringUtils.isEmpty(first)) {
			if (!org.apache.commons.lang.StringUtils.isEmpty(second)) {
				return nullValue;
			} else {
				return null;
			}
		} else {
			if (first.equals(second)) {
				return null;
			} else {
				return first;
			}
		}
	}
	
	public static int getDataForChange(int id1, int id2) {
		if (id1 == 0) {
			return 0;
		} else {
			if (id1 == id2) {
				return 0;
			} else {
				return id1;
			}
		}
	}
	
	public static String urlValid(String st) {
		if (st == null) {
			return "";
		} else {
			String result = st;
			for (int i = 0; i < origin.length(); i++) {
				result = result.replace(origin.charAt(i), dest.charAt(i));
			}
			return result.replaceAll("[^a-zA-Z0-9_aeiouAEIOUnN]", "");
		}
	}
	
	public static boolean equalsUnescaped(String first, String second) {
		if (first == null && second != null) {
			return false;
		}
		if (first != null && second == null) {
			return false;
		}
		if (first == null && second == null) {
			return true;
		}
		String firstToCompare = StringEscapeUtils.unescapeHtml(first);
		String secondToCompare = StringEscapeUtils.unescapeHtml(second);
		return firstToCompare.equals(secondToCompare);
	}
}
