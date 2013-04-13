package com.tdil.tuafesta.model.valueobjects;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.utils.TreeCategoryUtils;

public class SellValueObject extends Sell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7335582850975800212L;
	
	private String geo2name;
	private String geo3name;
	private String geo4name;
	
	private String serviceAreas;
	
	private String profesionalbusinessname;
	
	public String getSellTypeDescription() {
		return ApplicationResources.getMessage(this.getType() == SellType.PRODUCT ? "PRODUCT" : "SERVICE");
	}
	
	public boolean isProduct() {
		return this.getType() == SellType.PRODUCT;
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
	
	public String getProfesionalbusinessname() {
		return profesionalbusinessname;
	}

	public void setProfesionalbusinessname(String profesionalbusinessname) {
		this.profesionalbusinessname = profesionalbusinessname;
	}
	
	public String getGeoLevelPath() {
		if (this.isProduct()) {
			if (this.getGeo2name() == null || StringUtils.isEmpty(this.getGeo2name())) {
				return "-";
			} else {
				return this.getGeo2name() + " > " + this.getGeo3name() + " > " + this.getGeo4name();
			}
		} else {
			if (serviceAreas == null) {
				serviceAreas = getServiceAreas();
			}
			return serviceAreas;
		}
	}

	private String getServiceAreas() {
		try {
			return (String)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Collection<GeoLevelValueObject> resultList = DAOManager.getServiceAreaDAO().selectServiceAreaFor(SellValueObject.this.getIdProfesional());
					StringBuilder result = new StringBuilder();
					for (GeoLevelValueObject geoValueObject : resultList) {
						result.append(geoValueObject.getNombre()).append(", ");
					}
					if (result.length() > 2) {
						result.deleteCharAt(result.length() - 1);
						result.deleteCharAt(result.length() - 1);
					}
					return result.toString();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
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
