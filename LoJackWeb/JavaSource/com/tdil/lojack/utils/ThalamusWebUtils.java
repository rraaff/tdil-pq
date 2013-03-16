package com.tdil.lojack.utils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.PersonResult;
import com.tdil.thalamus.client.utils.ThalamusUtils;

public class ThalamusWebUtils {

	public static void loginToActivity(WebsiteUser user, String activity) {
		try {
			ThalamusClientFacade.loginToActivity(user.getToken(), activity);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public static void updateActivities(WebsiteUser user) {
		try {
			PersonResult getProfile = ThalamusClientBeanFacade.getPerson(user.getToken());
			user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static void addErrorsTo(ValidationError errors, JSON json) {
		JSONObject jsonObject = (JSONObject)json;
		JSONObject errorsJson = jsonObject.getJSONObject("errors");
		for (Object o : errorsJson.keySet()) {
			errors.setFieldError(o.toString(), errorsJson.getString(o.toString()));
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusWebUtils.class);
	}

	public static void addErrorsTo(ValidationError validationError,
			ThalamusResponse response) {
		addErrorsTo(validationError, response.getResult());
	}
}
