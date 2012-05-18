package com.tdil.djmag.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MagazineExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public MagazineExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	protected MagazineExample(MagazineExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MAGAZINE
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
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

		public Criteria andDescriptionIsNull() {
			addCriterion("description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andPublishDateIsNull() {
			addCriterion("publish_date is null");
			return (Criteria) this;
		}

		public Criteria andPublishDateIsNotNull() {
			addCriterion("publish_date is not null");
			return (Criteria) this;
		}

		public Criteria andPublishDateEqualTo(Date value) {
			addCriterionForJDBCDate("publish_date =", value, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateNotEqualTo(Date value) {
			addCriterionForJDBCDate("publish_date <>", value, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateGreaterThan(Date value) {
			addCriterionForJDBCDate("publish_date >", value, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("publish_date >=", value, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateLessThan(Date value) {
			addCriterionForJDBCDate("publish_date <", value, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("publish_date <=", value, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateIn(List<Date> values) {
			addCriterionForJDBCDate("publish_date in", values, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateNotIn(List<Date> values) {
			addCriterionForJDBCDate("publish_date not in", values, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("publish_date between", value1, value2, "publishDate");
			return (Criteria) this;
		}

		public Criteria andPublishDateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("publish_date not between", value1, value2, "publishDate");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextIsNull() {
			addCriterion("frontCoverExt is null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextIsNotNull() {
			addCriterion("frontCoverExt is not null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextEqualTo(String value) {
			addCriterion("frontCoverExt =", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextNotEqualTo(String value) {
			addCriterion("frontCoverExt <>", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextGreaterThan(String value) {
			addCriterion("frontCoverExt >", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextGreaterThanOrEqualTo(String value) {
			addCriterion("frontCoverExt >=", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextLessThan(String value) {
			addCriterion("frontCoverExt <", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextLessThanOrEqualTo(String value) {
			addCriterion("frontCoverExt <=", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextLike(String value) {
			addCriterion("frontCoverExt like", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextNotLike(String value) {
			addCriterion("frontCoverExt not like", value, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextIn(List<String> values) {
			addCriterion("frontCoverExt in", values, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextNotIn(List<String> values) {
			addCriterion("frontCoverExt not in", values, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextBetween(String value1, String value2) {
			addCriterion("frontCoverExt between", value1, value2, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverextNotBetween(String value1, String value2) {
			addCriterion("frontCoverExt not between", value1, value2, "frontcoverext");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdIsNull() {
			addCriterion("frontCover_id is null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdIsNotNull() {
			addCriterion("frontCover_id is not null");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdEqualTo(Integer value) {
			addCriterion("frontCover_id =", value, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdNotEqualTo(Integer value) {
			addCriterion("frontCover_id <>", value, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdGreaterThan(Integer value) {
			addCriterion("frontCover_id >", value, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("frontCover_id >=", value, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdLessThan(Integer value) {
			addCriterion("frontCover_id <", value, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdLessThanOrEqualTo(Integer value) {
			addCriterion("frontCover_id <=", value, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdIn(List<Integer> values) {
			addCriterion("frontCover_id in", values, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdNotIn(List<Integer> values) {
			addCriterion("frontCover_id not in", values, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdBetween(Integer value1, Integer value2) {
			addCriterion("frontCover_id between", value1, value2, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andFrontcoverIdNotBetween(Integer value1, Integer value2) {
			addCriterion("frontCover_id not between", value1, value2, "frontcoverId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextIsNull() {
			addCriterion("magazineContentExt is null");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextIsNotNull() {
			addCriterion("magazineContentExt is not null");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextEqualTo(String value) {
			addCriterion("magazineContentExt =", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextNotEqualTo(String value) {
			addCriterion("magazineContentExt <>", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextGreaterThan(String value) {
			addCriterion("magazineContentExt >", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextGreaterThanOrEqualTo(String value) {
			addCriterion("magazineContentExt >=", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextLessThan(String value) {
			addCriterion("magazineContentExt <", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextLessThanOrEqualTo(String value) {
			addCriterion("magazineContentExt <=", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextLike(String value) {
			addCriterion("magazineContentExt like", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextNotLike(String value) {
			addCriterion("magazineContentExt not like", value, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextIn(List<String> values) {
			addCriterion("magazineContentExt in", values, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextNotIn(List<String> values) {
			addCriterion("magazineContentExt not in", values, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextBetween(String value1, String value2) {
			addCriterion("magazineContentExt between", value1, value2, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentextNotBetween(String value1, String value2) {
			addCriterion("magazineContentExt not between", value1, value2, "magazinecontentext");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdIsNull() {
			addCriterion("magazineContent_id is null");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdIsNotNull() {
			addCriterion("magazineContent_id is not null");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdEqualTo(Integer value) {
			addCriterion("magazineContent_id =", value, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdNotEqualTo(Integer value) {
			addCriterion("magazineContent_id <>", value, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdGreaterThan(Integer value) {
			addCriterion("magazineContent_id >", value, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("magazineContent_id >=", value, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdLessThan(Integer value) {
			addCriterion("magazineContent_id <", value, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdLessThanOrEqualTo(Integer value) {
			addCriterion("magazineContent_id <=", value, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdIn(List<Integer> values) {
			addCriterion("magazineContent_id in", values, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdNotIn(List<Integer> values) {
			addCriterion("magazineContent_id not in", values, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdBetween(Integer value1, Integer value2) {
			addCriterion("magazineContent_id between", value1, value2, "magazinecontentId");
			return (Criteria) this;
		}

		public Criteria andMagazinecontentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("magazineContent_id not between", value1, value2, "magazinecontentId");
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
     * This class corresponds to the database table MAGAZINE
     *
     * @mbggenerated do_not_delete_during_merge Fri Apr 20 07:55:01 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}