package com.tdil.ljpeugeot.rest.model;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

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
	private String warrantyDescription;
	
	public ModelBean(Model model) {
		try {
			BeanUtils.copyProperties(this, model);
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setPerMill('.');
			DecimalFormat formateador = new DecimalFormat("###,###,###",simbolos);
			int years = model.getMonthwarranty() / 12;
			this.warrantyDescription = String.valueOf(years);
			this.warrantyDescription = this.warrantyDescription + (years == 1 ? "año" : "años");
			if (model.getKmwarranty() != 0) { 
				this.warrantyDescription = this.warrantyDescription + " o ";
				this.warrantyDescription = this.warrantyDescription + formateador.format(model.getKmwarranty());
				this.warrantyDescription = this.warrantyDescription + "km";
			 } 
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

	public String getWarrantyDescription() {
		return warrantyDescription;
	}

	public void setWarrantyDescription(String warrantyDescription) {
		this.warrantyDescription = warrantyDescription;
	}
	
}
