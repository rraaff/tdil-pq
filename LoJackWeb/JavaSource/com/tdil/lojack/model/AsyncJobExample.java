package com.tdil.lojack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsyncJobExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public AsyncJobExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	protected AsyncJobExample(AsyncJobExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ASYNC_JOB
	 * @mbggenerated  Fri Jul 05 18:38:52 ART 2013
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
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0 || criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
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
			addCriterion("aj.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("aj.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("aj.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("aj.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("aj.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("aj.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("aj.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("aj.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("aj.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("aj.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdjobIsNull() {
			addCriterion("aj.idJob is null");
			return (Criteria) this;
		}

		public Criteria andIdjobIsNotNull() {
			addCriterion("aj.idJob is not null");
			return (Criteria) this;
		}

		public Criteria andIdjobEqualTo(Integer value) {
			addCriterion("aj.idJob =", value, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobNotEqualTo(Integer value) {
			addCriterion("aj.idJob <>", value, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobGreaterThan(Integer value) {
			addCriterion("aj.idJob >", value, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.idJob >=", value, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobLessThan(Integer value) {
			addCriterion("aj.idJob <", value, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobLessThanOrEqualTo(Integer value) {
			addCriterion("aj.idJob <=", value, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobIn(List<Integer> values) {
			addCriterion("aj.idJob in", values, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobNotIn(List<Integer> values) {
			addCriterion("aj.idJob not in", values, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobBetween(Integer value1, Integer value2) {
			addCriterion("aj.idJob between", value1, value2, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdjobNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.idJob not between", value1, value2, "idjob");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserIsNull() {
			addCriterion("aj.idWebsiteUser is null");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserIsNotNull() {
			addCriterion("aj.idWebsiteUser is not null");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserEqualTo(Integer value) {
			addCriterion("aj.idWebsiteUser =", value, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserNotEqualTo(Integer value) {
			addCriterion("aj.idWebsiteUser <>", value, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserGreaterThan(Integer value) {
			addCriterion("aj.idWebsiteUser >", value, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.idWebsiteUser >=", value, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserLessThan(Integer value) {
			addCriterion("aj.idWebsiteUser <", value, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserLessThanOrEqualTo(Integer value) {
			addCriterion("aj.idWebsiteUser <=", value, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserIn(List<Integer> values) {
			addCriterion("aj.idWebsiteUser in", values, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserNotIn(List<Integer> values) {
			addCriterion("aj.idWebsiteUser not in", values, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserBetween(Integer value1, Integer value2) {
			addCriterion("aj.idWebsiteUser between", value1, value2, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdwebsiteuserNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.idWebsiteUser not between", value1, value2, "idwebsiteuser");
			return (Criteria) this;
		}

		public Criteria andPostdateIsNull() {
			addCriterion("aj.postDate is null");
			return (Criteria) this;
		}

		public Criteria andPostdateIsNotNull() {
			addCriterion("aj.postDate is not null");
			return (Criteria) this;
		}

		public Criteria andPostdateEqualTo(Date value) {
			addCriterion("aj.postDate =", value, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateNotEqualTo(Date value) {
			addCriterion("aj.postDate <>", value, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateGreaterThan(Date value) {
			addCriterion("aj.postDate >", value, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateGreaterThanOrEqualTo(Date value) {
			addCriterion("aj.postDate >=", value, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateLessThan(Date value) {
			addCriterion("aj.postDate <", value, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateLessThanOrEqualTo(Date value) {
			addCriterion("aj.postDate <=", value, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateIn(List<Date> values) {
			addCriterion("aj.postDate in", values, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateNotIn(List<Date> values) {
			addCriterion("aj.postDate not in", values, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateBetween(Date value1, Date value2) {
			addCriterion("aj.postDate between", value1, value2, "postdate");
			return (Criteria) this;
		}

		public Criteria andPostdateNotBetween(Date value1, Date value2) {
			addCriterion("aj.postDate not between", value1, value2, "postdate");
			return (Criteria) this;
		}

		public Criteria andActionIsNull() {
			addCriterion("aj.action is null");
			return (Criteria) this;
		}

		public Criteria andActionIsNotNull() {
			addCriterion("aj.action is not null");
			return (Criteria) this;
		}

		public Criteria andActionEqualTo(Integer value) {
			addCriterion("aj.action =", value, "action");
			return (Criteria) this;
		}

		public Criteria andActionNotEqualTo(Integer value) {
			addCriterion("aj.action <>", value, "action");
			return (Criteria) this;
		}

		public Criteria andActionGreaterThan(Integer value) {
			addCriterion("aj.action >", value, "action");
			return (Criteria) this;
		}

		public Criteria andActionGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.action >=", value, "action");
			return (Criteria) this;
		}

		public Criteria andActionLessThan(Integer value) {
			addCriterion("aj.action <", value, "action");
			return (Criteria) this;
		}

		public Criteria andActionLessThanOrEqualTo(Integer value) {
			addCriterion("aj.action <=", value, "action");
			return (Criteria) this;
		}

		public Criteria andActionIn(List<Integer> values) {
			addCriterion("aj.action in", values, "action");
			return (Criteria) this;
		}

		public Criteria andActionNotIn(List<Integer> values) {
			addCriterion("aj.action not in", values, "action");
			return (Criteria) this;
		}

		public Criteria andActionBetween(Integer value1, Integer value2) {
			addCriterion("aj.action between", value1, value2, "action");
			return (Criteria) this;
		}

		public Criteria andActionNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.action not between", value1, value2, "action");
			return (Criteria) this;
		}

		public Criteria andIdentidadIsNull() {
			addCriterion("aj.idEntidad is null");
			return (Criteria) this;
		}

		public Criteria andIdentidadIsNotNull() {
			addCriterion("aj.idEntidad is not null");
			return (Criteria) this;
		}

		public Criteria andIdentidadEqualTo(Integer value) {
			addCriterion("aj.idEntidad =", value, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadNotEqualTo(Integer value) {
			addCriterion("aj.idEntidad <>", value, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadGreaterThan(Integer value) {
			addCriterion("aj.idEntidad >", value, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.idEntidad >=", value, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadLessThan(Integer value) {
			addCriterion("aj.idEntidad <", value, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadLessThanOrEqualTo(Integer value) {
			addCriterion("aj.idEntidad <=", value, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadIn(List<Integer> values) {
			addCriterion("aj.idEntidad in", values, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadNotIn(List<Integer> values) {
			addCriterion("aj.idEntidad not in", values, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadBetween(Integer value1, Integer value2) {
			addCriterion("aj.idEntidad between", value1, value2, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdentidadNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.idEntidad not between", value1, value2, "identidad");
			return (Criteria) this;
		}

		public Criteria andIdluzIsNull() {
			addCriterion("aj.idLuz is null");
			return (Criteria) this;
		}

		public Criteria andIdluzIsNotNull() {
			addCriterion("aj.idLuz is not null");
			return (Criteria) this;
		}

		public Criteria andIdluzEqualTo(Integer value) {
			addCriterion("aj.idLuz =", value, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzNotEqualTo(Integer value) {
			addCriterion("aj.idLuz <>", value, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzGreaterThan(Integer value) {
			addCriterion("aj.idLuz >", value, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.idLuz >=", value, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzLessThan(Integer value) {
			addCriterion("aj.idLuz <", value, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzLessThanOrEqualTo(Integer value) {
			addCriterion("aj.idLuz <=", value, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzIn(List<Integer> values) {
			addCriterion("aj.idLuz in", values, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzNotIn(List<Integer> values) {
			addCriterion("aj.idLuz not in", values, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzBetween(Integer value1, Integer value2) {
			addCriterion("aj.idLuz between", value1, value2, "idluz");
			return (Criteria) this;
		}

		public Criteria andIdluzNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.idLuz not between", value1, value2, "idluz");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("aj.status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("aj.status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("aj.status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("aj.status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("aj.status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("aj.status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("aj.status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("aj.status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("aj.status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("aj.status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("aj.status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("aj.status not between", value1, value2, "status");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ASYNC_JOB
     *
     * @mbggenerated do_not_delete_during_merge Thu May 02 23:36:09 ART 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}