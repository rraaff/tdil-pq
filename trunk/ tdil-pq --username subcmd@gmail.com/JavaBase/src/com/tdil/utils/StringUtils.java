package com.tdil.utils;

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
	
	public static String nvl(String first, String second) {
		if (org.apache.commons.lang.StringUtils.isEmpty(first)) {
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
