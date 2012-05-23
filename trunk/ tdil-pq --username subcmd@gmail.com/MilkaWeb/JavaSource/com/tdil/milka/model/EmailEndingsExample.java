package com.tdil.milka.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailEndingsExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public EmailEndingsExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	protected EmailEndingsExample(EmailEndingsExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table EMAIL_ENDINGS
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
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
			addCriterion("ee.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ee.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("ee.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("ee.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("ee.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("ee.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("ee.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("ee.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("ee.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("ee.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andCreationdateIsNull() {
			addCriterion("ee.creationDate is null");
			return (Criteria) this;
		}

		public Criteria andCreationdateIsNotNull() {
			addCriterion("ee.creationDate is not null");
			return (Criteria) this;
		}

		public Criteria andCreationdateEqualTo(Date value) {
			addCriterion("ee.creationDate =", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateNotEqualTo(Date value) {
			addCriterion("ee.creationDate <>", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateGreaterThan(Date value) {
			addCriterion("ee.creationDate >", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateGreaterThanOrEqualTo(Date value) {
			addCriterion("ee.creationDate >=", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateLessThan(Date value) {
			addCriterion("ee.creationDate <", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateLessThanOrEqualTo(Date value) {
			addCriterion("ee.creationDate <=", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateIn(List<Date> values) {
			addCriterion("ee.creationDate in", values, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateNotIn(List<Date> values) {
			addCriterion("ee.creationDate not in", values, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateBetween(Date value1, Date value2) {
			addCriterion("ee.creationDate between", value1, value2, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateNotBetween(Date value1, Date value2) {
			addCriterion("ee.creationDate not between", value1, value2, "creationdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateIsNull() {
			addCriterion("ee.publishDate is null");
			return (Criteria) this;
		}

		public Criteria andPublishdateIsNotNull() {
			addCriterion("ee.publishDate is not null");
			return (Criteria) this;
		}

		public Criteria andPublishdateEqualTo(Date value) {
			addCriterion("ee.publishDate =", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateNotEqualTo(Date value) {
			addCriterion("ee.publishDate <>", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateGreaterThan(Date value) {
			addCriterion("ee.publishDate >", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateGreaterThanOrEqualTo(Date value) {
			addCriterion("ee.publishDate >=", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateLessThan(Date value) {
			addCriterion("ee.publishDate <", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateLessThanOrEqualTo(Date value) {
			addCriterion("ee.publishDate <=", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateIn(List<Date> values) {
			addCriterion("ee.publishDate in", values, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateNotIn(List<Date> values) {
			addCriterion("ee.publishDate not in", values, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateBetween(Date value1, Date value2) {
			addCriterion("ee.publishDate between", value1, value2, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateNotBetween(Date value1, Date value2) {
			addCriterion("ee.publishDate not between", value1, value2, "publishdate");
			return (Criteria) this;
		}

		public Criteria andIdAuthorIsNull() {
			addCriterion("ee.id_author is null");
			return (Criteria) this;
		}

		public Criteria andIdAuthorIsNotNull() {
			addCriterion("ee.id_author is not null");
			return (Criteria) this;
		}

		public Criteria andIdAuthorEqualTo(Integer value) {
			addCriterion("ee.id_author =", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorNotEqualTo(Integer value) {
			addCriterion("ee.id_author <>", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorGreaterThan(Integer value) {
			addCriterion("ee.id_author >", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.id_author >=", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorLessThan(Integer value) {
			addCriterion("ee.id_author <", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorLessThanOrEqualTo(Integer value) {
			addCriterion("ee.id_author <=", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorIn(List<Integer> values) {
			addCriterion("ee.id_author in", values, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorNotIn(List<Integer> values) {
			addCriterion("ee.id_author not in", values, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_author between", value1, value2, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_author not between", value1, value2, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("ee.title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("ee.title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("ee.title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("ee.title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("ee.title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("ee.title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("ee.title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("ee.title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("ee.title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("ee.title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("ee.title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("ee.title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("ee.title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("ee.title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("ee.description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("ee.description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("ee.description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("ee.description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("ee.description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("ee.description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("ee.description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("ee.description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("ee.description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("ee.description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("ee.description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("ee.description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("ee.description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("ee.description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIsNull() {
			addCriterion("ee.frontCover is null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIsNotNull() {
			addCriterion("ee.frontCover is not null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverEqualTo(Integer value) {
			addCriterion("ee.frontCover =", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverNotEqualTo(Integer value) {
			addCriterion("ee.frontCover <>", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverGreaterThan(Integer value) {
			addCriterion("ee.frontCover >", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.frontCover >=", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverLessThan(Integer value) {
			addCriterion("ee.frontCover <", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverLessThanOrEqualTo(Integer value) {
			addCriterion("ee.frontCover <=", value, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIn(List<Integer> values) {
			addCriterion("ee.frontCover in", values, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverNotIn(List<Integer> values) {
			addCriterion("ee.frontCover not in", values, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverBetween(Integer value1, Integer value2) {
			addCriterion("ee.frontCover between", value1, value2, "frontcover");
			return (Criteria) this;
		}

		public Criteria andFrontcoverNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.frontCover not between", value1, value2, "frontcover");
			return (Criteria) this;
		}

		public Criteria andShowinhomeIsNull() {
			addCriterion("ee.showInHome is null");
			return (Criteria) this;
		}

		public Criteria andShowinhomeIsNotNull() {
			addCriterion("ee.showInHome is not null");
			return (Criteria) this;
		}

		public Criteria andShowinhomeEqualTo(Integer value) {
			addCriterion("ee.showInHome =", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeNotEqualTo(Integer value) {
			addCriterion("ee.showInHome <>", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeGreaterThan(Integer value) {
			addCriterion("ee.showInHome >", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.showInHome >=", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeLessThan(Integer value) {
			addCriterion("ee.showInHome <", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeLessThanOrEqualTo(Integer value) {
			addCriterion("ee.showInHome <=", value, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeIn(List<Integer> values) {
			addCriterion("ee.showInHome in", values, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeNotIn(List<Integer> values) {
			addCriterion("ee.showInHome not in", values, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeBetween(Integer value1, Integer value2) {
			addCriterion("ee.showInHome between", value1, value2, "showinhome");
			return (Criteria) this;
		}

		public Criteria andShowinhomeNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.showInHome not between", value1, value2, "showinhome");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("ee.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("ee.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("ee.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("ee.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("ee.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("ee.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("ee.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("ee.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("ee.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("ee.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIsNull() {
			addCriterion("ee.id_blob_data is null");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIsNotNull() {
			addCriterion("ee.id_blob_data is not null");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataEqualTo(Integer value) {
			addCriterion("ee.id_blob_data =", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotEqualTo(Integer value) {
			addCriterion("ee.id_blob_data <>", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataGreaterThan(Integer value) {
			addCriterion("ee.id_blob_data >", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.id_blob_data >=", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataLessThan(Integer value) {
			addCriterion("ee.id_blob_data <", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataLessThanOrEqualTo(Integer value) {
			addCriterion("ee.id_blob_data <=", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIn(List<Integer> values) {
			addCriterion("ee.id_blob_data in", values, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotIn(List<Integer> values) {
			addCriterion("ee.id_blob_data not in", values, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_blob_data between", value1, value2, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_blob_data not between", value1, value2, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIsNull() {
			addCriterion("ee.ext_blob_data is null");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIsNotNull() {
			addCriterion("ee.ext_blob_data is not null");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataEqualTo(String value) {
			addCriterion("ee.ext_blob_data =", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotEqualTo(String value) {
			addCriterion("ee.ext_blob_data <>", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataGreaterThan(String value) {
			addCriterion("ee.ext_blob_data >", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataGreaterThanOrEqualTo(String value) {
			addCriterion("ee.ext_blob_data >=", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLessThan(String value) {
			addCriterion("ee.ext_blob_data <", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLessThanOrEqualTo(String value) {
			addCriterion("ee.ext_blob_data <=", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLike(String value) {
			addCriterion("ee.ext_blob_data like", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotLike(String value) {
			addCriterion("ee.ext_blob_data not like", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIn(List<String> values) {
			addCriterion("ee.ext_blob_data in", values, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotIn(List<String> values) {
			addCriterion("ee.ext_blob_data not in", values, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataBetween(String value1, String value2) {
			addCriterion("ee.ext_blob_data between", value1, value2, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotBetween(String value1, String value2) {
			addCriterion("ee.ext_blob_data not between", value1, value2, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataIsNull() {
			addCriterion("ee.id_approved_data is null");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataIsNotNull() {
			addCriterion("ee.id_approved_data is not null");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataEqualTo(Integer value) {
			addCriterion("ee.id_approved_data =", value, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataNotEqualTo(Integer value) {
			addCriterion("ee.id_approved_data <>", value, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataGreaterThan(Integer value) {
			addCriterion("ee.id_approved_data >", value, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.id_approved_data >=", value, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataLessThan(Integer value) {
			addCriterion("ee.id_approved_data <", value, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataLessThanOrEqualTo(Integer value) {
			addCriterion("ee.id_approved_data <=", value, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataIn(List<Integer> values) {
			addCriterion("ee.id_approved_data in", values, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataNotIn(List<Integer> values) {
			addCriterion("ee.id_approved_data not in", values, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_approved_data between", value1, value2, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdApprovedDataNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_approved_data not between", value1, value2, "idApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataIsNull() {
			addCriterion("ee.ext_approved_data is null");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataIsNotNull() {
			addCriterion("ee.ext_approved_data is not null");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataEqualTo(String value) {
			addCriterion("ee.ext_approved_data =", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataNotEqualTo(String value) {
			addCriterion("ee.ext_approved_data <>", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataGreaterThan(String value) {
			addCriterion("ee.ext_approved_data >", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataGreaterThanOrEqualTo(String value) {
			addCriterion("ee.ext_approved_data >=", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataLessThan(String value) {
			addCriterion("ee.ext_approved_data <", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataLessThanOrEqualTo(String value) {
			addCriterion("ee.ext_approved_data <=", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataLike(String value) {
			addCriterion("ee.ext_approved_data like", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataNotLike(String value) {
			addCriterion("ee.ext_approved_data not like", value, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataIn(List<String> values) {
			addCriterion("ee.ext_approved_data in", values, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataNotIn(List<String> values) {
			addCriterion("ee.ext_approved_data not in", values, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataBetween(String value1, String value2) {
			addCriterion("ee.ext_approved_data between", value1, value2, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andExtApprovedDataNotBetween(String value1, String value2) {
			addCriterion("ee.ext_approved_data not between", value1, value2, "extApprovedData");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterIsNull() {
			addCriterion("ee.id_click_counter is null");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterIsNotNull() {
			addCriterion("ee.id_click_counter is not null");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterEqualTo(Integer value) {
			addCriterion("ee.id_click_counter =", value, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterNotEqualTo(Integer value) {
			addCriterion("ee.id_click_counter <>", value, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterGreaterThan(Integer value) {
			addCriterion("ee.id_click_counter >", value, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.id_click_counter >=", value, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterLessThan(Integer value) {
			addCriterion("ee.id_click_counter <", value, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterLessThanOrEqualTo(Integer value) {
			addCriterion("ee.id_click_counter <=", value, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterIn(List<Integer> values) {
			addCriterion("ee.id_click_counter in", values, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterNotIn(List<Integer> values) {
			addCriterion("ee.id_click_counter not in", values, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_click_counter between", value1, value2, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andIdClickCounterNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.id_click_counter not between", value1, value2, "idClickCounter");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("ee.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("ee.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("ee.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("ee.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("ee.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("ee.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("ee.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("ee.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("ee.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("ee.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("ee.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("ee.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table EMAIL_ENDINGS
     *
     * @mbggenerated do_not_delete_during_merge Mon May 21 16:35:11 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}