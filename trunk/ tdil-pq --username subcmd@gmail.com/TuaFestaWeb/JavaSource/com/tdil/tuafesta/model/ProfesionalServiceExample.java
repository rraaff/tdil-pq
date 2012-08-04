package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfesionalServiceExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public ProfesionalServiceExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	protected ProfesionalServiceExample(ProfesionalServiceExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROF_SERVICE
	 * @mbggenerated  Fri Aug 03 18:15:40 ART 2012
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
			addCriterion("ps.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ps.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("ps.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("ps.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("ps.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("ps.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("ps.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("ps.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("ps.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("ps.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("ps.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("ps.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("ps.name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("ps.name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("ps.name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("ps.name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("ps.name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("ps.name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("ps.name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("ps.name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("ps.name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("ps.name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("ps.name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("ps.name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("ps.name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("ps.name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("ps.description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("ps.description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("ps.description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("ps.description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("ps.description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("ps.description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("ps.description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("ps.description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("ps.description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("ps.description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("ps.description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("ps.description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("ps.description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("ps.description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andAveragePriceIsNull() {
			addCriterion("ps.average_price is null");
			return (Criteria) this;
		}

		public Criteria andAveragePriceIsNotNull() {
			addCriterion("ps.average_price is not null");
			return (Criteria) this;
		}

		public Criteria andAveragePriceEqualTo(Integer value) {
			addCriterion("ps.average_price =", value, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceNotEqualTo(Integer value) {
			addCriterion("ps.average_price <>", value, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceGreaterThan(Integer value) {
			addCriterion("ps.average_price >", value, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceGreaterThanOrEqualTo(Integer value) {
			addCriterion("ps.average_price >=", value, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceLessThan(Integer value) {
			addCriterion("ps.average_price <", value, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceLessThanOrEqualTo(Integer value) {
			addCriterion("ps.average_price <=", value, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceIn(List<Integer> values) {
			addCriterion("ps.average_price in", values, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceNotIn(List<Integer> values) {
			addCriterion("ps.average_price not in", values, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceBetween(Integer value1, Integer value2) {
			addCriterion("ps.average_price between", value1, value2, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andAveragePriceNotBetween(Integer value1, Integer value2) {
			addCriterion("ps.average_price not between", value1, value2, "averagePrice");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("ps.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("ps.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("ps.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("ps.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("ps.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("ps.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("ps.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("ps.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("ps.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("ps.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("ps.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("ps.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("ps.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("ps.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("ps.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("ps.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("ps.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("ps.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("ps.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("ps.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("ps.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("ps.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("ps.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("ps.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROF_SERVICE
     *
     * @mbggenerated do_not_delete_during_merge Sun Jun 24 19:13:03 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}