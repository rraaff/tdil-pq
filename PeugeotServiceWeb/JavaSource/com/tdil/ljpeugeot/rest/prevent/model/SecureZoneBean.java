package com.tdil.ljpeugeot.rest.prevent.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.prevent.model.SecureZone;
import com.tdil.log4j.LoggerProvider;

public class SecureZoneBean implements Serializable {

	private static final long serialVersionUID = 46657343459626873L;

	private String id;
	private String description;
	private String status;
	
	public SecureZoneBean(SecureZone state) {
		try {
			BeanUtils.copyProperties(this, state);
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	public static SecureZone asSecureZone(SecureZoneBean personBean) {
		try {
			SecureZone result = new SecureZone();
			BeanUtils.copyProperties(result, result);
			return result;
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecureZone [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
		}
		builder.append(']');
		return builder.toString();
	}
	
	public boolean isActive() {
		return this.getDescription().endsWith("ACTIVA");
	}
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(SecureZoneBean.class);
	}
	
}
