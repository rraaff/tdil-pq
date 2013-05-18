package com.tdil.cache;

import org.apache.jcs.access.CacheAccess;

public class TimeoutCacheAccess {

	private long timestamp;
	private long timeout; /* en millisegundos*/
	private CacheAccess cacheAccess;

	public TimeoutCacheAccess(CacheAccess cacheAccess, long timeout) {
		super();
		this.cacheAccess = cacheAccess;
		this.timeout = timeout;
		this.timestamp = System.currentTimeMillis();
	}

	public CacheAccess getCacheAccess() {
		return cacheAccess;
	}
	public void setCacheAccess(CacheAccess cacheAccess) {
		this.cacheAccess = cacheAccess;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isOutOfDate() {
		if (this.getTimeout() == -1) {
			return true;
		} else {
			if (this.getTimeout() == 0) {
				return false;
			} else {
				return System.currentTimeMillis() > (this.getTimestamp() + this.getTimeout());
			}
		}
	}

	public void updateTimestamp() {
		this.setTimestamp(System.currentTimeMillis());

	}


}
