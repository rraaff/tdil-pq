package com.tdil.thalamus.client.cache;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.tdil.cache.TimeoutCacheManager;
import com.tdil.log4j.LoggerProvider;

public class ThalamusCache {

	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusCache.class);
	}
	
	public static final String CACHE_DEFAULT = "thalamus.cache";

	private static final String DEFAULT_CACHE_TIME = "180000"; // media hora
	
	public static final String CACHE_BRAND = "thalamus.cache.brand";
	public static final String CACHE_BRAND_FAMILIES = "thalamus.cache.brandfamilies";
	public static final String CACHE_CHANNELS = "thalamus.cache.channels";
	public static final String CACHE_COUNTRIES = "thalamus.cache.countries";
	public static final String CACHE_DOCUMENTTYPES = "thalamus.cache.documenttypes";
	public static final String CACHE_PERSONFIELDS = "thalamus.cache.personfields";
	
	public static final String CACHE_SIGN_IN_FACEBOOK = "thalamus.cache.signin.facebook";
	public static final String CACHE_SIGN_IN_TWITTER = "thalamus.cache.signin.twitter";

	public static final String ALL[] = new String[] {CACHE_BRAND, CACHE_BRAND_FAMILIES, CACHE_CHANNELS, CACHE_COUNTRIES, CACHE_DOCUMENTTYPES, 
		CACHE_PERSONFIELDS, CACHE_SIGN_IN_FACEBOOK, CACHE_SIGN_IN_TWITTER};
	
	public static final String ALL_RESULT = "ALL";
	
	public static void configureWith(Properties properties) {
		// TODO Auto-generated method stub
		String defaultString = properties.getProperty(CACHE_DEFAULT, DEFAULT_CACHE_TIME);
		long defaultTimeout = Long.parseLong(defaultString);
		TimeoutCacheManager.setDEFAULT_TIMEOUT(defaultTimeout);
		getLog().fatal("Default cache time is (millis) " + defaultString);
		for (String region : ALL) {
			String regionTimeout = properties.getProperty(region, defaultString);
			getLog().fatal("Cache region " + region + " time is (millis) " + regionTimeout);
			TimeoutCacheManager.defineRegion(region, Long.parseLong(regionTimeout));
		}
	}

}
