package com.tdil.lojack.utils;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CameraCache {

	private static Cache<String, byte[]> cameraImageCache;
	
	
	
	public static void init(int millis, int maxSize) {
		cameraImageCache = CacheBuilder.newBuilder().expireAfterWrite(millis, TimeUnit.MILLISECONDS).maximumSize(maxSize).build();
	}
	
	public static byte[] getImage(String url) {
		byte[] result = cameraImageCache.getIfPresent(url);
		if (result != null) {
			System.out.println("image found in cache");
		} else {
			System.out.println("image not found in cache");
		}
		return result;
	}
	
	public static void putImage(String url, byte[] image) {
		cameraImageCache.put(url, image);
	}
	
}
