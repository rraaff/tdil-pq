package com.tdil.milka.web;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;

public class MilkaErrorFormatter {
	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "<img id='"+error.replace('.', '_')+"error' src='img/unchecked.gif' hovertext='", "' />");
	}
	
}
