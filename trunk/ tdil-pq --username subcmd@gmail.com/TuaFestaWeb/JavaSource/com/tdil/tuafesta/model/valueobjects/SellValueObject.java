package com.tdil.tuafesta.model.valueobjects;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.utils.TreeCategoryUtils;

public class SellValueObject extends Sell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7335582850975800212L;
	
	private String name;
	private String description;
	
	private String geo2name;
	private String geo3name;
	private String geo4name;
	
	private String profesionalbusinessname;
	
	public String getSellTypeDescription() {
		return ApplicationResources.getMessage(this.getType() == SellType.PRODUCT ? "PRODUCT" : "SERVICE");
	}
	
	public String getStatusText() {
		if (this.getApproved() != null && this.getApproved().equals(1)) {
			return ApplicationResources.getMessage("APPROVED");
		} else {
			return ApplicationResources.getMessage("PENDING");
		}
	}
	
	public String getCategoryText() {
		try {
			return TreeCategoryUtils.getCategoryPath(this.getIdCategory());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return "";
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SellValueObject.class);
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

	public String getProfesionalbusinessname() {
		return profesionalbusinessname;
	}

	public void setProfesionalbusinessname(String profesionalbusinessname) {
		this.profesionalbusinessname = profesionalbusinessname;
	}
	
	public String getGeoLevelPath() {
		if (this.getGeo2name() == null || StringUtils.isEmpty(this.getGeo2name())) {
			return "-";
		} else {
			return this.getGeo2name() + " > " + this.getGeo3name() + " > " + this.getGeo4name();
		}
	}

	public String getGeo2name() {
		return geo2name;
	}

	public void setGeo2name(String geo2name) {
		this.geo2name = geo2name;
	}

	public String getGeo3name() {
		return geo3name;
	}

	public void setGeo3name(String geo3name) {
		this.geo3name = geo3name;
	}

	public String getGeo4name() {
		return geo4name;
	}

	public void setGeo4name(String geo4name) {
		this.geo4name = geo4name;
	}

}
