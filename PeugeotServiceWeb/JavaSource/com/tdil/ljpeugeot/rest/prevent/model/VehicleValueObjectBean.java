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
	private String purchasedateString;
	private String domain;
	private String description;
	private Integer km;
	private Integer lastservicekm;
	private String lastservicedateString;
	private Integer warrantyexpired;
	private boolean needsService;
	
	private ModelBean vehicleModel;
	
	public VehicleValueObjectBean(VehicleValueObject vehicleValueObject) {
		try {
			BeanUtils.copyProperties(this, vehicleValueObject.getVehicle());
			if (vehicleValueObject.getModel() != null) {
				this.vehicleModel = new ModelBean(vehicleValueObject.getModel());
			}
			this.setPurchasedateString(vehicleValueObject.getVehicle().getPurchasedate() == null ? "" : vehicleValueObject.getVehicle().getPurchasedate().toString()); // TODO
			this.setLastservicedateString(vehicleValueObject.getVehicle().getLastservicedate() == null ? "" : vehicleValueObject.getVehicle().getLastservicedate().toString()); // TODO
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(VehicleValueObjectBean.class);
	}

	public String getPurchasedateString() {
		return purchasedateString;
	}

	public void setPurchasedateString(String purchasedate) {
		this.purchasedateString = purchasedate;
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

	public String getLastservicedateString() {
		return lastservicedateString;
	}

	public void setLastservicedateString(String lastservicedate) {
		this.lastservicedateString = lastservicedate;
	}

	public Integer getWarrantyexpired() {
		return warrantyexpired;
	}

	public void setWarrantyexpired(Integer warrantyexpired) {
		this.warrantyexpired = warrantyexpired;
	}

	public boolean getNeedsService() {
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
