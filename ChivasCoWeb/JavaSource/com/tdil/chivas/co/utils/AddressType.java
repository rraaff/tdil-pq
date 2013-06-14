package com.tdil.chivas.co.utils;

import java.util.ArrayList;
import java.util.List;

public class AddressType {

	public static List<String> types = new ArrayList<String>();
	
	static {
		types.add("home");
		types.add("work");
		types.add("other");
	}
}
