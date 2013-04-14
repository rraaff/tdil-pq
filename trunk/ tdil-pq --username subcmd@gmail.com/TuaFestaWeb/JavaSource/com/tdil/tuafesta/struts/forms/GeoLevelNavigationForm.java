package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.utils.CategoryUtils;
import com.tdil.tuafesta.utils.GeoLevelUtils;

public class GeoLevelNavigationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(GeoLevelNavigationForm.class);

	private int level;
	private int geoLevelId;
	
	private Geo2 geo2;
	private Geo3 geo3;
	private Geo4 geo4;
	
	private List<Geo2> geo2List;
	private List<Geo3> geo3List;
	private List<Geo4> geo4List;

	private List<SellValueObject> sells;
	
	public void initWith(int geoId, int level) throws SQLException {
		Map<String, Object> geosearch = new HashMap<String, Object>();
		sells = new ArrayList<SellValueObject>();
		if (level == 1) {
			geoLevelId = 0;
			geo2 = null;
			geo3 = null;
			geo4 = null;
			geo2List = GeoLevelUtils.getActiveGeo2Levels();
			geo3List = new ArrayList<Geo3>();
			geo4List = new ArrayList<Geo4>();
		}
		if (level == 2) {
			geoLevelId = geoId;
			geo2 = GeoLevelUtils.getGeo2(geoId);
			geo3 = null;
			geo4 = null;
			geo2List = new ArrayList<Geo2>();
			geo3List = GeoLevelUtils.getActiveGeo3Levels(geoId);
			geo4List = new ArrayList<Geo4>();
			geosearch.put("level2", geoLevelId);
		}
		if (level == 3) {
			geoLevelId = geoId;
			geo3 = GeoLevelUtils.getGeo3(geoId);
			geo2 = GeoLevelUtils.getGeo2(geo3.getGeo2Id());
			geo4 = null;
			geo2List = new ArrayList<Geo2>();
			geo3List = new ArrayList<Geo3>();
			geo4List = GeoLevelUtils.getActiveGeo4Levels(geoId);
			geosearch.put("level3", geo3.getId());
			geosearch.put("level2", geo3.getGeo2Id());
		}
		if (level == 4) {
			geoLevelId = geoId;
			geo4 = GeoLevelUtils.getGeo4(geoId);
			geo3 = GeoLevelUtils.getGeo3(geo4.getGeo3Id());
			geo2 = GeoLevelUtils.getGeo2(geo3.getGeo2Id());
			geo2List = new ArrayList<Geo2>();
			geo3List = new ArrayList<Geo3>();
			geo4List = new ArrayList<Geo4>();
			
			geosearch.put("level4", geo4.getId());
			geosearch.put("level3", geo4.getGeo3Id());
			geosearch.put("level2", geo3.getGeo2Id());
		}
		if (!geosearch.isEmpty()) {
			sells.addAll(getProductSells(geosearch));
			sells.addAll(getServiceSells(geosearch));
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List<SellValueObject> getProductSells(final Map<String, Object> geos)  {
		try {
			return (List<SellValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getSellDAO().selectProductSellsByGeoLevel(geos);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<SellValueObject>();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List<SellValueObject> getServiceSells(final Map<String, Object> geos)  {
		try {
			return (List<SellValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getSellDAO().selectServicesSellsByGeoLevel(geos);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<SellValueObject>();
		}
	}

	public List<SellValueObject> getSells() {
		return sells;
	}

	public void setSells(List<SellValueObject> sells) {
		this.sells = sells;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getGeoLevelId() {
		return geoLevelId;
	}

	public void setGeoLevelId(int geoLevelId) {
		this.geoLevelId = geoLevelId;
	}

	public Geo2 getGeo2() {
		return geo2;
	}

	public void setGeo2(Geo2 geo2) {
		this.geo2 = geo2;
	}

	public Geo3 getGeo3() {
		return geo3;
	}

	public void setGeo3(Geo3 geo3) {
		this.geo3 = geo3;
	}

	public Geo4 getGeo4() {
		return geo4;
	}

	public void setGeo4(Geo4 geo4) {
		this.geo4 = geo4;
	}

	public List<Geo2> getGeo2List() {
		return geo2List;
	}

	public void setGeo2List(List<Geo2> geo2List) {
		this.geo2List = geo2List;
	}

	public List<Geo3> getGeo3List() {
		return geo3List;
	}

	public void setGeo3List(List<Geo3> geo3List) {
		this.geo3List = geo3List;
	}

	public List<Geo4> getGeo4List() {
		return geo4List;
	}

	public void setGeo4List(List<Geo4> geo4List) {
		this.geo4List = geo4List;
	}
	

}
