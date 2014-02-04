package com.tdil.ljpeugeot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteUserExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public WebsiteUserExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected WebsiteUserExample(WebsiteUserExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
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
			addCriterion("su.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("su.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("su.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("su.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("su.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("su.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("su.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("su.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("su.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("su.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("su.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("su.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andFirstnameIsNull() {
			addCriterion("su.firstName is null");
			return (Criteria) this;
		}

		public Criteria andFirstnameIsNotNull() {
			addCriterion("su.firstName is not null");
			return (Criteria) this;
		}

		public Criteria andFirstnameEqualTo(String value) {
			addCriterion("su.firstName =", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotEqualTo(String value) {
			addCriterion("su.firstName <>", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameGreaterThan(String value) {
			addCriterion("su.firstName >", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameGreaterThanOrEqualTo(String value) {
			addCriterion("su.firstName >=", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameLessThan(String value) {
			addCriterion("su.firstName <", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameLessThanOrEqualTo(String value) {
			addCriterion("su.firstName <=", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameLike(String value) {
			addCriterion("su.firstName like", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotLike(String value) {
			addCriterion("su.firstName not like", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameIn(List<String> values) {
			addCriterion("su.firstName in", values, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotIn(List<String> values) {
			addCriterion("su.firstName not in", values, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameBetween(String value1, String value2) {
			addCriterion("su.firstName between", value1, value2, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotBetween(String value1, String value2) {
			addCriterion("su.firstName not between", value1, value2, "firstname");
			return (Criteria) this;
		}

		public Criteria andLastnameIsNull() {
			addCriterion("su.lastName is null");
			return (Criteria) this;
		}

		public Criteria andLastnameIsNotNull() {
			addCriterion("su.lastName is not null");
			return (Criteria) this;
		}

		public Criteria andLastnameEqualTo(String value) {
			addCriterion("su.lastName =", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotEqualTo(String value) {
			addCriterion("su.lastName <>", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameGreaterThan(String value) {
			addCriterion("su.lastName >", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameGreaterThanOrEqualTo(String value) {
			addCriterion("su.lastName >=", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameLessThan(String value) {
			addCriterion("su.lastName <", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameLessThanOrEqualTo(String value) {
			addCriterion("su.lastName <=", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameLike(String value) {
			addCriterion("su.lastName like", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotLike(String value) {
			addCriterion("su.lastName not like", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameIn(List<String> values) {
			addCriterion("su.lastName in", values, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotIn(List<String> values) {
			addCriterion("su.lastName not in", values, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameBetween(String value1, String value2) {
			addCriterion("su.lastName between", value1, value2, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotBetween(String value1, String value2) {
			addCriterion("su.lastName not between", value1, value2, "lastname");
			return (Criteria) this;
		}

		public Criteria andLojackuseridIsNull() {
			addCriterion("su.lojackUserId is null");
			return (Criteria) this;
		}

		public Criteria andLojackuseridIsNotNull() {
			addCriterion("su.lojackUserId is not null");
			return (Criteria) this;
		}

		public Criteria andLojackuseridEqualTo(String value) {
			addCriterion("su.lojackUserId =", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridNotEqualTo(String value) {
			addCriterion("su.lojackUserId <>", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridGreaterThan(String value) {
			addCriterion("su.lojackUserId >", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridGreaterThanOrEqualTo(String value) {
			addCriterion("su.lojackUserId >=", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridLessThan(String value) {
			addCriterion("su.lojackUserId <", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridLessThanOrEqualTo(String value) {
			addCriterion("su.lojackUserId <=", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridLike(String value) {
			addCriterion("su.lojackUserId like", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridNotLike(String value) {
			addCriterion("su.lojackUserId not like", value, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridIn(List<String> values) {
			addCriterion("su.lojackUserId in", values, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridNotIn(List<String> values) {
			addCriterion("su.lojackUserId not in", values, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridBetween(String value1, String value2) {
			addCriterion("su.lojackUserId between", value1, value2, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andLojackuseridNotBetween(String value1, String value2) {
			addCriterion("su.lojackUserId not between", value1, value2, "lojackuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridIsNull() {
			addCriterion("su.peugeotUserId is null");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridIsNotNull() {
			addCriterion("su.peugeotUserId is not null");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridEqualTo(String value) {
			addCriterion("su.peugeotUserId =", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridNotEqualTo(String value) {
			addCriterion("su.peugeotUserId <>", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridGreaterThan(String value) {
			addCriterion("su.peugeotUserId >", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridGreaterThanOrEqualTo(String value) {
			addCriterion("su.peugeotUserId >=", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridLessThan(String value) {
			addCriterion("su.peugeotUserId <", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridLessThanOrEqualTo(String value) {
			addCriterion("su.peugeotUserId <=", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridLike(String value) {
			addCriterion("su.peugeotUserId like", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridNotLike(String value) {
			addCriterion("su.peugeotUserId not like", value, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridIn(List<String> values) {
			addCriterion("su.peugeotUserId in", values, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridNotIn(List<String> values) {
			addCriterion("su.peugeotUserId not in", values, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridBetween(String value1, String value2) {
			addCriterion("su.peugeotUserId between", value1, value2, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andPeugeotuseridNotBetween(String value1, String value2) {
			addCriterion("su.peugeotUserId not between", value1, value2, "peugeotuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridIsNull() {
			addCriterion("su.homeUserId is null");
			return (Criteria) this;
		}

		public Criteria andHomeuseridIsNotNull() {
			addCriterion("su.homeUserId is not null");
			return (Criteria) this;
		}

		public Criteria andHomeuseridEqualTo(String value) {
			addCriterion("su.homeUserId =", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridNotEqualTo(String value) {
			addCriterion("su.homeUserId <>", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridGreaterThan(String value) {
			addCriterion("su.homeUserId >", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridGreaterThanOrEqualTo(String value) {
			addCriterion("su.homeUserId >=", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridLessThan(String value) {
			addCriterion("su.homeUserId <", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridLessThanOrEqualTo(String value) {
			addCriterion("su.homeUserId <=", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridLike(String value) {
			addCriterion("su.homeUserId like", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridNotLike(String value) {
			addCriterion("su.homeUserId not like", value, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridIn(List<String> values) {
			addCriterion("su.homeUserId in", values, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridNotIn(List<String> values) {
			addCriterion("su.homeUserId not in", values, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridBetween(String value1, String value2) {
			addCriterion("su.homeUserId between", value1, value2, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andHomeuseridNotBetween(String value1, String value2) {
			addCriterion("su.homeUserId not between", value1, value2, "homeuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridIsNull() {
			addCriterion("su.petUserId is null");
			return (Criteria) this;
		}

		public Criteria andPetuseridIsNotNull() {
			addCriterion("su.petUserId is not null");
			return (Criteria) this;
		}

		public Criteria andPetuseridEqualTo(String value) {
			addCriterion("su.petUserId =", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridNotEqualTo(String value) {
			addCriterion("su.petUserId <>", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridGreaterThan(String value) {
			addCriterion("su.petUserId >", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridGreaterThanOrEqualTo(String value) {
			addCriterion("su.petUserId >=", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridLessThan(String value) {
			addCriterion("su.petUserId <", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridLessThanOrEqualTo(String value) {
			addCriterion("su.petUserId <=", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridLike(String value) {
			addCriterion("su.petUserId like", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridNotLike(String value) {
			addCriterion("su.petUserId not like", value, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridIn(List<String> values) {
			addCriterion("su.petUserId in", values, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridNotIn(List<String> values) {
			addCriterion("su.petUserId not in", values, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridBetween(String value1, String value2) {
			addCriterion("su.petUserId between", value1, value2, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPetuseridNotBetween(String value1, String value2) {
			addCriterion("su.petUserId not between", value1, value2, "petuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridIsNull() {
			addCriterion("su.preventUserId is null");
			return (Criteria) this;
		}

		public Criteria andPreventuseridIsNotNull() {
			addCriterion("su.preventUserId is not null");
			return (Criteria) this;
		}

		public Criteria andPreventuseridEqualTo(String value) {
			addCriterion("su.preventUserId =", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridNotEqualTo(String value) {
			addCriterion("su.preventUserId <>", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridGreaterThan(String value) {
			addCriterion("su.preventUserId >", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridGreaterThanOrEqualTo(String value) {
			addCriterion("su.preventUserId >=", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridLessThan(String value) {
			addCriterion("su.preventUserId <", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridLessThanOrEqualTo(String value) {
			addCriterion("su.preventUserId <=", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridLike(String value) {
			addCriterion("su.preventUserId like", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridNotLike(String value) {
			addCriterion("su.preventUserId not like", value, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridIn(List<String> values) {
			addCriterion("su.preventUserId in", values, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridNotIn(List<String> values) {
			addCriterion("su.preventUserId not in", values, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridBetween(String value1, String value2) {
			addCriterion("su.preventUserId between", value1, value2, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andPreventuseridNotBetween(String value1, String value2) {
			addCriterion("su.preventUserId not between", value1, value2, "preventuserid");
			return (Criteria) this;
		}

		public Criteria andEmailIsNull() {
			addCriterion("su.email is null");
			return (Criteria) this;
		}

		public Criteria andEmailIsNotNull() {
			addCriterion("su.email is not null");
			return (Criteria) this;
		}

		public Criteria andEmailEqualTo(String value) {
			addCriterion("su.email =", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotEqualTo(String value) {
			addCriterion("su.email <>", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThan(String value) {
			addCriterion("su.email >", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThanOrEqualTo(String value) {
			addCriterion("su.email >=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThan(String value) {
			addCriterion("su.email <", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThanOrEqualTo(String value) {
			addCriterion("su.email <=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLike(String value) {
			addCriterion("su.email like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotLike(String value) {
			addCriterion("su.email not like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailIn(List<String> values) {
			addCriterion("su.email in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotIn(List<String> values) {
			addCriterion("su.email not in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailBetween(String value1, String value2) {
			addCriterion("su.email between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotBetween(String value1, String value2) {
			addCriterion("su.email not between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andIdAvatarIsNull() {
			addCriterion("su.id_avatar is null");
			return (Criteria) this;
		}

		public Criteria andIdAvatarIsNotNull() {
			addCriterion("su.id_avatar is not null");
			return (Criteria) this;
		}

		public Criteria andIdAvatarEqualTo(Integer value) {
			addCriterion("su.id_avatar =", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarNotEqualTo(Integer value) {
			addCriterion("su.id_avatar <>", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarGreaterThan(Integer value) {
			addCriterion("su.id_avatar >", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarGreaterThanOrEqualTo(Integer value) {
			addCriterion("su.id_avatar >=", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarLessThan(Integer value) {
			addCriterion("su.id_avatar <", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarLessThanOrEqualTo(Integer value) {
			addCriterion("su.id_avatar <=", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarIn(List<Integer> values) {
			addCriterion("su.id_avatar in", values, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarNotIn(List<Integer> values) {
			addCriterion("su.id_avatar not in", values, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarBetween(Integer value1, Integer value2) {
			addCriterion("su.id_avatar between", value1, value2, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarNotBetween(Integer value1, Integer value2) {
			addCriterion("su.id_avatar not between", value1, value2, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarIsNull() {
			addCriterion("su.ext_avatar is null");
			return (Criteria) this;
		}

		public Criteria andExtAvatarIsNotNull() {
			addCriterion("su.ext_avatar is not null");
			return (Criteria) this;
		}

		public Criteria andExtAvatarEqualTo(String value) {
			addCriterion("su.ext_avatar =", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotEqualTo(String value) {
			addCriterion("su.ext_avatar <>", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarGreaterThan(String value) {
			addCriterion("su.ext_avatar >", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarGreaterThanOrEqualTo(String value) {
			addCriterion("su.ext_avatar >=", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarLessThan(String value) {
			addCriterion("su.ext_avatar <", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarLessThanOrEqualTo(String value) {
			addCriterion("su.ext_avatar <=", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarLike(String value) {
			addCriterion("su.ext_avatar like", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotLike(String value) {
			addCriterion("su.ext_avatar not like", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarIn(List<String> values) {
			addCriterion("su.ext_avatar in", values, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotIn(List<String> values) {
			addCriterion("su.ext_avatar not in", values, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarBetween(String value1, String value2) {
			addCriterion("su.ext_avatar between", value1, value2, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotBetween(String value1, String value2) {
			addCriterion("su.ext_avatar not between", value1, value2, "extAvatar");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table WEBSITEUSER
     *
     * @mbggenerated do_not_delete_during_merge Thu Jan 30 23:53:15 ART 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}