package com.tdil.ljpeugeot.web;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;

public class LJPeugeotErrorFormatter {
	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "", "");
	}
	
}
