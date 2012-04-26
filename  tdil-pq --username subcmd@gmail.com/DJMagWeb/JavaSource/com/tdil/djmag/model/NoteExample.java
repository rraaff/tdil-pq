package com.tdil.djmag.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NoteExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public NoteExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected NoteExample(NoteExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NOTE
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
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
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andSummaryIsNull() {
			addCriterion("summary is null");
			return (Criteria) this;
		}

		public Criteria andSummaryIsNotNull() {
			addCriterion("summary is not null");
			return (Criteria) this;
		}

		public Criteria andSummaryEqualTo(String value) {
			addCriterion("summary =", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryNotEqualTo(String value) {
			addCriterion("summary <>", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryGreaterThan(String value) {
			addCriterion("summary >", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryGreaterThanOrEqualTo(String value) {
			addCriterion("summary >=", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryLessThan(String value) {
			addCriterion("summary <", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryLessThanOrEqualTo(String value) {
			addCriterion("summary <=", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryLike(String value) {
			addCriterion("summary like", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryNotLike(String value) {
			addCriterion("summary not like", value, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryIn(List<String> values) {
			addCriterion("summary in", values, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryNotIn(List<String> values) {
			addCriterion("summary not in", values, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryBetween(String value1, String value2) {
			addCriterion("summary between", value1, value2, "summary");
			return (Criteria) this;
		}

		public Criteria andSummaryNotBetween(String value1, String value2) {
			addCriterion("summary not between", value1, value2, "summary");
			return (Criteria) this;
		}

		public Criteria andIdSectionIsNull() {
			addCriterion("id_section is null");
			return (Criteria) this;
		}

		public Criteria andIdSectionIsNotNull() {
			addCriterion("id_section is not null");
			return (Criteria) this;
		}

		public Criteria andIdSectionEqualTo(Integer value) {
			addCriterion("id_section =", value, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionNotEqualTo(Integer value) {
			addCriterion("id_section <>", value, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionGreaterThan(Integer value) {
			addCriterion("id_section >", value, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_section >=", value, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionLessThan(Integer value) {
			addCriterion("id_section <", value, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionLessThanOrEqualTo(Integer value) {
			addCriterion("id_section <=", value, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionIn(List<Integer> values) {
			addCriterion("id_section in", values, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionNotIn(List<Integer> values) {
			addCriterion("id_section not in", values, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionBetween(Integer value1, Integer value2) {
			addCriterion("id_section between", value1, value2, "idSection");
			return (Criteria) this;
		}

		public Criteria andIdSectionNotBetween(Integer value1, Integer value2) {
			addCriterion("id_section not between", value1, value2, "idSection");
			return (Criteria) this;
		}

		public Criteria andWebTitleIsNull() {
			addCriterion("web_title is null");
			return (Criteria) this;
		}

		public Criteria andWebTitleIsNotNull() {
			addCriterion("web_title is not null");
			return (Criteria) this;
		}

		public Criteria andWebTitleEqualTo(String value) {
			addCriterion("web_title =", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleNotEqualTo(String value) {
			addCriterion("web_title <>", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleGreaterThan(String value) {
			addCriterion("web_title >", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleGreaterThanOrEqualTo(String value) {
			addCriterion("web_title >=", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleLessThan(String value) {
			addCriterion("web_title <", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleLessThanOrEqualTo(String value) {
			addCriterion("web_title <=", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleLike(String value) {
			addCriterion("web_title like", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleNotLike(String value) {
			addCriterion("web_title not like", value, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleIn(List<String> values) {
			addCriterion("web_title in", values, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleNotIn(List<String> values) {
			addCriterion("web_title not in", values, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleBetween(String value1, String value2) {
			addCriterion("web_title between", value1, value2, "webTitle");
			return (Criteria) this;
		}

		public Criteria andWebTitleNotBetween(String value1, String value2) {
			addCriterion("web_title not between", value1, value2, "webTitle");
			return (Criteria) this;
		}

		public Criteria andFromDateIsNull() {
			addCriterion("from_date is null");
			return (Criteria) this;
		}

		public Criteria andFromDateIsNotNull() {
			addCriterion("from_date is not null");
			return (Criteria) this;
		}

		public Criteria andFromDateEqualTo(Date value) {
			addCriterionForJDBCDate("from_date =", value, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateNotEqualTo(Date value) {
			addCriterionForJDBCDate("from_date <>", value, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateGreaterThan(Date value) {
			addCriterionForJDBCDate("from_date >", value, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("from_date >=", value, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateLessThan(Date value) {
			addCriterionForJDBCDate("from_date <", value, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("from_date <=", value, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateIn(List<Date> values) {
			addCriterionForJDBCDate("from_date in", values, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateNotIn(List<Date> values) {
			addCriterionForJDBCDate("from_date not in", values, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("from_date between", value1, value2, "fromDate");
			return (Criteria) this;
		}

		public Criteria andFromDateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("from_date not between", value1, value2, "fromDate");
			return (Criteria) this;
		}

		public Criteria andToDateIsNull() {
			addCriterion("to_date is null");
			return (Criteria) this;
		}

		public Criteria andToDateIsNotNull() {
			addCriterion("to_date is not null");
			return (Criteria) this;
		}

		public Criteria andToDateEqualTo(Date value) {
			addCriterionForJDBCDate("to_date =", value, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateNotEqualTo(Date value) {
			addCriterionForJDBCDate("to_date <>", value, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateGreaterThan(Date value) {
			addCriterionForJDBCDate("to_date >", value, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("to_date >=", value, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateLessThan(Date value) {
			addCriterionForJDBCDate("to_date <", value, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("to_date <=", value, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateIn(List<Date> values) {
			addCriterionForJDBCDate("to_date in", values, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateNotIn(List<Date> values) {
			addCriterionForJDBCDate("to_date not in", values, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("to_date between", value1, value2, "toDate");
			return (Criteria) this;
		}

		public Criteria andToDateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("to_date not between", value1, value2, "toDate");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIsNull() {
			addCriterion("frontCover is null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIsNotNull() {
			addCriterion("frontCover is not null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverEqualTo(Integer value) {
			addCriterion("frontCover =", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverNotEqualTo(Integer value) {
			addCriterion("frontCover <>", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverGreaterThan(Integer value) {
			addCriterion("frontCover >", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverGreaterThanOrEqualTo(Integer value) {
			addCriterion("frontCover >=", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverLessThan(Integer value) {
			addCriterion("frontCover <", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverLessThanOrEqualTo(Integer value) {
			addCriterion("frontCover <=", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIn(List<Integer> values) {
			addCriterion("frontCover in", values, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverNotIn(List<Integer> values) {
			addCriterion("frontCover not in", values, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverBetween(Integer value1, Integer value2) {
			addCriterion("frontCover between", value1, value2, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverNotBetween(Integer value1, Integer value2) {
			addCriterion("frontCover not between", value1, value2, "frontcover");
			return (Criteria) this;
		}

		public Criteria andPopularIsNull() {
			addCriterion("popular is null");
			return (Criteria) this;
		}

		public Criteria andPopularIsNotNull() {
			addCriterion("popular is not null");
			return (Criteria) this;
		}

		public Criteria andPopularEqualTo(Integer value) {
			addCriterion("popular =", value, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularNotEqualTo(Integer value) {
			addCriterion("popular <>", value, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularGreaterThan(Integer value) {
			addCriterion("popular >", value, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularGreaterThanOrEqualTo(Integer value) {
			addCriterion("popular >=", value, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularLessThan(Integer value) {
			addCriterion("popular <", value, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularLessThanOrEqualTo(Integer value) {
			addCriterion("popular <=", value, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularIn(List<Integer> values) {
			addCriterion("popular in", values, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularNotIn(List<Integer> values) {
			addCriterion("popular not in", values, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularBetween(Integer value1, Integer value2) {
			addCriterion("popular between", value1, value2, "popular");
			return (Criteria) this;
		}

		public Criteria andPopularNotBetween(Integer value1, Integer value2) {
			addCriterion("popular not between", value1, value2, "popular");
			return (Criteria) this;
		}

		public Criteria andShowinagendaIsNull() {
			addCriterion("showInAgenda is null");
			return (Criteria) this;
		}

		public Criteria andShowinagendaIsNotNull() {
			addCriterion("showInAgenda is not null");
			return (Criteria) this;
		}

		public Criteria andShowinagendaEqualTo(Integer value) {
			addCriterion("showInAgenda =", value, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaNotEqualTo(Integer value) {
			addCriterion("showInAgenda <>", value, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaGreaterThan(Integer value) {
			addCriterion("showInAgenda >", value, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaGreaterThanOrEqualTo(Integer value) {
			addCriterion("showInAgenda >=", value, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaLessThan(Integer value) {
			addCriterion("showInAgenda <", value, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaLessThanOrEqualTo(Integer value) {
			addCriterion("showInAgenda <=", value, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaIn(List<Integer> values) {
			addCriterion("showInAgenda in", values, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaNotIn(List<Integer> values) {
			addCriterion("showInAgenda not in", values, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaBetween(Integer value1, Integer value2) {
			addCriterion("showInAgenda between", value1, value2, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andShowinagendaNotBetween(Integer value1, Integer value2) {
			addCriterion("showInAgenda not between", value1, value2, "showinagenda");
			return (Criteria) this;
		}

		public Criteria andAgendaDateIsNull() {
			addCriterion("agenda_date is null");
			return (Criteria) this;
		}

		public Criteria andAgendaDateIsNotNull() {
			addCriterion("agenda_date is not null");
			return (Criteria) this;
		}

		public Criteria andAgendaDateEqualTo(Date value) {
			addCriterionForJDBCDate("agenda_date =", value, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateNotEqualTo(Date value) {
			addCriterionForJDBCDate("agenda_date <>", value, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateGreaterThan(Date value) {
			addCriterionForJDBCDate("agenda_date >", value, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("agenda_date >=", value, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateLessThan(Date value) {
			addCriterionForJDBCDate("agenda_date <", value, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("agenda_date <=", value, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateIn(List<Date> values) {
			addCriterionForJDBCDate("agenda_date in", values, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateNotIn(List<Date> values) {
			addCriterionForJDBCDate("agenda_date not in", values, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("agenda_date between", value1, value2, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andAgendaDateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("agenda_date not between", value1, value2, "agendaDate");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table NOTE
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 16 18:11:37 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}