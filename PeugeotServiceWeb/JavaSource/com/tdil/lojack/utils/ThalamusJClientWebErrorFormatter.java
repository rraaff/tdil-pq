package com.tdil.lojack.utils;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;


public class ThalamusJClientWebErrorFormatter {

	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "<img id='"+error.replace('.', '_')+"error' src='img/unchecked.gif' hovertext='", "' />");
	}
}
