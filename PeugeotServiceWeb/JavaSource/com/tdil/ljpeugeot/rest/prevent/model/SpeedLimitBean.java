package com.tdil.ljpeugeot.rest.prevent.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.prevent.model.SpeedLimit;
import com.tdil.log4j.LoggerProvider;

public class SpeedLimitBean implements Serializable {

	private static final long serialVersionUID = 8350465465200249803L;
	
	private String id;
	
	private String description;
	
	public SpeedLimitBean(SpeedLimit vehicle) {
		try {
			BeanUtils.copyProperties(this, vehicle);
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	public static SpeedLimit asSpeedLimit(SpeedLimitBean personBean) {
		try {
			SpeedLimit result = new SpeedLimit();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpeedLimit [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
		}
		builder.append(']');
		return builder.toString();
	}

	public boolean isActive() {
		return this.getDescription().endsWith("ACTIVA");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SpeedLimitBean.class);
	}
	
}
