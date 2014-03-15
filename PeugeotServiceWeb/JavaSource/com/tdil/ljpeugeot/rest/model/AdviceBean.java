package com.tdil.ljpeugeot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.valueobjects.AdviceValueObject;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.utils.DateUtils;

@XmlRootElement
public class AdviceBean {

	private int id;
	private String domain;
	private String description;
	private Integer km;
	private String servicedate;
	
	public AdviceBean(AdviceValueObject adviceValueObject) {
		this.id = adviceValueObject.getAdvice().getId();
		this.domain = adviceValueObject.getVehicle().getDomain();
		if (adviceValueObject.getVehicle().getIdModel() != null && adviceValueObject.getVehicle().getIdModel() != 0) {
			Model model = PeugeotService.getModel(adviceValueObject.getVehicle().getIdModel());
			if (model != null) {
				this.setDescription(adviceValueObject.getVehicle().getDomain() + "(" + model.getName()+")");
			} else {
				this.description = adviceValueObject.getVehicle().getDomain();
			}
		} else {
			this.description = adviceValueObject.getVehicle().getDomain();
		}
		this.km = adviceValueObject.getAdvice().getKm();
		if (adviceValueObject.getAdvice().getServicedate() != null) {
			this.servicedate = DateUtils.formatDateSp(adviceValueObject.getAdvice().getServicedate());
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getServicedate() {
		return servicedate;
	}
	public void setServicedate(String servicedate) {
		this.servicedate = servicedate;
	}
}
