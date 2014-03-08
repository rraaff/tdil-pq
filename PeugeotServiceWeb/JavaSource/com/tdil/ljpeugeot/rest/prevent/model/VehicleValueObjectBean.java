package com.tdil.ljpeugeot.rest.prevent.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.model.valueobjects.VehicleValueObject;
import com.tdil.ljpeugeot.rest.model.ModelBean;
import com.tdil.log4j.LoggerProvider;

@XmlRootElement
public class VehicleValueObjectBean {

	// vehicle
	private Date purchasedate;
	private String domain;
	private String description;
	private Integer km;
	private Integer lastservicekm;
	private Date lastservicedate;
	private Integer warrantyexpired;
	private boolean needsService;
	
	private ModelBean vehicleModel;
	
	public VehicleValueObjectBean(VehicleValueObject vehicleValueObject) {
		try {
			BeanUtils.copyProperties(this, vehicleValueObject.getVehicle());
			if (vehicleValueObject.getModel() != null) {
				this.vehicleModel = new ModelBean(vehicleValueObject.getModel());
			}
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(VehicleValueObjectBean.class);
	}

	public Date getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
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

	public Date getLastservicedate() {
		return lastservicedate;
	}

	public void setLastservicedate(Date lastservicedate) {
		this.lastservicedate = lastservicedate;
	}

	public Integer getWarrantyexpired() {
		return warrantyexpired;
	}

	public void setWarrantyexpired(Integer warrantyexpired) {
		this.warrantyexpired = warrantyexpired;
	}

	public boolean isNeedsService() {
		return needsService;
	}

	public void setNeedsService(boolean needsService) {
		this.needsService = needsService;
	}

	public ModelBean getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(ModelBean model) {
		this.vehicleModel = model;
	}
	
}
