package com.tdil.ljpeugeot.rest.prevent.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.model.valueobjects.VehicleValueObject;
import com.tdil.ljpeugeot.rest.model.ModelBean;
import com.tdil.log4j.LoggerProvider;
import com.tdil.utils.DateUtils;

@XmlRootElement
public class VehicleValueObjectBean {

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
	
	public VehicleValueObjectBean(VehicleValueObject vehicleValueObject) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setPerMill('.');
		DecimalFormat formateador = new DecimalFormat("###,###,###",simbolos);
		this.setId(vehicleValueObject.getVehicle().getId());
		this.setDomain(vehicleValueObject.getVehicle().getDomain());
		this.setDescription(vehicleValueObject.getVehicle().getDomain() + (vehicleValueObject.getModel() != null ? "(" + vehicleValueObject.getModel().getName()+")" : ""));
		this.setKm(vehicleValueObject.getVehicle().getKm() != null ? formateador.format(vehicleValueObject.getVehicle().getKm()) : "-");
		this.setPurchasedate(vehicleValueObject.getVehicle().getPurchasedate() != null ? (DateUtils.formatDateSp(vehicleValueObject.getVehicle().getPurchasedate())) : "-");
		this.setLastservicekm(vehicleValueObject.getVehicle().getLastservicekm() != null ? formateador.format(vehicleValueObject.getVehicle().getLastservicekm()) : "-");
		this.setLastservicedate(vehicleValueObject.getVehicle().getLastservicedate() != null ? (DateUtils.formatDateSp(vehicleValueObject.getVehicle().getLastservicedate())) : "-");
		this.setWarrantyexpired(vehicleValueObject.getVehicle().getWarrantyexpired() == 1);
		this.setNeedsService(vehicleValueObject.getVehicle().getNeedsService());
		if (vehicleValueObject.getModel() != null) {
			this.vehicleModel = new ModelBean(vehicleValueObject.getModel());
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(VehicleValueObjectBean.class);
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
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

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
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

	public boolean getWarrantyexpired() {
		return warrantyexpired;
	}

	public void setWarrantyexpired(boolean warrantyexpired) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
