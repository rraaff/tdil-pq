package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.List;

public class PhoneType {

	public static List<String> getPhoneTypes() {
		List<String> result = new ArrayList<String>();
		result.add("Particular");
		result.add("Celular");
		result.add("Laboral");
		return result;
	}
}
