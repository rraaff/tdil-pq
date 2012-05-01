package com.tdil.milka.web;

import com.tdil.milka.model.ClickCounter;

public class MeltButton {

	//<div id="mb-2" buttonType="photomilka" buttonId="2" quantity="150"></div>
	// TODO ojo que esto podria hacerlo por pk
	public static String meltButton(ClickCounter c) {
		StringBuffer result = new StringBuffer();
		result.append("<div id=\"mb-").append(c.getId());
		result.append("\"  buttonType=\"").append(c.getOwnertype()).append("\"  buttonId=\"");
		result.append(c.getOwnerid()).append("\" quantity=\"").append(c.getClicks().toString()).append("\"></div>");
		return result.toString();
	}
}
