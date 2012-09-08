package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighlightedCategoryExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public HighlightedCategoryExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected HighlightedCategoryExample(HighlightedCategoryExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Fri Sep 07 23:51:40 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table HIGHLIGHTED_CAT
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
			addCriterion("hc.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("hc.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("hc.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("hc.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("hc.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("hc.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("hc.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("hc.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("hc.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("hc.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("hc.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("hc.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("hc.type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("hc.type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("hc.type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("hc.type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("hc.type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("hc.type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("hc.type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("hc.type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("hc.type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("hc.type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("hc.type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("hc.type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatIsNull() {
			addCriterion("hc.id_prod_serv_cat is null");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatIsNotNull() {
			addCriterion("hc.id_prod_serv_cat is not null");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatEqualTo(Integer value) {
			addCriterion("hc.id_prod_serv_cat =", value, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatNotEqualTo(Integer value) {
			addCriterion("hc.id_prod_serv_cat <>", value, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatGreaterThan(Integer value) {
			addCriterion("hc.id_prod_serv_cat >", value, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatGreaterThanOrEqualTo(Integer value) {
			addCriterion("hc.id_prod_serv_cat >=", value, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatLessThan(Integer value) {
			addCriterion("hc.id_prod_serv_cat <", value, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatLessThanOrEqualTo(Integer value) {
			addCriterion("hc.id_prod_serv_cat <=", value, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatIn(List<Integer> values) {
			addCriterion("hc.id_prod_serv_cat in", values, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatNotIn(List<Integer> values) {
			addCriterion("hc.id_prod_serv_cat not in", values, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatBetween(Integer value1, Integer value2) {
			addCriterion("hc.id_prod_serv_cat between", value1, value2, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andIdProdServCatNotBetween(Integer value1, Integer value2) {
			addCriterion("hc.id_prod_serv_cat not between", value1, value2, "idProdServCat");
			return (Criteria) this;
		}

		public Criteria andFromdateIsNull() {
			addCriterion("hc.fromDate is null");
			return (Criteria) this;
		}

		public Criteria andFromdateIsNotNull() {
			addCriterion("hc.fromDate is not null");
			return (Criteria) this;
		}

		public Criteria andFromdateEqualTo(Date value) {
			addCriterion("hc.fromDate =", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateNotEqualTo(Date value) {
			addCriterion("hc.fromDate <>", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateGreaterThan(Date value) {
			addCriterion("hc.fromDate >", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateGreaterThanOrEqualTo(Date value) {
			addCriterion("hc.fromDate >=", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateLessThan(Date value) {
			addCriterion("hc.fromDate <", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateLessThanOrEqualTo(Date value) {
			addCriterion("hc.fromDate <=", value, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateIn(List<Date> values) {
			addCriterion("hc.fromDate in", values, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateNotIn(List<Date> values) {
			addCriterion("hc.fromDate not in", values, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateBetween(Date value1, Date value2) {
			addCriterion("hc.fromDate between", value1, value2, "fromdate");
			return (Criteria) this;
		}

		public Criteria andFromdateNotBetween(Date value1, Date value2) {
			addCriterion("hc.fromDate not between", value1, value2, "fromdate");
			return (Criteria) this;
		}

		public Criteria andTodateIsNull() {
			addCriterion("hc.toDate is null");
			return (Criteria) this;
		}

		public Criteria andTodateIsNotNull() {
			addCriterion("hc.toDate is not null");
			return (Criteria) this;
		}

		public Criteria andTodateEqualTo(Date value) {
			addCriterion("hc.toDate =", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateNotEqualTo(Date value) {
			addCriterion("hc.toDate <>", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateGreaterThan(Date value) {
			addCriterion("hc.toDate >", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateGreaterThanOrEqualTo(Date value) {
			addCriterion("hc.toDate >=", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateLessThan(Date value) {
			addCriterion("hc.toDate <", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateLessThanOrEqualTo(Date value) {
			addCriterion("hc.toDate <=", value, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateIn(List<Date> values) {
			addCriterion("hc.toDate in", values, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateNotIn(List<Date> values) {
			addCriterion("hc.toDate not in", values, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateBetween(Date value1, Date value2) {
			addCriterion("hc.toDate between", value1, value2, "todate");
			return (Criteria) this;
		}

		public Criteria andTodateNotBetween(Date value1, Date value2) {
			addCriterion("hc.toDate not between", value1, value2, "todate");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("hc.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("hc.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("hc.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("hc.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("hc.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("hc.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("hc.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("hc.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("hc.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("hc.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("hc.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("hc.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table HIGHLIGHTED_CAT
     *
     * @mbggenerated do_not_delete_during_merge Sun Aug 26 20:37:47 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}