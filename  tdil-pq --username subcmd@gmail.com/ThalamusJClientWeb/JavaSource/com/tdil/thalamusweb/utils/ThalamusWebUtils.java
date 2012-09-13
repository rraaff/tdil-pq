package com.tdil.thalamusweb.utils;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.utils.ThalamusUtils;

public class ThalamusWebUtils {

	public static void loginToActivity(WebsiteUser user, int activity) {
		try {
			ThalamusClientFacade.loginToActivity(user.getUsername(), user.getPassword(), activity);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public static void updateActivities(WebsiteUser user) {
		try {
			JSONObject getProfile = (JSONObject)ThalamusClientFacade.getProfile(user.getUsername(), user.getPassword());
			user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusWebUtils.class);
	}
}
