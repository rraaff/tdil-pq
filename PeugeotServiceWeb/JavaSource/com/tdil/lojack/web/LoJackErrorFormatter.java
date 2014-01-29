package com.tdil.lojack.web;

import javax.servlet.ServletRequest;

import com.tdil.web.ErrorFormatter;

public class LoJackErrorFormatter {
	
	public static String getErrorFrom(ServletRequest request, String error) {
		return ErrorFormatter.getErrorFrom(request, error, "", "");
	}
	
}
