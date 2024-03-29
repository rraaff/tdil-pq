package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Iterator;

public class ClientExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public ClientExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected ClientExample(ClientExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table CLIENT
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

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("cli.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("cli.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("cli.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("cli.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("cli.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cli.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("cli.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("cli.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("cli.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("cli.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("cli.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cli.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andFirstnameIsNull() {
			addCriterion("cli.firstname is null");
			return (Criteria) this;
		}

		public Criteria andFirstnameIsNotNull() {
			addCriterion("cli.firstname is not null");
			return (Criteria) this;
		}

		public Criteria andFirstnameEqualTo(String value) {
			addCriterion("cli.firstname =", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotEqualTo(String value) {
			addCriterion("cli.firstname <>", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameGreaterThan(String value) {
			addCriterion("cli.firstname >", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameGreaterThanOrEqualTo(String value) {
			addCriterion("cli.firstname >=", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameLessThan(String value) {
			addCriterion("cli.firstname <", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameLessThanOrEqualTo(String value) {
			addCriterion("cli.firstname <=", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameLike(String value) {
			addCriterion("cli.firstname like", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotLike(String value) {
			addCriterion("cli.firstname not like", value, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameIn(List<String> values) {
			addCriterion("cli.firstname in", values, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotIn(List<String> values) {
			addCriterion("cli.firstname not in", values, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameBetween(String value1, String value2) {
			addCriterion("cli.firstname between", value1, value2, "firstname");
			return (Criteria) this;
		}

		public Criteria andFirstnameNotBetween(String value1, String value2) {
			addCriterion("cli.firstname not between", value1, value2, "firstname");
			return (Criteria) this;
		}

		public Criteria andLastnameIsNull() {
			addCriterion("cli.lastname is null");
			return (Criteria) this;
		}

		public Criteria andLastnameIsNotNull() {
			addCriterion("cli.lastname is not null");
			return (Criteria) this;
		}

		public Criteria andLastnameEqualTo(String value) {
			addCriterion("cli.lastname =", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotEqualTo(String value) {
			addCriterion("cli.lastname <>", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameGreaterThan(String value) {
			addCriterion("cli.lastname >", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameGreaterThanOrEqualTo(String value) {
			addCriterion("cli.lastname >=", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameLessThan(String value) {
			addCriterion("cli.lastname <", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameLessThanOrEqualTo(String value) {
			addCriterion("cli.lastname <=", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameLike(String value) {
			addCriterion("cli.lastname like", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotLike(String value) {
			addCriterion("cli.lastname not like", value, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameIn(List<String> values) {
			addCriterion("cli.lastname in", values, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotIn(List<String> values) {
			addCriterion("cli.lastname not in", values, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameBetween(String value1, String value2) {
			addCriterion("cli.lastname between", value1, value2, "lastname");
			return (Criteria) this;
		}

		public Criteria andLastnameNotBetween(String value1, String value2) {
			addCriterion("cli.lastname not between", value1, value2, "lastname");
			return (Criteria) this;
		}

		public Criteria andSexIsNull() {
			addCriterion("cli.sex is null");
			return (Criteria) this;
		}

		public Criteria andSexIsNotNull() {
			addCriterion("cli.sex is not null");
			return (Criteria) this;
		}

		public Criteria andSexEqualTo(String value) {
			addCriterion("cli.sex =", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotEqualTo(String value) {
			addCriterion("cli.sex <>", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThan(String value) {
			addCriterion("cli.sex >", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexGreaterThanOrEqualTo(String value) {
			addCriterion("cli.sex >=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThan(String value) {
			addCriterion("cli.sex <", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLessThanOrEqualTo(String value) {
			addCriterion("cli.sex <=", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexLike(String value) {
			addCriterion("cli.sex like", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotLike(String value) {
			addCriterion("cli.sex not like", value, "sex");
			return (Criteria) this;
		}

		public Criteria andSexIn(List<String> values) {
			addCriterion("cli.sex in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotIn(List<String> values) {
			addCriterion("cli.sex not in", values, "sex");
			return (Criteria) this;
		}

		public Criteria andSexBetween(String value1, String value2) {
			addCriterion("cli.sex between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andSexNotBetween(String value1, String value2) {
			addCriterion("cli.sex not between", value1, value2, "sex");
			return (Criteria) this;
		}

		public Criteria andBirthdateIsNull() {
			addCriterion("cli.birthdate is null");
			return (Criteria) this;
		}

		public Criteria andBirthdateIsNotNull() {
			addCriterion("cli.birthdate is not null");
			return (Criteria) this;
		}

		public Criteria andBirthdateEqualTo(Date value) {
			addCriterionForJDBCDate("cli.birthdate =", value, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateNotEqualTo(Date value) {
			addCriterionForJDBCDate("cli.birthdate <>", value, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateGreaterThan(Date value) {
			addCriterionForJDBCDate("cli.birthdate >", value, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("cli.birthdate >=", value, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateLessThan(Date value) {
			addCriterionForJDBCDate("cli.birthdate <", value, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("cli.birthdate <=", value, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateIn(List<Date> values) {
			addCriterionForJDBCDate("cli.birthdate in", values, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateNotIn(List<Date> values) {
			addCriterionForJDBCDate("cli.birthdate not in", values, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("cli.birthdate between", value1, value2, "birthdate");
			return (Criteria) this;
		}

		public Criteria andBirthdateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("cli.birthdate not between", value1, value2, "birthdate");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelIsNull() {
			addCriterion("cli.id_geolevel is null");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelIsNotNull() {
			addCriterion("cli.id_geolevel is not null");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelEqualTo(Integer value) {
			addCriterion("cli.id_geolevel =", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelNotEqualTo(Integer value) {
			addCriterion("cli.id_geolevel <>", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelGreaterThan(Integer value) {
			addCriterion("cli.id_geolevel >", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("cli.id_geolevel >=", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelLessThan(Integer value) {
			addCriterion("cli.id_geolevel <", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelLessThanOrEqualTo(Integer value) {
			addCriterion("cli.id_geolevel <=", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelIn(List<Integer> values) {
			addCriterion("cli.id_geolevel in", values, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelNotIn(List<Integer> values) {
			addCriterion("cli.id_geolevel not in", values, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelBetween(Integer value1, Integer value2) {
			addCriterion("cli.id_geolevel between", value1, value2, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelNotBetween(Integer value1, Integer value2) {
			addCriterion("cli.id_geolevel not between", value1, value2, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andEmailIsNull() {
			addCriterion("cli.email is null");
			return (Criteria) this;
		}

		public Criteria andEmailIsNotNull() {
			addCriterion("cli.email is not null");
			return (Criteria) this;
		}

		public Criteria andEmailEqualTo(String value) {
			addCriterion("cli.email =", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotEqualTo(String value) {
			addCriterion("cli.email <>", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThan(String value) {
			addCriterion("cli.email >", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailGreaterThanOrEqualTo(String value) {
			addCriterion("cli.email >=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThan(String value) {
			addCriterion("cli.email <", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLessThanOrEqualTo(String value) {
			addCriterion("cli.email <=", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailLike(String value) {
			addCriterion("cli.email like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotLike(String value) {
			addCriterion("cli.email not like", value, "email");
			return (Criteria) this;
		}

		public Criteria andEmailIn(List<String> values) {
			addCriterion("cli.email in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotIn(List<String> values) {
			addCriterion("cli.email not in", values, "email");
			return (Criteria) this;
		}

		public Criteria andEmailBetween(String value1, String value2) {
			addCriterion("cli.email between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andEmailNotBetween(String value1, String value2) {
			addCriterion("cli.email not between", value1, value2, "email");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNull() {
			addCriterion("cli.password is null");
			return (Criteria) this;
		}

		public Criteria andPasswordIsNotNull() {
			addCriterion("cli.password is not null");
			return (Criteria) this;
		}

		public Criteria andPasswordEqualTo(String value) {
			addCriterion("cli.password =", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("cli.password <>", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("cli.password >", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("cli.password >=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThan(String value) {
			addCriterion("cli.password <", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("cli.password <=", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordLike(String value) {
			addCriterion("cli.password like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotLike(String value) {
			addCriterion("cli.password not like", value, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordIn(List<String> values) {
			addCriterion("cli.password in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotIn(List<String> values) {
			addCriterion("cli.password not in", values, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("cli.password between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("cli.password not between", value1, value2, "password");
			return (Criteria) this;
		}

		public Criteria andFacebookidIsNull() {
			addCriterion("cli.facebookid is null");
			return (Criteria) this;
		}

		public Criteria andFacebookidIsNotNull() {
			addCriterion("cli.facebookid is not null");
			return (Criteria) this;
		}

		public Criteria andFacebookidEqualTo(String value) {
			addCriterion("cli.facebookid =", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidNotEqualTo(String value) {
			addCriterion("cli.facebookid <>", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidGreaterThan(String value) {
			addCriterion("cli.facebookid >", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidGreaterThanOrEqualTo(String value) {
			addCriterion("cli.facebookid >=", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidLessThan(String value) {
			addCriterion("cli.facebookid <", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidLessThanOrEqualTo(String value) {
			addCriterion("cli.facebookid <=", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidLike(String value) {
			addCriterion("cli.facebookid like", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidNotLike(String value) {
			addCriterion("cli.facebookid not like", value, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidIn(List<String> values) {
			addCriterion("cli.facebookid in", values, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidNotIn(List<String> values) {
			addCriterion("cli.facebookid not in", values, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidBetween(String value1, String value2) {
			addCriterion("cli.facebookid between", value1, value2, "facebookid");
			return (Criteria) this;
		}

		public Criteria andFacebookidNotBetween(String value1, String value2) {
			addCriterion("cli.facebookid not between", value1, value2, "facebookid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidIsNull() {
			addCriterion("cli.emailvalid is null");
			return (Criteria) this;
		}

		public Criteria andEmailvalidIsNotNull() {
			addCriterion("cli.emailvalid is not null");
			return (Criteria) this;
		}

		public Criteria andEmailvalidEqualTo(Integer value) {
			addCriterion("cli.emailvalid =", value, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidNotEqualTo(Integer value) {
			addCriterion("cli.emailvalid <>", value, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidGreaterThan(Integer value) {
			addCriterion("cli.emailvalid >", value, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidGreaterThanOrEqualTo(Integer value) {
			addCriterion("cli.emailvalid >=", value, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidLessThan(Integer value) {
			addCriterion("cli.emailvalid <", value, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidLessThanOrEqualTo(Integer value) {
			addCriterion("cli.emailvalid <=", value, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidIn(List<Integer> values) {
			addCriterion("cli.emailvalid in", values, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidNotIn(List<Integer> values) {
			addCriterion("cli.emailvalid not in", values, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidBetween(Integer value1, Integer value2) {
			addCriterion("cli.emailvalid between", value1, value2, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andEmailvalidNotBetween(Integer value1, Integer value2) {
			addCriterion("cli.emailvalid not between", value1, value2, "emailvalid");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("cli.status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("cli.status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("cli.status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("cli.status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("cli.status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("cli.status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("cli.status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("cli.status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("cli.status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("cli.status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("cli.status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("cli.status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andVerifemailIsNull() {
			addCriterion("cli.verifemail is null");
			return (Criteria) this;
		}

		public Criteria andVerifemailIsNotNull() {
			addCriterion("cli.verifemail is not null");
			return (Criteria) this;
		}

		public Criteria andVerifemailEqualTo(String value) {
			addCriterion("cli.verifemail =", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailNotEqualTo(String value) {
			addCriterion("cli.verifemail <>", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailGreaterThan(String value) {
			addCriterion("cli.verifemail >", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailGreaterThanOrEqualTo(String value) {
			addCriterion("cli.verifemail >=", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailLessThan(String value) {
			addCriterion("cli.verifemail <", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailLessThanOrEqualTo(String value) {
			addCriterion("cli.verifemail <=", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailLike(String value) {
			addCriterion("cli.verifemail like", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailNotLike(String value) {
			addCriterion("cli.verifemail not like", value, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailIn(List<String> values) {
			addCriterion("cli.verifemail in", values, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailNotIn(List<String> values) {
			addCriterion("cli.verifemail not in", values, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailBetween(String value1, String value2) {
			addCriterion("cli.verifemail between", value1, value2, "verifemail");
			return (Criteria) this;
		}

		public Criteria andVerifemailNotBetween(String value1, String value2) {
			addCriterion("cli.verifemail not between", value1, value2, "verifemail");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("cli.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("cli.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("cli.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("cli.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("cli.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("cli.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("cli.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("cli.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("cli.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("cli.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("cli.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("cli.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CLIENT
     *
     * @mbggenerated do_not_delete_during_merge Fri Aug 17 19:00:12 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}