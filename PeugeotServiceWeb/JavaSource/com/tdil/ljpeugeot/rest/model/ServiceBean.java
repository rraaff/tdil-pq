package com.tdil.ljpeugeot.rest.model;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.model.Service;
import com.tdil.log4j.LoggerProvider;
import com.tdil.utils.DateUtils;

public class ServiceBean {

	private Integer idVechicle;
	private Integer km;
	private String serviceDate;
	
	public ServiceBean(Service contactData) {
		try {
			BeanUtils.copyProperties(this, contactData);
			this.setServiceDate(DateUtils.formatDate(contactData.getServicedate()));
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public static Service asService(ServiceBean contactDataBean) {
		try {
			Service result = new Service();
			BeanUtils.copyProperties(result, contactDataBean);
			result.setServicedate(DateUtils.parseDate(contactDataBean.getServiceDate()));
			return result;
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public Integer getIdVechicle() {
		return idVechicle;
	}
	public void setIdVechicle(Integer idVechicle) {
		this.idVechicle = idVechicle;
	}
	public Integer getKm() {
		return km;
	}
	public void setKm(Integer km) {
		this.km = km;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ServiceBean.class);
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
}
