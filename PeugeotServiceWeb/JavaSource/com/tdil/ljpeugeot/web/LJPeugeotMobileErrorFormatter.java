package com.tdil.ljpeugeot.web;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;

public class LJPeugeotMobileErrorFormatter {
	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "", "");
	}
	
}
