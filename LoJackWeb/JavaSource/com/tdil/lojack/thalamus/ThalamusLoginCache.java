package com.tdil.lojack.thalamus;

import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.utils.LoJackConfig;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.utils.CryptoUtils;

public class ThalamusLoginCache {

	private static Cache<String, JSONObject> petsLoginCacheCache;
	private static Cache<String, JSONObject> preventLoginCacheCache;
	private static Cache<String, JSONObject> homeLoginCacheCache;
	
	private static final String homeUser = "homeUser";
	private static final String petUser = "petUser";
	private static final String preventUser = "preventUser";

	public static final Logger LOG = LoggerProvider.getLogger(ThalamusLoginCache.class);
	
	static {
		petsLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
		preventLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
		homeLoginCacheCache = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
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
	
	public static void updateCache(JSONObject jsonObject, String signHeader) {
		String JSESSIONID = jsonObject.getString("JSESSIONID");
		String AWSELB = jsonObject.getString("AWSELB");
		String toSign = JSESSIONID + AWSELB + LoJackConfig.getPeugeotSign();
		String regeneratedSignHeader = CryptoUtils.getHashedValue(toSign);
		if (!regeneratedSignHeader.equals(signHeader)) {
			LOG.error("La firma del header no se corresponde con la local, no se agrega el usuario a los logins");
			LOG.error(jsonObject);
		} else {
			LOG.info("Agregando login remoto de peugeot" + jsonObject);
		}
		if (jsonObject.containsKey(homeUser)) {
			JSONObject homeUserObj = jsonObject.getJSONObject(homeUser);
			homeLoginCacheCache.put(JSESSIONID + "-" + AWSELB, homeUserObj);
		}
		if (jsonObject.containsKey(petUser)) {
			JSONObject petUserObj = jsonObject.getJSONObject(petUser);
			petsLoginCacheCache.put(JSESSIONID + "-" + AWSELB, petUserObj);
		}
		if (jsonObject.containsKey(preventUser)) {
			JSONObject preventUserObj = jsonObject.getJSONObject(preventUser);
			preventLoginCacheCache.put(JSESSIONID + "-" + AWSELB, preventUserObj);
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
