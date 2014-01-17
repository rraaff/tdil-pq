package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SocialConnectionsBean implements Serializable {

	private static final long serialVersionUID = 4882129405762955450L;

	private SocialBean facebook;
	private SocialBean twitter;
	
	public SocialConnectionsBean(JSONArray json) {
		for (int i = 0; i < json.size(); i++) {
			JSONObject element = json.getJSONObject(i);
			if ("facebook".equals(element.getString("providerId"))) {
				SocialBean facebook = new SocialBean();
				facebook.setAccessToken(element.getString("accessToken"));
				facebook.setDisplayName(element.getString("displayName"));
				//facebook.setExpireTime(element.getLong("expireTime"));
				facebook.setImageUrl(element.getString("imageUrl"));
				facebook.setProfileUrl(element.getString("profileUrl"));
				facebook.setProviderUserId(element.getString("providerUserId"));
				setFacebook(facebook);
			}
			if ("twitter".equals(element.getString("providerId"))) {
				SocialBean twitter = new SocialBean();
				twitter.setAccessToken(element.getString("accessToken"));
				twitter.setDisplayName(element.getString("displayName"));
				//twitter.setExpireTime(element.getLong("expireTime"));
				twitter.setImageUrl(element.getString("imageUrl"));
				twitter.setProfileUrl(element.getString("profileUrl"));
				twitter.setProviderUserId(element.getString("providerUserId"));
				setTwitter(twitter);
			}
		}
	}
	
	public boolean hasFacebook() {
		return facebook != null;
	}

	public boolean hasTwitter() {
		return twitter != null;
	}
	
	public SocialBean getFacebook() {
		return facebook;
	}

	public void setFacebook(SocialBean facebook) {
		this.facebook = facebook;
	}

	public SocialBean getTwitter() {
		return twitter;
	}

	public void setTwitter(SocialBean twitter) {
		this.twitter = twitter;
	}

}
