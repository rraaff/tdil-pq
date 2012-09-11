package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WallExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public WallExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected WallExample(WallExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WALL
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
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
			addCriterion("wa.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("wa.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("wa.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("wa.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("wa.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("wa.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("wa.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("wa.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("wa.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("wa.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("wa.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("wa.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("wa.description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("wa.description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("wa.description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("wa.description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("wa.description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("wa.description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("wa.description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("wa.description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("wa.description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("wa.description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("wa.description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("wa.description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("wa.description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("wa.description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andModeratedIsNull() {
			addCriterion("wa.moderated is null");
			return (Criteria) this;
		}

		public Criteria andModeratedIsNotNull() {
			addCriterion("wa.moderated is not null");
			return (Criteria) this;
		}

		public Criteria andModeratedEqualTo(Integer value) {
			addCriterion("wa.moderated =", value, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedNotEqualTo(Integer value) {
			addCriterion("wa.moderated <>", value, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedGreaterThan(Integer value) {
			addCriterion("wa.moderated >", value, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedGreaterThanOrEqualTo(Integer value) {
			addCriterion("wa.moderated >=", value, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedLessThan(Integer value) {
			addCriterion("wa.moderated <", value, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedLessThanOrEqualTo(Integer value) {
			addCriterion("wa.moderated <=", value, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedIn(List<Integer> values) {
			addCriterion("wa.moderated in", values, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedNotIn(List<Integer> values) {
			addCriterion("wa.moderated not in", values, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedBetween(Integer value1, Integer value2) {
			addCriterion("wa.moderated between", value1, value2, "moderated");
			return (Criteria) this;
		}

		public Criteria andModeratedNotBetween(Integer value1, Integer value2) {
			addCriterion("wa.moderated not between", value1, value2, "moderated");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterIsNull() {
			addCriterion("wa.profanityFilter is null");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterIsNotNull() {
			addCriterion("wa.profanityFilter is not null");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterEqualTo(Integer value) {
			addCriterion("wa.profanityFilter =", value, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterNotEqualTo(Integer value) {
			addCriterion("wa.profanityFilter <>", value, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterGreaterThan(Integer value) {
			addCriterion("wa.profanityFilter >", value, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterGreaterThanOrEqualTo(Integer value) {
			addCriterion("wa.profanityFilter >=", value, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterLessThan(Integer value) {
			addCriterion("wa.profanityFilter <", value, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterLessThanOrEqualTo(Integer value) {
			addCriterion("wa.profanityFilter <=", value, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterIn(List<Integer> values) {
			addCriterion("wa.profanityFilter in", values, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterNotIn(List<Integer> values) {
			addCriterion("wa.profanityFilter not in", values, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterBetween(Integer value1, Integer value2) {
			addCriterion("wa.profanityFilter between", value1, value2, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andProfanityfilterNotBetween(Integer value1, Integer value2) {
			addCriterion("wa.profanityFilter not between", value1, value2, "profanityfilter");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("wa.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("wa.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("wa.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("wa.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("wa.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("wa.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("wa.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("wa.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("wa.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("wa.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("wa.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("wa.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table WALL
     *
     * @mbggenerated do_not_delete_during_merge Mon Jun 18 23:18:25 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}