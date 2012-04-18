package com.tdil.utils;

import java.net.URLEncoder;

import org.apache.commons.lang.StringEscapeUtils;

public class StringUtils {
	
	private static final String origin = " áéíóúÁÉÍÓÚñÑ";
	private static final String dest = "_aeiouAEIOUnN";

	public static String notNullValueOf(String st) {
		if (st == null) {
			return "";
		} else {
			return st;
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
			return URLEncoder.encode(result);
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
