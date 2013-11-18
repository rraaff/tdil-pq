package com.tdil.lojack.rest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tdil.lojack.utils.WebsiteUser;

public class ApkLoginCache {

	private static Cache<String, WebsiteUser> apkLoginCache;
	
	
	static {
		apkLoginCache = CacheBuilder.newBuilder().expireAfterAccess(24, TimeUnit.HOURS).build();
	}
	
	public static String add(WebsiteUser user) {
		String hash = createCacheKey();
		apkLoginCache.put(hash, user);
		return hash;
	}
	
	private static String createCacheKey() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static WebsiteUser get(String cacheKey) {
		return apkLoginCache.getIfPresent(cacheKey);
	}
	
}
