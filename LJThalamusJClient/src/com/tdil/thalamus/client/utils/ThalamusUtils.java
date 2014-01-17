package com.tdil.thalamus.client.utils;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.StandardResponse;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.PersonResult;

public class ThalamusUtils {

	private static Set<String> getAppliedActivitiesFrom(JSONObject jsonObject) {
		Set<String> appliedActivities = new HashSet<String>();
		JSONObject data = jsonObject.getJSONObject("context");
		JSONArray activities= data.getJSONArray(StandardResponse.ACTIVITIES);
		for (int i = 0; i < activities.size(); i++) {
			JSONObject act = activities.getJSONObject(i);
			appliedActivities.add(act.getString("code"));
		}
		return appliedActivities;
	}

	public static Set<String> getAppliedActivitiesFrom(PersonResult getProfile) {
		return getAppliedActivitiesFrom(getProfile.getProfile());
	}

	public static Set<String> getAppliedActivitiesFrom(LoginResult loginResult) {
		return getAppliedActivitiesFrom((JSONObject)loginResult.getResult());
	}
}
