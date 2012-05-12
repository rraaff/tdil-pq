package com.tdil.text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class FilteredWord {

	private String word;
	
	private static String headers[] = {"word"};
	private static List<FilteredWord> instances = new ArrayList<FilteredWord>();
	
	private static FilterProfanity filterProfanity = new FilterProfanity();

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		readAll();
		System.out.println(filterProfanity.aprovesStrict("Vos sos un repu tó"));
		System.out.println(filterProfanity.aprovesStrict("Mi con ichapa para es lo mas"));
	}
	
	public static void readAll() throws IOException, IllegalAccessException, InvocationTargetException,
			InstantiationException {
		InputStream input = FilteredWord.class.getResourceAsStream("filteredword.csv");
		List<String> lines = (List<String>)IOUtils.readLines(input);
		Set<String> filterList = new HashSet<String>();
		filterList.addAll(lines);
		filterProfanity.setFilterList(filterList);
	}

	public static FilterProfanity getFilterProfanity() {
		return filterProfanity;
	}
}
