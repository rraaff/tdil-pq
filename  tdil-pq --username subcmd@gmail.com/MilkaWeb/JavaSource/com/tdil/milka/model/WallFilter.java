package com.tdil.milka.model;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.text.FilterProfanity;

public class WallFilter {

	private static FilterProfanity filterProfanity;
	
	public static void init() {
		filterProfanity = new FilterProfanity();
		try {
			filterProfanity.readAll();
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public static void add(String word) {
		filterProfanity.addFilterList(word);
	}
	
	public static boolean approves(String text) {
		return filterProfanity.approvesStrict(text);
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(WallFilter.class);
	}
}
