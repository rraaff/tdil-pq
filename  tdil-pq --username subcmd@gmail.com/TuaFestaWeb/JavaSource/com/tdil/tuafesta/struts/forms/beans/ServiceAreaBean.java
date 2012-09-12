package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;
import java.sql.SQLException;

import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.ServiceArea;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

public class ServiceAreaBean implements Serializable {

	// TODO a esto le falta considerar que puede ser una edicion
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5478327470170295601L;

	private int index;
	
	private int id;
	private boolean approved;
	private int level;
	private int geoLevelId;
	private String serviceAreaText;

	public ServiceAreaBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ServiceAreaBean(ServiceArea serviceArea) throws SQLException {
		// TODO Auto-generated constructor stub
		this.id = serviceArea.getId();
		this.approved = serviceArea.getApproved().equals(1);
		this.level = serviceArea.getLevel();
		this.geoLevelId = serviceArea.getIdGeolevel();
		if (level == 2) {
			GeoLevelValueObject vo = DAOManager.getGeo2DAO().selectGeoLevelsByGeo2(this.getGeoLevelId());
//			serviceAreaText = vo.getAreaText();
			serviceAreaText = vo.getNombre();
		} else {
			if (level == 3) {
				GeoLevelValueObject vo = DAOManager.getGeo3DAO().selectGeoLevelsByGeo3(this.getGeoLevelId());
//				serviceAreaText = vo.getAreaText();
				serviceAreaText = vo.getNombre();
			} else {
				GeoLevelValueObject vo = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(this.getGeoLevelId());
//				serviceAreaText = vo.getAreaText();
				serviceAreaText = vo.getNombre();
			}
		}
//		this.serviceAreaText = serviceArea.get
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getGeoLevelId() {
		return geoLevelId;
	}
	public void setGeoLevelId(int geoLevel4Id) {
		this.geoLevelId = geoLevel4Id;
	}
	public String getServiceAreaText() {
		return serviceAreaText;
	}
	public void setServiceAreaText(String serviceAreaText) {
		this.serviceAreaText = serviceAreaText;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
}
