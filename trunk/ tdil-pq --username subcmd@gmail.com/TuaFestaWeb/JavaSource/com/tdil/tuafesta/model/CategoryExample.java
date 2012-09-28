package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public CategoryExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected CategoryExample(CategoryExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table CATEGORY
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
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
			addCriterion("cate.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("cate.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("cate.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("cate.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("cate.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("cate.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("cate.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("cate.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("cate.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("cate.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("cate.name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("cate.name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("cate.name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("cate.name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("cate.name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("cate.name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("cate.name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("cate.name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("cate.name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("cate.name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("cate.name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("cate.name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("cate.name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("cate.name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("cate.description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("cate.description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("cate.description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("cate.description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("cate.description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("cate.description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("cate.description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("cate.description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("cate.description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("cate.description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("cate.description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("cate.description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("cate.description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("cate.description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNull() {
			addCriterion("cate.parent_id is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("cate.parent_id is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(Integer value) {
			addCriterion("cate.parent_id =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(Integer value) {
			addCriterion("cate.parent_id <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(Integer value) {
			addCriterion("cate.parent_id >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.parent_id >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(Integer value) {
			addCriterion("cate.parent_id <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(Integer value) {
			addCriterion("cate.parent_id <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<Integer> values) {
			addCriterion("cate.parent_id in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<Integer> values) {
			addCriterion("cate.parent_id not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(Integer value1, Integer value2) {
			addCriterion("cate.parent_id between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.parent_id not between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("cate.type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("cate.type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("cate.type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("cate.type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("cate.type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("cate.type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("cate.type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("cate.type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("cate.type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("cate.type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andShowinhomeIsNull() {
			addCriterion("cate.showInHome is null");
			return (Criteria) this;
		}

		public Criteria andShowinhomeIsNotNull() {
			addCriterion("cate.showInHome is not null");
			return (Criteria) this;
		}

		public Criteria andShowinhomeEqualTo(Integer value) {
			addCriterion("cate.showInHome =", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeNotEqualTo(Integer value) {
			addCriterion("cate.showInHome <>", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeGreaterThan(Integer value) {
			addCriterion("cate.showInHome >", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.showInHome >=", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeLessThan(Integer value) {
			addCriterion("cate.showInHome <", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeLessThanOrEqualTo(Integer value) {
			addCriterion("cate.showInHome <=", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeIn(List<Integer> values) {
			addCriterion("cate.showInHome in", values, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeNotIn(List<Integer> values) {
			addCriterion("cate.showInHome not in", values, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeBetween(Integer value1, Integer value2) {
			addCriterion("cate.showInHome between", value1, value2, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.showInHome not between", value1, value2, "showinhome");
			return (Criteria) this;
		}

		public Criteria andHomeindexIsNull() {
			addCriterion("cate.homeIndex is null");
			return (Criteria) this;
		}

		public Criteria andHomeindexIsNotNull() {
			addCriterion("cate.homeIndex is not null");
			return (Criteria) this;
		}

		public Criteria andHomeindexEqualTo(Integer value) {
			addCriterion("cate.homeIndex =", value, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexNotEqualTo(Integer value) {
			addCriterion("cate.homeIndex <>", value, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexGreaterThan(Integer value) {
			addCriterion("cate.homeIndex >", value, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.homeIndex >=", value, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexLessThan(Integer value) {
			addCriterion("cate.homeIndex <", value, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexLessThanOrEqualTo(Integer value) {
			addCriterion("cate.homeIndex <=", value, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexIn(List<Integer> values) {
			addCriterion("cate.homeIndex in", values, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexNotIn(List<Integer> values) {
			addCriterion("cate.homeIndex not in", values, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexBetween(Integer value1, Integer value2) {
			addCriterion("cate.homeIndex between", value1, value2, "homeindex");
			return (Criteria) this;
		}

		public Criteria andHomeindexNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.homeIndex not between", value1, value2, "homeindex");
			return (Criteria) this;
		}

		public Criteria andIsotherIsNull() {
			addCriterion("cate.isother is null");
			return (Criteria) this;
		}

		public Criteria andIsotherIsNotNull() {
			addCriterion("cate.isother is not null");
			return (Criteria) this;
		}

		public Criteria andIsotherEqualTo(Integer value) {
			addCriterion("cate.isother =", value, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherNotEqualTo(Integer value) {
			addCriterion("cate.isother <>", value, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherGreaterThan(Integer value) {
			addCriterion("cate.isother >", value, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.isother >=", value, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherLessThan(Integer value) {
			addCriterion("cate.isother <", value, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherLessThanOrEqualTo(Integer value) {
			addCriterion("cate.isother <=", value, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherIn(List<Integer> values) {
			addCriterion("cate.isother in", values, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherNotIn(List<Integer> values) {
			addCriterion("cate.isother not in", values, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherBetween(Integer value1, Integer value2) {
			addCriterion("cate.isother between", value1, value2, "isother");
			return (Criteria) this;
		}

		public Criteria andIsotherNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.isother not between", value1, value2, "isother");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("cate.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("cate.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("cate.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("cate.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("cate.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("cate.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("cate.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("cate.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("cate.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("cate.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("cate.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("cate.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CATEGORY
     *
     * @mbggenerated do_not_delete_during_merge Sat Sep 22 16:49:03 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}