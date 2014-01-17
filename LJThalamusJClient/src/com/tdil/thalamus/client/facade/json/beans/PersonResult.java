package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;

import net.sf.json.JSONObject;

import com.tdil.thalamus.client.facade.ProfileResponse;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;

public class PersonResult implements Serializable {

	private static final long serialVersionUID = -3064992187355111597L;
	private JSONObject profile;

	public PersonResult(JSONObject profile) {
		super();
		this.profile = profile;
	}

	public JSONObject getProfile() {
		return profile;
	}

	public String getFirstName() {
		JSONObject data = getProfile().getJSONObject("person");
		return (data).getJSONObject(ProfileResponse.PROFILE).getString(PersonFieldNames.firstName);
	}

	public String getLastName() {
		JSONObject data = getProfile().getJSONObject("person");
		return (data).getJSONObject(ProfileResponse.PROFILE).getString(PersonFieldNames.lastName);
	}
}
