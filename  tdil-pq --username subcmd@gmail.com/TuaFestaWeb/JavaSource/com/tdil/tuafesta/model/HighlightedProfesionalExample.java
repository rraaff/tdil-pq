package com.tdil.tuafesta.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighlightedProfesionalExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public HighlightedProfesionalExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected HighlightedProfesionalExample(HighlightedProfesionalExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table HIGHLIGHTED_PROF
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
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
			addCriterion("hp.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("hp.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("hp.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("hp.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("hp.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("hp.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("hp.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("hp.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("hp.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("hp.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("hp.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("hp.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNull() {
			addCriterion("hp.id_profesional is null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNotNull() {
			addCriterion("hp.id_profesional is not null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalEqualTo(Integer value) {
			addCriterion("hp.id_profesional =", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotEqualTo(Integer value) {
			addCriterion("hp.id_profesional <>", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThan(Integer value) {
			addCriterion("hp.id_profesional >", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThanOrEqualTo(Integer value) {
			addCriterion("hp.id_profesional >=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThan(Integer value) {
			addCriterion("hp.id_profesional <", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThanOrEqualTo(Integer value) {
			addCriterion("hp.id_profesional <=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIn(List<Integer> values) {
			addCriterion("hp.id_profesional in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotIn(List<Integer> values) {
			addCriterion("hp.id_profesional not in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalBetween(Integer value1, Integer value2) {
			addCriterion("hp.id_profesional between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotBetween(Integer value1, Integer value2) {
			addCriterion("hp.id_profesional not between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andFromdateIsNull() {
			addCriterion("hp.fromDate is null");
			return (Criteria) this;
		}

		public Criteria andFromdateIsNotNull() {
			addCriterion("hp.fromDate is not null");
			return (Criteria) this;
		}

		public Criteria andFromdateEqualTo(Date value) {
			addCriterion("hp.fromDate =", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateNotEqualTo(Date value) {
			addCriterion("hp.fromDate <>", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateGreaterThan(Date value) {
			addCriterion("hp.fromDate >", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateGreaterThanOrEqualTo(Date value) {
			addCriterion("hp.fromDate >=", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateLessThan(Date value) {
			addCriterion("hp.fromDate <", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateLessThanOrEqualTo(Date value) {
			addCriterion("hp.fromDate <=", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateIn(List<Date> values) {
			addCriterion("hp.fromDate in", values, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateNotIn(List<Date> values) {
			addCriterion("hp.fromDate not in", values, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateBetween(Date value1, Date value2) {
			addCriterion("hp.fromDate between", value1, value2, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateNotBetween(Date value1, Date value2) {
			addCriterion("hp.fromDate not between", value1, value2, "fromdate");
			return (Criteria) this;
		}

		public Criteria andTodateIsNull() {
			addCriterion("hp.toDate is null");
			return (Criteria) this;
		}

		public Criteria andTodateIsNotNull() {
			addCriterion("hp.toDate is not null");
			return (Criteria) this;
		}

		public Criteria andTodateEqualTo(Date value) {
			addCriterion("hp.toDate =", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateNotEqualTo(Date value) {
			addCriterion("hp.toDate <>", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateGreaterThan(Date value) {
			addCriterion("hp.toDate >", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateGreaterThanOrEqualTo(Date value) {
			addCriterion("hp.toDate >=", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateLessThan(Date value) {
			addCriterion("hp.toDate <", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateLessThanOrEqualTo(Date value) {
			addCriterion("hp.toDate <=", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateIn(List<Date> values) {
			addCriterion("hp.toDate in", values, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateNotIn(List<Date> values) {
			addCriterion("hp.toDate not in", values, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateBetween(Date value1, Date value2) {
			addCriterion("hp.toDate between", value1, value2, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateNotBetween(Date value1, Date value2) {
			addCriterion("hp.toDate not between", value1, value2, "todate");
			return (Criteria) this;
		}

		public Criteria andPaymentIsNull() {
			addCriterion("hp.payment is null");
			return (Criteria) this;
		}

		public Criteria andPaymentIsNotNull() {
			addCriterion("hp.payment is not null");
			return (Criteria) this;
		}

		public Criteria andPaymentEqualTo(BigDecimal value) {
			addCriterion("hp.payment =", value, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentNotEqualTo(BigDecimal value) {
			addCriterion("hp.payment <>", value, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentGreaterThan(BigDecimal value) {
			addCriterion("hp.payment >", value, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("hp.payment >=", value, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentLessThan(BigDecimal value) {
			addCriterion("hp.payment <", value, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentLessThanOrEqualTo(BigDecimal value) {
			addCriterion("hp.payment <=", value, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentIn(List<BigDecimal> values) {
			addCriterion("hp.payment in", values, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentNotIn(List<BigDecimal> values) {
			addCriterion("hp.payment not in", values, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("hp.payment between", value1, value2, "payment");
			return (Criteria) this;
		}

		public Criteria andPaymentNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("hp.payment not between", value1, value2, "payment");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("hp.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("hp.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("hp.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("hp.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("hp.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("hp.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("hp.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("hp.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("hp.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("hp.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("hp.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("hp.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table HIGHLIGHTED_PROF
     *
     * @mbggenerated do_not_delete_during_merge Sun Aug 26 20:37:47 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}