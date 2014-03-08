package com.tdil.ljpeugeot.rest.model;

import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.model.Model;
import com.tdil.log4j.LoggerProvider;

@XmlRootElement
public class ModelBean {

	private String name;
	private String description;
	private Integer monthwarranty;
	private Integer kmwarranty;
	
	public ModelBean(Model model) {
		try {
			BeanUtils.copyProperties(this, model);
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ModelBean.class);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMonthwarranty() {
		return monthwarranty;
	}
	public void setMonthwarranty(Integer monthwarranty) {
		this.monthwarranty = monthwarranty;
	}

	public Integer getKmwarranty() {
		return kmwarranty;
	}

	public void setKmwarranty(Integer kmwarranty) {
		this.kmwarranty = kmwarranty;
	}
	
}
