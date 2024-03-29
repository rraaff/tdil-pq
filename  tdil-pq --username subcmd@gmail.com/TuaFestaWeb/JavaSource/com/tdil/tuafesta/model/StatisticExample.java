package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class StatisticExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public StatisticExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected StatisticExample(StatisticExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table STATISTIC
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected abstract static class GeneratedCriteria {
		protected List<String> criteriaWithoutValue;
		protected List<Map<String, Object>> criteriaWithSingleValue;
		protected List<Map<String, Object>> criteriaWithListValue;
		protected List<Map<String, Object>> criteriaWithBetweenValue;

		protected GeneratedCriteria() {
			super();
			criteriaWithoutValue = new ArrayList<String>();
			criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
			criteriaWithListValue = new ArrayList<Map<String, Object>>();
			criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0 || criteriaWithBetweenValue.size() > 0;
		}

		public List<String> getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List<Map<String, Object>> getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List<Map<String, Object>> getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List<Map<String, Object>> getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List<? extends Object> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			List<Object> list = new ArrayList<Object>();
			list.add(value1);
			list.add(value2);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andIdIsNull() {
			addCriterion("stat.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("stat.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("stat.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("stat.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("stat.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("stat.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("stat.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("stat.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("stat.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("stat.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("stat.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("stat.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andTextdataIsNull() {
			addCriterion("stat.textData is null");
			return (Criteria) this;
		}

		public Criteria andTextdataIsNotNull() {
			addCriterion("stat.textData is not null");
			return (Criteria) this;
		}

		public Criteria andTextdataEqualTo(String value) {
			addCriterion("stat.textData =", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataNotEqualTo(String value) {
			addCriterion("stat.textData <>", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataGreaterThan(String value) {
			addCriterion("stat.textData >", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataGreaterThanOrEqualTo(String value) {
			addCriterion("stat.textData >=", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataLessThan(String value) {
			addCriterion("stat.textData <", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataLessThanOrEqualTo(String value) {
			addCriterion("stat.textData <=", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataLike(String value) {
			addCriterion("stat.textData like", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataNotLike(String value) {
			addCriterion("stat.textData not like", value, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataIn(List<String> values) {
			addCriterion("stat.textData in", values, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataNotIn(List<String> values) {
			addCriterion("stat.textData not in", values, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataBetween(String value1, String value2) {
			addCriterion("stat.textData between", value1, value2, "textdata");
			return (Criteria) this;
		}

		public Criteria andTextdataNotBetween(String value1, String value2) {
			addCriterion("stat.textData not between", value1, value2, "textdata");
			return (Criteria) this;
		}

		public Criteria andStattypeIsNull() {
			addCriterion("stat.statType is null");
			return (Criteria) this;
		}

		public Criteria andStattypeIsNotNull() {
			addCriterion("stat.statType is not null");
			return (Criteria) this;
		}

		public Criteria andStattypeEqualTo(Integer value) {
			addCriterion("stat.statType =", value, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeNotEqualTo(Integer value) {
			addCriterion("stat.statType <>", value, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeGreaterThan(Integer value) {
			addCriterion("stat.statType >", value, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("stat.statType >=", value, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeLessThan(Integer value) {
			addCriterion("stat.statType <", value, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeLessThanOrEqualTo(Integer value) {
			addCriterion("stat.statType <=", value, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeIn(List<Integer> values) {
			addCriterion("stat.statType in", values, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeNotIn(List<Integer> values) {
			addCriterion("stat.statType not in", values, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeBetween(Integer value1, Integer value2) {
			addCriterion("stat.statType between", value1, value2, "stattype");
			return (Criteria) this;
		}

		public Criteria andStattypeNotBetween(Integer value1, Integer value2) {
			addCriterion("stat.statType not between", value1, value2, "stattype");
			return (Criteria) this;
		}

		public Criteria andObjectidIsNull() {
			addCriterion("stat.objectId is null");
			return (Criteria) this;
		}

		public Criteria andObjectidIsNotNull() {
			addCriterion("stat.objectId is not null");
			return (Criteria) this;
		}

		public Criteria andObjectidEqualTo(Integer value) {
			addCriterion("stat.objectId =", value, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidNotEqualTo(Integer value) {
			addCriterion("stat.objectId <>", value, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidGreaterThan(Integer value) {
			addCriterion("stat.objectId >", value, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidGreaterThanOrEqualTo(Integer value) {
			addCriterion("stat.objectId >=", value, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidLessThan(Integer value) {
			addCriterion("stat.objectId <", value, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidLessThanOrEqualTo(Integer value) {
			addCriterion("stat.objectId <=", value, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidIn(List<Integer> values) {
			addCriterion("stat.objectId in", values, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidNotIn(List<Integer> values) {
			addCriterion("stat.objectId not in", values, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidBetween(Integer value1, Integer value2) {
			addCriterion("stat.objectId between", value1, value2, "objectid");
			return (Criteria) this;
		}

		public Criteria andObjectidNotBetween(Integer value1, Integer value2) {
			addCriterion("stat.objectId not between", value1, value2, "objectid");
			return (Criteria) this;
		}

		public Criteria andExtraidIsNull() {
			addCriterion("stat.extraId is null");
			return (Criteria) this;
		}

		public Criteria andExtraidIsNotNull() {
			addCriterion("stat.extraId is not null");
			return (Criteria) this;
		}

		public Criteria andExtraidEqualTo(Integer value) {
			addCriterion("stat.extraId =", value, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidNotEqualTo(Integer value) {
			addCriterion("stat.extraId <>", value, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidGreaterThan(Integer value) {
			addCriterion("stat.extraId >", value, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidGreaterThanOrEqualTo(Integer value) {
			addCriterion("stat.extraId >=", value, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidLessThan(Integer value) {
			addCriterion("stat.extraId <", value, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidLessThanOrEqualTo(Integer value) {
			addCriterion("stat.extraId <=", value, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidIn(List<Integer> values) {
			addCriterion("stat.extraId in", values, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidNotIn(List<Integer> values) {
			addCriterion("stat.extraId not in", values, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidBetween(Integer value1, Integer value2) {
			addCriterion("stat.extraId between", value1, value2, "extraid");
			return (Criteria) this;
		}

		public Criteria andExtraidNotBetween(Integer value1, Integer value2) {
			addCriterion("stat.extraId not between", value1, value2, "extraid");
			return (Criteria) this;
		}

		public Criteria andObjecttimeIsNull() {
			addCriterion("stat.objectTime is null");
			return (Criteria) this;
		}

		public Criteria andObjecttimeIsNotNull() {
			addCriterion("stat.objectTime is not null");
			return (Criteria) this;
		}

		public Criteria andObjecttimeEqualTo(Date value) {
			addCriterion("stat.objectTime =", value, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeNotEqualTo(Date value) {
			addCriterion("stat.objectTime <>", value, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeGreaterThan(Date value) {
			addCriterion("stat.objectTime >", value, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeGreaterThanOrEqualTo(Date value) {
			addCriterion("stat.objectTime >=", value, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeLessThan(Date value) {
			addCriterion("stat.objectTime <", value, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeLessThanOrEqualTo(Date value) {
			addCriterion("stat.objectTime <=", value, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeIn(List<Date> values) {
			addCriterion("stat.objectTime in", values, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeNotIn(List<Date> values) {
			addCriterion("stat.objectTime not in", values, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeBetween(Date value1, Date value2) {
			addCriterion("stat.objectTime between", value1, value2, "objecttime");
			return (Criteria) this;
		}

		public Criteria andObjecttimeNotBetween(Date value1, Date value2) {
			addCriterion("stat.objectTime not between", value1, value2, "objecttime");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("stat.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("stat.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("stat.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("stat.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("stat.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("stat.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("stat.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("stat.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("stat.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("stat.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("stat.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("stat.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table STATISTIC
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 20 20:10:42 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}