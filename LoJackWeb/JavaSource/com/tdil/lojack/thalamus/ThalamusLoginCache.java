package com.tdil.lojack.thalamus;

import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tdil.lojack.utils.WebsiteUser;

public class ThalamusLoginCache {

	private static Cache<String, JSONObject> petsLoginCacheCache;
	private static Cache<String, JSONObject> preventLoginCacheCache;
	private static Cache<String, JSONObject> homeLoginCacheCache;
	
	private static final String homeUser = "homeUser";
	private static final String petUser = "petUser";
	private static final String preventUser = "preventUser";
	
	static {
		petsLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).build();
		preventLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).build();
		homeLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).build();
	}
	
	public static void updateCache(WebsiteUser user) {
		if (user.isHomeUser()) {
			putHomeUser(user);
		}
		if (user.isPetUser()) {
			putPetUser(user);
		}
		if (user.isPreventUser()) {
			putPreventUser(user);
		}
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
