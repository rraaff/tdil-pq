package com.tdil.peugeotservice.android.rest.prevent.model;

import java.io.Serializable;


public class VehicleValueObjectBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2899891591879086678L;
	// vehicle
	private int id;
	private String domain;
	private String description;
	private String km;
	private String purchasedate;
	private String lastservicekm;
	private String lastservicedate;
	private boolean warrantyexpired;
	private boolean needsService;
	
	private ModelBean vehicleModel;
	
	public VehicleValueObjectBean() {
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


	public String getKm() {
		return km;
	}


	public void setKm(String km) {
		this.km = km;
	}


	public String getPurchasedate() {
		return purchasedate;
	}


	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}


	public String getLastservicekm() {
		return lastservicekm;
	}


	public void setLastservicekm(String lastservicekm) {
		this.lastservicekm = lastservicekm;
	}


	public String getLastservicedate() {
		return lastservicedate;
	}


	public void setLastservicedate(String lastservicedate) {
		this.lastservicedate = lastservicedate;
	}


	public boolean isWarrantyexpired() {
		return warrantyexpired;
	}


	public void setWarrantyexpired(boolean warrantyexpired) {
		this.warrantyexpired = warrantyexpired;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
