package com.tdil.ljpeugeot.rest.model;

import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.log4j.LoggerProvider;
import com.tdil.utils.DateUtils;

@XmlRootElement
public class VehicleBean {

	private Integer id;
	private Integer idModel;
	private Integer idDealer;
	private String purchaseDate;
	private String domain;
	private String description;
	private Integer km;
	private Integer lastservicekm;
	private String lastServiceDate;
	
	public VehicleBean() {
	}
	
	public VehicleBean(Vehicle contactData) {
		try {
			BeanUtils.copyProperties(this, contactData);
			this.setPurchaseDate(DateUtils.formatDate(contactData.getPurchasedate()));
			this.setLastServiceDate(DateUtils.formatDate(contactData.getLastservicedate()));
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(VehicleBean.class);
	}

	public Integer getIdModel() {
		return idModel;
	}

	public void setIdModel(Integer idModel) {
		this.idModel = idModel;
	}

	public Integer getIdDealer() {
		return idDealer;
	}

	public void setIdDealer(Integer idDealer) {
		this.idDealer = idDealer;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Integer getLastservicekm() {
		return lastservicekm;
	}

	public void setLastservicekm(Integer lastservicekm) {
		this.lastservicekm = lastservicekm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	
}
