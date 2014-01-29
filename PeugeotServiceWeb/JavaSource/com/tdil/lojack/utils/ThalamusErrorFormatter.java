package com.tdil.lojack.utils;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;

public class ThalamusErrorFormatter {
	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "<div>", "</div>");
	}
	
}
