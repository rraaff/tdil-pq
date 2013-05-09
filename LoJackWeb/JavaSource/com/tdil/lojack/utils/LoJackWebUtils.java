package com.tdil.lojack.utils;

import javax.servlet.http.HttpServletRequest;

public class LoJackWebUtils {

	public static boolean isUserLogged(HttpServletRequest request) {
		WebsiteUser websiteUser = null;
		boolean logged = false;
		if (request.getSession(false) != null) {
			websiteUser = (WebsiteUser)request.getSession(false).getAttribute("user");
			if (websiteUser != null && websiteUser.isLogged()) {
				logged = true;
			}
		}
		return logged;
	}

}
