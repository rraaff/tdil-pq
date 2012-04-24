package com.tdil.djmag.web;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;


public class DJMagErrorFormatter {

	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "<img id='"+error.replace('.', '_')+"error' src='img/unchecked.gif' hovertext='", "' />");
	}
}
