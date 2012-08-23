package com.tdil.cache;

import org.apache.jcs.access.CacheAccess;

public class VersionedCacheAccess {

	private Integer version;
	private CacheAccess cacheAccess;
	
	public VersionedCacheAccess(CacheAccess cacheAccess, Integer version) {
		super();
		this.cacheAccess = cacheAccess;
		this.version = version;
	}
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public CacheAccess getCacheAccess() {
		return cacheAccess;
	}
	public void setCacheAccess(CacheAccess cacheAccess) {
		this.cacheAccess = cacheAccess;
	}
	
	
}
