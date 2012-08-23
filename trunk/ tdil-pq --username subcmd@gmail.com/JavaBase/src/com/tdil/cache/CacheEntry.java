package com.tdil.cache;

import java.io.Serializable;

/**
 * Class <code>CacheEntry</code> are the objects finally cached.
 * The objective of this class is to be able to cache null values.
 * 
 * @author mgodoy
 *
 */
public class CacheEntry<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7829678724139454924L;
	
	private T value;

	public CacheEntry(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
