package com.tdil.ljpeugeot.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.thalamus.client.core.ThalamusResponse;

public class ThalamusWebUtils {

	public static void loginToActivity(WebsiteUser user, String activity) {
		/*
		 * try {
			ThalamusClientFacade.loginToActivity(user.getToken(), activity);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}*/
	}

	public static void updateActivities(WebsiteUser user) {
		/*
		 * try {
			PersonResult getProfile = ThalamusClientBeanFacade.getPerson(user.getToken());
			user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(getProfile));
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}*/
	}

	private static void addErrorsTo(ValidationError errors, JSON json) {
		JSONObject jsonObject = (JSONObject)json;
		JSONObject errorsJson = jsonObject.getJSONObject("errors");
		for (Object o : errorsJson.keySet()) {
			String keyToStore = o.toString();
			if (keyToStore.equals("credential.principal")) {
				errors.setFieldError(keyToStore, errorsJson.getString(o.toString()));
				keyToStore = "RegisterForm.document";
			}
			if (keyToStore.equals("credential.password")) {
				errors.setFieldError(keyToStore, errorsJson.getString(o.toString()));
				keyToStore = "RegisterForm.password";
			}
			errors.setFieldError(keyToStore, errorsJson.getString(o.toString()));
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusWebUtils.class);
	}

	public static void addErrorsTo(ValidationError validationError,
			ThalamusResponse response) {
		addErrorsTo(validationError, response.getResult());
	}

	public static boolean useCameraApplet(HttpServletRequest request) {
		Cookie cameraCookie = getCameraCookie(request);
		if (cameraCookie == null) {
			return true;
		}
		return "applet".equals(cameraCookie.getValue());
	}

	public static Cookie getCameraCookie(HttpServletRequest request) {
		for (Cookie co : request.getCookies()) {
			if (co.getName().equals("cameview")) {
				return co;
			}
		}
		return null;
	}

}
