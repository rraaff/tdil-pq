package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.struts.ValidationError;
import com.tdil.struts.forms.ReportForm;
import com.tdil.tuafesta.dao.StatisticDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.valueobjects.StatisticValueObject;
import com.tdil.tuafesta.stats.StatisticType;

public class StatisticReportForm extends ReportForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int sStatType;
	private int sGroup;
	private String sFrom;
	private String sTo;
	private int sSum;

	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}
	
	@Override
	public void initWith(int id) throws SQLException {
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public List<List<Object>> search() throws SQLException {
		List<List<Object>> full = new ArrayList<List<Object>>();
		if (this.sStatType == StatisticType.PROD_CATEGORY_SEARCH.getID()) {
			searchByProdCategories(full);
		}
		if (this.sStatType == StatisticType.SERV_CATEGORY_SEARCH.getID()) {
			searchByServCategories(full);
		}
		if (this.sStatType == StatisticType.GEO_LEVEL_SEARCH.getID()) {
			searchByGeoLevels(full);
		}
		if (this.sStatType == StatisticType.PROFESIONAL_VIEW.getID()) {
			searchProfesionalView(full);
		}
		if (this.sStatType == StatisticType.PROFESIONAL_CONTACT.getID()) {
			searchProfesionalContact(full);
		}
		if (this.sStatType == StatisticType.PROFESIONAL_REGISTRATION.getID()) {
			searchProfesionalRegistration(full);
		}
		if (this.sStatType == StatisticType.CLIENT_REGISTRATION.getID()) {
			searchClientRegistration(full);
		}
		if (this.sStatType == StatisticType.PROMOTION_CONTACT.getID()) {
			searchPromotionContact(full);
		}
		return full;
	}

	private void searchByGeoLevels(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectGeoLevelStats(params);
		fillResult(full, result);
	}

	private void searchByProdCategories(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectProdCategoryStats(params);
		fillResult(full, result);
	}
	
	private void searchProfesionalView(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectProfesionalViewStats(params);
		fillResult(full, result);
	}
	
	private void searchProfesionalContact(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectProfesionalContactStats(params);
		fillResult(full, result);
	}
	
	private void searchPromotionContact(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectPromotionContactStats(params);
		fillResult(full, result);
	}
	
	private void searchProfesionalRegistration(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectProfesionalRegistrationStats(params);
		fillResult(full, result);
	}
	
	private void searchClientRegistration(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectClientRegistrationStats(params);
		fillResult(full, result);
	}

	private void fillResult(List<List<Object>> full, List<StatisticValueObject> result) {
		for (StatisticValueObject svo : result) {
			List<Object> row = new ArrayList<Object>();
			row.add(svo.getData());
			row.add(svo.getObjecttime());
			row.add(svo.getQuantity());
			full.add(row);
		}
	}

	private Map<String, Object> getSearchParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		boolean groupbyobject = false;
		boolean groupbydate = false;
		if (sGroup == 1) {
			params.put("groupby", "1");
			params.put("groupbyobject", "1");
			groupbyobject = true;
		}
		if (sSum == 0) {
			params.put("dateformated", "%Y-%m-%d %H:%i:%S");
		}
		if (sSum == 1) {
			params.put("groupby", "1");
			params.put("dateformated", "%Y-%m");
			params.put("groupbydate", "1");
			groupbydate = true;
		}
		if (sSum == 2) {
			params.put("groupby", "1");
			params.put("dateformated", "%Y-%m-%d");
			params.put("groupbydate", "1");
			groupbydate = true;
		}
		if (groupbyobject && !groupbydate) {
			params.put("datena", "1");
		}
		if (!groupbyobject && groupbydate) {
			params.put("objectna", "1");
		}
		Date sfrom = com.tdil.utils.DateUtils.parseDateSp(this.getsFrom());
		if (sfrom != null) {
			params.put("sFrom", sfrom);
		}
		Date sto = com.tdil.utils.DateUtils.parseDateSp(this.getsTo());
		if (sto != null) {
			params.put("sTo", com.tdil.utils.DateUtils.date2LastMomentOfDate(sto));
		}
		return params;
	}
	
	private void searchByServCategories(List<List<Object>> full) throws SQLException {
		Map<String, Object> params = getSearchParams();
		StatisticDAO statDao = DAOManager.getStatisticDAO();
		List<StatisticValueObject> result = statDao.selectServCategoryStats(params);
		fillResult(full, result);
	}

	public int getsStatType() {
		return sStatType;
	}

	public void setsStatType(int sStatType) {
		this.sStatType = sStatType;
	}

	public int getsGroup() {
		return sGroup;
	}

	public void setsGroup(int sGroup) {
		this.sGroup = sGroup;
	}

	public String getsFrom() {
		return sFrom;
	}

	public void setsFrom(String sFrom) {
		this.sFrom = sFrom;
	}

	public String getsTo() {
		return sTo;
	}

	public void setsTo(String sTo) {
		this.sTo = sTo;
	}

	public int getsSum() {
		return sSum;
	}

	public void setsSum(int sSum) {
		this.sSum = sSum;
	}
}
