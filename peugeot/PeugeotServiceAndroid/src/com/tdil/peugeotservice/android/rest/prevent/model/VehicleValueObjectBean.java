package com.tdil.peugeotservice.android.rest.prevent.model;


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
	
	public VehicleValueObjectBean() {
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
