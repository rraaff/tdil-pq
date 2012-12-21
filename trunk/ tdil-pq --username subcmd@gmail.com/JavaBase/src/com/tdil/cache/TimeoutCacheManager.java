package com.tdil.cache;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.jcs.JCS;
import org.apache.jcs.access.CacheAccess;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.CompositeCacheAttributes;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;

public class TimeoutCacheManager {

	/** This are cache regions by name */
	private static ConcurrentHashMap<String, TimeoutCacheAccess> caches = new ConcurrentHashMap<String, TimeoutCacheAccess>();
	
	private static final int DEFAULT_MAX_OBJECTS = 1000;
	private static long DEFAULT_TIMEOUT = 1000 * 60 * 30; // 1 seg * 60 * 30 = 30 minutos
	private static boolean started = false;
	
	private static final Logger logger = LoggerProvider.getLogger(TimeoutCacheManager.class);
	
	private static void startup() {
		if (started) {
			return;
		}
		CompositeCacheManager ccm = CompositeCacheManager.getUnconfiguredInstance();
		Properties props = getDefaultProperties();
		ccm.configure(props);
		started = true;
	}
	
	private static Properties getDefaultProperties() {
		Properties props = new Properties();
		props.put("jcs.default","");
		props.put("jcs.default.cacheattributes","org.apache.jcs.engine.CompositeCacheAttributes");
		props.put("jcs.default.cacheattributes.MaxObjects","1000");
		props.put("jcs.default.cacheattributes.MemoryCacheName","org.apache.jcs.engine.memory.lru.LRUMemoryCache");
		props.put("jcs.default.elementattributes","org.apache.jcs.engine.ElementAttributes");
		props.put("jcs.default.elementattributes.IsEternal","true");
		props.put("jcs.default.elementattributes.IsSpool","false");
		props.put("jcs.default.elementattributes.IsRemote","false");
		props.put("jcs.default.elementattributes.IsLateral","false");
		return props;
	}
	/**
	 * This method creates the region with default values.
	 * @param regionName
	 * @throws CacheException 
	 * @throws IOException 
	 */
	public synchronized static void defineRegion(String regionName, long timeout) {
		if (isRegionDefined(regionName)) {
			logger.warn("Region " + regionName + " already defined");
		} else {
			CompositeCacheAttributes compositeCacheAttributes = new CompositeCacheAttributes();
			compositeCacheAttributes.setUseLateral(false);
			compositeCacheAttributes.setUseRemote(false);
			compositeCacheAttributes.setUseDisk(false);
			compositeCacheAttributes.setMaxObjects(DEFAULT_MAX_OBJECTS);
			try {
				final CacheAccess ca = JCS.defineRegion(regionName, compositeCacheAttributes);
				caches.putIfAbsent(regionName, new TimeoutCacheAccess(ca, timeout));
			} catch (CacheException e) {
				logger.error(e.getMessage(), e);
				logger.error("Could not define region");
			}
		}
	}
	
	/**
	 * This method creates the region with default values and max objects.
	 * @param regionName
	 * @throws CacheException 
	 * @throws IOException 
	 */
	public synchronized static void defineRegion(String regionName, int maxObjects, long timeout) {
		if (isRegionDefined(regionName)) {
			logger.warn("Region " + regionName + " already defined");
		} else {
			CompositeCacheAttributes compositeCacheAttributes = new CompositeCacheAttributes();
			compositeCacheAttributes.setUseLateral(false);
			compositeCacheAttributes.setUseRemote(false);
			compositeCacheAttributes.setUseDisk(false);
			compositeCacheAttributes.setMaxObjects(maxObjects);
			try {
				final CacheAccess ca = JCS.defineRegion(regionName, compositeCacheAttributes);
				caches.putIfAbsent(regionName, new TimeoutCacheAccess(ca, timeout));
			} catch (CacheException e) {
				logger.error(e.getMessage(), e);
				logger.error("Could not define region");
			}
		}
	}
	
	/**
	 * This method answers true if the region has already been defined
	 * @param regionName
	 * @return
	 */
	public static boolean isRegionDefined(String regionName) {
		if (!started) {
			startup();
		}
		return caches.containsKey(regionName);
	}
	
	/**
	 * This method stores the value under key in the region named regionName.
	 * If cache is clustered, this method notifies the other member.
	 * @param regionName
	 * @param key
	 * @param value
	 */
	public static void put(String regionName, String key, Serializable value) {
		if (!isRegionDefined(regionName)) {
			defineRegion(regionName, DEFAULT_TIMEOUT);
		}
		basicPut(regionName, key, value);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void basicPut(String regionName, String key,
			Serializable value) {
		CacheAccess ca = getCacheAccess(regionName);
		try {
			ca.put(key, new CacheEntry(value));
		} catch (CacheException e) {
			logger.error(e.getMessage(), e);
			logger.error("Could not add object to cache");
		}
	}
	
	/**
	 * This method answers the object cached under some key.
	 * If the entry is null or the entry is not present, this method answers null.
	 * If nulls must be cached, it is strongly recommended to use the method getCacheEntry
	 * or the get(String regionName, String key, Object nullValue).
	 * @param regionName
	 * @param key
	 * @return
	 */
	public static Object get(String regionName, String key) {
		if (!isRegionDefined(regionName)) {
			defineRegion(regionName, DEFAULT_TIMEOUT);
		}
		return basicGet(regionName, key);
	}

	@SuppressWarnings("rawtypes")
	private static Object basicGet(String regionName, String key) {
		CacheAccess ca = getCacheAccess(regionName);
		CacheEntry cacheEntry = (CacheEntry)ca.get(key);
		if (cacheEntry == null) {
			return null;
		} else {
			return cacheEntry.getValue();
		}
	}
	
	/**
	 * This method answers the object cached under key.
	 * If the object is not present, the nullValue parameter is returned.
	 * @param regionName
	 * @param key
	 * @param nullValue
	 * @return
	 */
	public static Object get(String regionName, String key, Object nullValue) {
		if (!isRegionDefined(regionName)) {
			defineRegion(regionName, DEFAULT_TIMEOUT);
		}
		return basicGet(regionName, key, nullValue);
	}

	@SuppressWarnings("rawtypes")
	private static Object basicGet(String regionName, String key,
			Object nullValue) {
		CacheAccess ca = getCacheAccess(regionName);
		CacheEntry cacheEntry = (CacheEntry)ca.get(key);
		if (cacheEntry == null) {
			return nullValue;
		} else {
			return cacheEntry.getValue();
		}
	}
	
	/**
	 * This method answers the CacheEntry under some key.
	 * If the entry is not found, this method answers null.
	 * This method must be used when caching null values.
	 */
	@SuppressWarnings("rawtypes")
	public static CacheEntry getCacheEntry(String regionName, String key) {
		if (!isRegionDefined(regionName)) {
			defineRegion(regionName, DEFAULT_TIMEOUT);
		}
		CacheAccess ca = getCacheAccess(regionName);
		return (CacheEntry)ca.get(key);
	}
	
	/**
	 * This method removes all entries for the entire cache
	 */
	public static void clearAll() {
		for (Map.Entry<String, TimeoutCacheAccess> entry : caches.entrySet()) {
			try {
				entry.getValue().getCacheAccess().clear();
			} catch (CacheException e) {
				logger.error(e.getMessage(), e);
				logger.error("Could not clear region " + entry.getKey());
			}
		}
	}
	
	private static CacheAccess getCacheAccess(String regionName) {
		TimeoutCacheAccess versionedCacheAccess = caches.get(regionName);
		if (versionedCacheAccess.isOutOfDate()) {
			try {
				versionedCacheAccess.getCacheAccess().clear();
				versionedCacheAccess.updateTimestamp();
			} catch (CacheException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return caches.get(regionName).getCacheAccess();
	}

	public static long getDEFAULT_TIMEOUT() {
		return DEFAULT_TIMEOUT;
	}

	public static void setDEFAULT_TIMEOUT(long dEFAULT_TIMEOUT) {
		DEFAULT_TIMEOUT = dEFAULT_TIMEOUT;
	}
}
