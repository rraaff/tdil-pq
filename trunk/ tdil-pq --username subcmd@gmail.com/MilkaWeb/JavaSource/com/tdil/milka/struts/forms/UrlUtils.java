package com.tdil.milka.struts.forms;

import java.util.ArrayList;
import java.util.List;

import com.tdil.milka.model.Target;

public class UrlUtils {

	private static List<String> allTargets;
	
	static {
		allTargets = new ArrayList<String>();
		allTargets.add(Target.SELF);
		allTargets.add(Target.BLANK);
	}

	public static List<String> getAllTargets() {
		return allTargets;
	}
}
