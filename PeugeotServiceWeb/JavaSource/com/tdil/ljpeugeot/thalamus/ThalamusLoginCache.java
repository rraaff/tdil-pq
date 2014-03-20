package com.tdil.ljpeugeot.thalamus;

import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tdil.ljpeugeot.lojack.LoJackConnector;
import com.tdil.ljpeugeot.utils.WebsiteUser;

public class ThalamusLoginCache {

	private static Cache<String, JSONObject> petsLoginCacheCache;
	private static Cache<String, JSONObject> preventLoginCacheCache;
	private static Cache<String, JSONObject> homeLoginCacheCache;
	
	private static final String homeUser = "homeUser";
	private static final String petUser = "petUser";
	private static final String preventUser = "preventUser";
	
	static {
		petsLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
		preventLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
		homeLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
	}
	
	public static void updateCache(WebsiteUser user) {
		String JSESSIONID = user.getJSESSIONID();
		String AWSELB = user.getAWSELB();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("JSESSIONID", JSESSIONID);
		jsonObject.put("AWSELB", AWSELB);
		
		if (user.isHomeUser()) {
			putHomeUser(user);
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put(homeUser, user.getHomeUserId());
			jsonObject.put(homeUser, jsonObject1);
		}
		if (user.isPetUser()) {
			putPetUser(user);
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put(petUser, user.getPetUserId());
			jsonObject.put(petUser, jsonObject1);
		}
		if (user.isPreventUser()) {
			putPreventUser(user);
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put(preventUser, user.getPreventUserId());
			jsonObject.put(preventUser, jsonObject1);
		}
		// Llamo al LoJackFront y paso el json
		LoJackConnector.addToRemoteCache(jsonObject);
	}

	public static void putPetUser(WebsiteUser user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(petUser, user.getPetUserId());
		petsLoginCacheCache.put(user.getJSESSIONID() + "-" + user.getAWSELB(), jsonObject);
	}
	
	public static JSONObject getPetJSON(String key) {
		return petsLoginCacheCache.getIfPresent(key);
	}
	
	public static void putPreventUser(WebsiteUser user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(preventUser, user.getPreventUserId());
		preventLoginCacheCache.put(user.getJSESSIONID() + "-" + user.getAWSELB(), jsonObject);
	}
	
	public static JSONObject getPreventJSON(String key) {
		return preventLoginCacheCache.getIfPresent(key);
	}
	
	public static void putHomeUser(WebsiteUser user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(homeUser, user.getHomeUserId());
		homeLoginCacheCache.put(user.getJSESSIONID() + "-" + user.getAWSELB(), jsonObject);
	}
	
	public static JSONObject getHomeJSON(String key) {
		return homeLoginCacheCache.getIfPresent(key);
	}

}
