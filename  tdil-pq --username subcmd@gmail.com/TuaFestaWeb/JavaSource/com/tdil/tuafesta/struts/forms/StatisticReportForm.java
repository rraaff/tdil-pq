package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
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
			StatisticDAO statDao = DAOManager.getStatisticDAO();
			List<StatisticValueObject> result = statDao.selectProdCategoryStats(params);
			System.out.println(result);
			for (StatisticValueObject svo : result) {
				List<Object> row = new ArrayList<Object>();
				row.add(svo.getData());
				row.add(svo.getObjecttime());
				row.add(svo.getQuantity());
				full.add(row);
			}
		}
		return full;
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
