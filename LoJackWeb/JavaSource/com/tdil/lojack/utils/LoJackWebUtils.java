package com.tdil.lojack.utils;

import javax.servlet.http.HttpServletRequest;

import com.tdil.lojack.roles.HomeUser;
import com.tdil.lojack.roles.PreventUser;

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
	
	public static boolean isHomeUserLogged(HttpServletRequest request) {
		WebsiteUser websiteUser = null;
		boolean logged = false;
		if (request.getSession(false) != null) {
			websiteUser = (WebsiteUser)request.getSession(false).getAttribute("user");
			if (websiteUser != null && websiteUser.isLogged()) {
				if (HomeUser.INSTANCE.isValid(websiteUser)) {
					logged = true;
				}
			}
		}
		return logged;
	}
	
	public static boolean isPreventUserLogged(HttpServletRequest request) {
		WebsiteUser websiteUser = null;
		boolean logged = false;
		if (request.getSession(false) != null) {
			websiteUser = (WebsiteUser)request.getSession(false).getAttribute("user");
			if (websiteUser != null && websiteUser.isLogged()) {
				if (PreventUser.INSTANCE.isValid(websiteUser)) {
					logged = true;
				}
			}
		}
		return logged;
	}
	
	public static boolean isMobile(HttpServletRequest request) {
		com.tdil.mobile.UAgentInfo agentInfo = new com.tdil.mobile.UAgentInfo(request.getHeader("User-Agent"), request.getHeader("Accept"));
		boolean mobile = agentInfo.detectMobileLong();
		System.out.println("mobile = " + mobile);
		return mobile;
	}

}
