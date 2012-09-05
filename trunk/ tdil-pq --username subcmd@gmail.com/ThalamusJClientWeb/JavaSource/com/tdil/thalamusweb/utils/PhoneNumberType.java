package com.tdil.thalamusweb.utils;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberType {

	public static List<String> types = new ArrayList<String>();
	
	static {
		types.add("home");
		types.add("work");
		types.add("cellphone");
		types.add("other");
	}
}
