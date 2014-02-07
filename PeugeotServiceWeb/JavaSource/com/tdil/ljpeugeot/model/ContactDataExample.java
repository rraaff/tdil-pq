package com.tdil.ljpeugeot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDataExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public ContactDataExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	protected ContactDataExample(ContactDataExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table CONTACTDATA
	 * @mbggenerated  Fri Feb 07 00:38:06 ART 2014
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
			addCriterion("cd.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("cd.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("cd.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("cd.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("cd.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cd.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("cd.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("cd.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("cd.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("cd.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("cd.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cd.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserIsNull() {
			addCriterion("cd.id_websiteuser is null");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserIsNotNull() {
			addCriterion("cd.id_websiteuser is not null");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserEqualTo(Integer value) {
			addCriterion("cd.id_websiteuser =", value, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserNotEqualTo(Integer value) {
			addCriterion("cd.id_websiteuser <>", value, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserGreaterThan(Integer value) {
			addCriterion("cd.id_websiteuser >", value, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserGreaterThanOrEqualTo(Integer value) {
			addCriterion("cd.id_websiteuser >=", value, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserLessThan(Integer value) {
			addCriterion("cd.id_websiteuser <", value, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserLessThanOrEqualTo(Integer value) {
			addCriterion("cd.id_websiteuser <=", value, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserIn(List<Integer> values) {
			addCriterion("cd.id_websiteuser in", values, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserNotIn(List<Integer> values) {
			addCriterion("cd.id_websiteuser not in", values, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserBetween(Integer value1, Integer value2) {
			addCriterion("cd.id_websiteuser between", value1, value2, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andIdWebsiteuserNotBetween(Integer value1, Integer value2) {
			addCriterion("cd.id_websiteuser not between", value1, value2, "idWebsiteuser");
			return (Criteria) this;
		}

		public Criteria andContact1nameIsNull() {
			addCriterion("cd.contact1Name is null");
			return (Criteria) this;
		}

		public Criteria andContact1nameIsNotNull() {
			addCriterion("cd.contact1Name is not null");
			return (Criteria) this;
		}

		public Criteria andContact1nameEqualTo(String value) {
			addCriterion("cd.contact1Name =", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameNotEqualTo(String value) {
			addCriterion("cd.contact1Name <>", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameGreaterThan(String value) {
			addCriterion("cd.contact1Name >", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact1Name >=", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameLessThan(String value) {
			addCriterion("cd.contact1Name <", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameLessThanOrEqualTo(String value) {
			addCriterion("cd.contact1Name <=", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameLike(String value) {
			addCriterion("cd.contact1Name like", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameNotLike(String value) {
			addCriterion("cd.contact1Name not like", value, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameIn(List<String> values) {
			addCriterion("cd.contact1Name in", values, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameNotIn(List<String> values) {
			addCriterion("cd.contact1Name not in", values, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameBetween(String value1, String value2) {
			addCriterion("cd.contact1Name between", value1, value2, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1nameNotBetween(String value1, String value2) {
			addCriterion("cd.contact1Name not between", value1, value2, "contact1name");
			return (Criteria) this;
		}

		public Criteria andContact1relationIsNull() {
			addCriterion("cd.contact1Relation is null");
			return (Criteria) this;
		}

		public Criteria andContact1relationIsNotNull() {
			addCriterion("cd.contact1Relation is not null");
			return (Criteria) this;
		}

		public Criteria andContact1relationEqualTo(String value) {
			addCriterion("cd.contact1Relation =", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationNotEqualTo(String value) {
			addCriterion("cd.contact1Relation <>", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationGreaterThan(String value) {
			addCriterion("cd.contact1Relation >", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact1Relation >=", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationLessThan(String value) {
			addCriterion("cd.contact1Relation <", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationLessThanOrEqualTo(String value) {
			addCriterion("cd.contact1Relation <=", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationLike(String value) {
			addCriterion("cd.contact1Relation like", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationNotLike(String value) {
			addCriterion("cd.contact1Relation not like", value, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationIn(List<String> values) {
			addCriterion("cd.contact1Relation in", values, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationNotIn(List<String> values) {
			addCriterion("cd.contact1Relation not in", values, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationBetween(String value1, String value2) {
			addCriterion("cd.contact1Relation between", value1, value2, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1relationNotBetween(String value1, String value2) {
			addCriterion("cd.contact1Relation not between", value1, value2, "contact1relation");
			return (Criteria) this;
		}

		public Criteria andContact1phoneIsNull() {
			addCriterion("cd.contact1Phone is null");
			return (Criteria) this;
		}

		public Criteria andContact1phoneIsNotNull() {
			addCriterion("cd.contact1Phone is not null");
			return (Criteria) this;
		}

		public Criteria andContact1phoneEqualTo(String value) {
			addCriterion("cd.contact1Phone =", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneNotEqualTo(String value) {
			addCriterion("cd.contact1Phone <>", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneGreaterThan(String value) {
			addCriterion("cd.contact1Phone >", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact1Phone >=", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneLessThan(String value) {
			addCriterion("cd.contact1Phone <", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneLessThanOrEqualTo(String value) {
			addCriterion("cd.contact1Phone <=", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneLike(String value) {
			addCriterion("cd.contact1Phone like", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneNotLike(String value) {
			addCriterion("cd.contact1Phone not like", value, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneIn(List<String> values) {
			addCriterion("cd.contact1Phone in", values, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneNotIn(List<String> values) {
			addCriterion("cd.contact1Phone not in", values, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneBetween(String value1, String value2) {
			addCriterion("cd.contact1Phone between", value1, value2, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1phoneNotBetween(String value1, String value2) {
			addCriterion("cd.contact1Phone not between", value1, value2, "contact1phone");
			return (Criteria) this;
		}

		public Criteria andContact1secwordIsNull() {
			addCriterion("cd.contact1SecWord is null");
			return (Criteria) this;
		}

		public Criteria andContact1secwordIsNotNull() {
			addCriterion("cd.contact1SecWord is not null");
			return (Criteria) this;
		}

		public Criteria andContact1secwordEqualTo(String value) {
			addCriterion("cd.contact1SecWord =", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordNotEqualTo(String value) {
			addCriterion("cd.contact1SecWord <>", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordGreaterThan(String value) {
			addCriterion("cd.contact1SecWord >", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact1SecWord >=", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordLessThan(String value) {
			addCriterion("cd.contact1SecWord <", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordLessThanOrEqualTo(String value) {
			addCriterion("cd.contact1SecWord <=", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordLike(String value) {
			addCriterion("cd.contact1SecWord like", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordNotLike(String value) {
			addCriterion("cd.contact1SecWord not like", value, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordIn(List<String> values) {
			addCriterion("cd.contact1SecWord in", values, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordNotIn(List<String> values) {
			addCriterion("cd.contact1SecWord not in", values, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordBetween(String value1, String value2) {
			addCriterion("cd.contact1SecWord between", value1, value2, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1secwordNotBetween(String value1, String value2) {
			addCriterion("cd.contact1SecWord not between", value1, value2, "contact1secword");
			return (Criteria) this;
		}

		public Criteria andContact1healthiIsNull() {
			addCriterion("cd.contact1HealthI is null");
			return (Criteria) this;
		}

		public Criteria andContact1healthiIsNotNull() {
			addCriterion("cd.contact1HealthI is not null");
			return (Criteria) this;
		}

		public Criteria andContact1healthiEqualTo(String value) {
			addCriterion("cd.contact1HealthI =", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiNotEqualTo(String value) {
			addCriterion("cd.contact1HealthI <>", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiGreaterThan(String value) {
			addCriterion("cd.contact1HealthI >", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact1HealthI >=", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiLessThan(String value) {
			addCriterion("cd.contact1HealthI <", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiLessThanOrEqualTo(String value) {
			addCriterion("cd.contact1HealthI <=", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiLike(String value) {
			addCriterion("cd.contact1HealthI like", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiNotLike(String value) {
			addCriterion("cd.contact1HealthI not like", value, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiIn(List<String> values) {
			addCriterion("cd.contact1HealthI in", values, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiNotIn(List<String> values) {
			addCriterion("cd.contact1HealthI not in", values, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiBetween(String value1, String value2) {
			addCriterion("cd.contact1HealthI between", value1, value2, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact1healthiNotBetween(String value1, String value2) {
			addCriterion("cd.contact1HealthI not between", value1, value2, "contact1healthi");
			return (Criteria) this;
		}

		public Criteria andContact2nameIsNull() {
			addCriterion("cd.contact2Name is null");
			return (Criteria) this;
		}

		public Criteria andContact2nameIsNotNull() {
			addCriterion("cd.contact2Name is not null");
			return (Criteria) this;
		}

		public Criteria andContact2nameEqualTo(String value) {
			addCriterion("cd.contact2Name =", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameNotEqualTo(String value) {
			addCriterion("cd.contact2Name <>", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameGreaterThan(String value) {
			addCriterion("cd.contact2Name >", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact2Name >=", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameLessThan(String value) {
			addCriterion("cd.contact2Name <", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameLessThanOrEqualTo(String value) {
			addCriterion("cd.contact2Name <=", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameLike(String value) {
			addCriterion("cd.contact2Name like", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameNotLike(String value) {
			addCriterion("cd.contact2Name not like", value, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameIn(List<String> values) {
			addCriterion("cd.contact2Name in", values, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameNotIn(List<String> values) {
			addCriterion("cd.contact2Name not in", values, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameBetween(String value1, String value2) {
			addCriterion("cd.contact2Name between", value1, value2, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2nameNotBetween(String value1, String value2) {
			addCriterion("cd.contact2Name not between", value1, value2, "contact2name");
			return (Criteria) this;
		}

		public Criteria andContact2relationIsNull() {
			addCriterion("cd.contact2Relation is null");
			return (Criteria) this;
		}

		public Criteria andContact2relationIsNotNull() {
			addCriterion("cd.contact2Relation is not null");
			return (Criteria) this;
		}

		public Criteria andContact2relationEqualTo(String value) {
			addCriterion("cd.contact2Relation =", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationNotEqualTo(String value) {
			addCriterion("cd.contact2Relation <>", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationGreaterThan(String value) {
			addCriterion("cd.contact2Relation >", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact2Relation >=", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationLessThan(String value) {
			addCriterion("cd.contact2Relation <", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationLessThanOrEqualTo(String value) {
			addCriterion("cd.contact2Relation <=", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationLike(String value) {
			addCriterion("cd.contact2Relation like", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationNotLike(String value) {
			addCriterion("cd.contact2Relation not like", value, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationIn(List<String> values) {
			addCriterion("cd.contact2Relation in", values, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationNotIn(List<String> values) {
			addCriterion("cd.contact2Relation not in", values, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationBetween(String value1, String value2) {
			addCriterion("cd.contact2Relation between", value1, value2, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2relationNotBetween(String value1, String value2) {
			addCriterion("cd.contact2Relation not between", value1, value2, "contact2relation");
			return (Criteria) this;
		}

		public Criteria andContact2phoneIsNull() {
			addCriterion("cd.contact2Phone is null");
			return (Criteria) this;
		}

		public Criteria andContact2phoneIsNotNull() {
			addCriterion("cd.contact2Phone is not null");
			return (Criteria) this;
		}

		public Criteria andContact2phoneEqualTo(String value) {
			addCriterion("cd.contact2Phone =", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneNotEqualTo(String value) {
			addCriterion("cd.contact2Phone <>", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneGreaterThan(String value) {
			addCriterion("cd.contact2Phone >", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact2Phone >=", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneLessThan(String value) {
			addCriterion("cd.contact2Phone <", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneLessThanOrEqualTo(String value) {
			addCriterion("cd.contact2Phone <=", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneLike(String value) {
			addCriterion("cd.contact2Phone like", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneNotLike(String value) {
			addCriterion("cd.contact2Phone not like", value, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneIn(List<String> values) {
			addCriterion("cd.contact2Phone in", values, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneNotIn(List<String> values) {
			addCriterion("cd.contact2Phone not in", values, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneBetween(String value1, String value2) {
			addCriterion("cd.contact2Phone between", value1, value2, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact2phoneNotBetween(String value1, String value2) {
			addCriterion("cd.contact2Phone not between", value1, value2, "contact2phone");
			return (Criteria) this;
		}

		public Criteria andContact3nameIsNull() {
			addCriterion("cd.contact3Name is null");
			return (Criteria) this;
		}

		public Criteria andContact3nameIsNotNull() {
			addCriterion("cd.contact3Name is not null");
			return (Criteria) this;
		}

		public Criteria andContact3nameEqualTo(String value) {
			addCriterion("cd.contact3Name =", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameNotEqualTo(String value) {
			addCriterion("cd.contact3Name <>", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameGreaterThan(String value) {
			addCriterion("cd.contact3Name >", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact3Name >=", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameLessThan(String value) {
			addCriterion("cd.contact3Name <", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameLessThanOrEqualTo(String value) {
			addCriterion("cd.contact3Name <=", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameLike(String value) {
			addCriterion("cd.contact3Name like", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameNotLike(String value) {
			addCriterion("cd.contact3Name not like", value, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameIn(List<String> values) {
			addCriterion("cd.contact3Name in", values, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameNotIn(List<String> values) {
			addCriterion("cd.contact3Name not in", values, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameBetween(String value1, String value2) {
			addCriterion("cd.contact3Name between", value1, value2, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3nameNotBetween(String value1, String value2) {
			addCriterion("cd.contact3Name not between", value1, value2, "contact3name");
			return (Criteria) this;
		}

		public Criteria andContact3relationIsNull() {
			addCriterion("cd.contact3Relation is null");
			return (Criteria) this;
		}

		public Criteria andContact3relationIsNotNull() {
			addCriterion("cd.contact3Relation is not null");
			return (Criteria) this;
		}

		public Criteria andContact3relationEqualTo(String value) {
			addCriterion("cd.contact3Relation =", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationNotEqualTo(String value) {
			addCriterion("cd.contact3Relation <>", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationGreaterThan(String value) {
			addCriterion("cd.contact3Relation >", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact3Relation >=", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationLessThan(String value) {
			addCriterion("cd.contact3Relation <", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationLessThanOrEqualTo(String value) {
			addCriterion("cd.contact3Relation <=", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationLike(String value) {
			addCriterion("cd.contact3Relation like", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationNotLike(String value) {
			addCriterion("cd.contact3Relation not like", value, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationIn(List<String> values) {
			addCriterion("cd.contact3Relation in", values, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationNotIn(List<String> values) {
			addCriterion("cd.contact3Relation not in", values, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationBetween(String value1, String value2) {
			addCriterion("cd.contact3Relation between", value1, value2, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3relationNotBetween(String value1, String value2) {
			addCriterion("cd.contact3Relation not between", value1, value2, "contact3relation");
			return (Criteria) this;
		}

		public Criteria andContact3phoneIsNull() {
			addCriterion("cd.contact3Phone is null");
			return (Criteria) this;
		}

		public Criteria andContact3phoneIsNotNull() {
			addCriterion("cd.contact3Phone is not null");
			return (Criteria) this;
		}

		public Criteria andContact3phoneEqualTo(String value) {
			addCriterion("cd.contact3Phone =", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneNotEqualTo(String value) {
			addCriterion("cd.contact3Phone <>", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneGreaterThan(String value) {
			addCriterion("cd.contact3Phone >", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneGreaterThanOrEqualTo(String value) {
			addCriterion("cd.contact3Phone >=", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneLessThan(String value) {
			addCriterion("cd.contact3Phone <", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneLessThanOrEqualTo(String value) {
			addCriterion("cd.contact3Phone <=", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneLike(String value) {
			addCriterion("cd.contact3Phone like", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneNotLike(String value) {
			addCriterion("cd.contact3Phone not like", value, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneIn(List<String> values) {
			addCriterion("cd.contact3Phone in", values, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneNotIn(List<String> values) {
			addCriterion("cd.contact3Phone not in", values, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneBetween(String value1, String value2) {
			addCriterion("cd.contact3Phone between", value1, value2, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andContact3phoneNotBetween(String value1, String value2) {
			addCriterion("cd.contact3Phone not between", value1, value2, "contact3phone");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("cd.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("cd.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("cd.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("cd.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("cd.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("cd.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("cd.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("cd.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("cd.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("cd.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("cd.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("cd.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CONTACTDATA
     *
     * @mbggenerated do_not_delete_during_merge Fri Jan 31 19:31:54 ART 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}