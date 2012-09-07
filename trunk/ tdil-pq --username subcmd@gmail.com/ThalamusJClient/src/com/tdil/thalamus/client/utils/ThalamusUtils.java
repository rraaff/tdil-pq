package com.tdil.thalamus.client.utils;

import java.util.HashSet;
import java.util.Set;

import com.tdil.thalamus.client.facade.StandardResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ThalamusUtils {

	public static Set<Integer> getAppliedActivitiesFrom(JSONObject jsonObject) {
		Set<Integer> appliedActivities = new HashSet<Integer>();
		JSONObject data = jsonObject.getJSONObject(StandardResponse.DATA);
		JSONArray activities= data.getJSONArray(StandardResponse.ACTIVITIES);
		for (int i = 0; i < activities.size(); i++) {
			JSONObject act = activities.getJSONObject(i);
			appliedActivities.add(act.getInt(StandardResponse.ID));
		}
		return appliedActivities;
	}
}
