package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WallWrittingExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public WallWrittingExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected WallWrittingExample(WallWrittingExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WALL_WRITTING
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
			addCriterion("ww.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ww.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("ww.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("ww.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("ww.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("ww.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("ww.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("ww.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("ww.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("ww.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("ww.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("ww.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andCreationdateIsNull() {
			addCriterion("ww.creationDate is null");
			return (Criteria) this;
		}

		public Criteria andCreationdateIsNotNull() {
			addCriterion("ww.creationDate is not null");
			return (Criteria) this;
		}

		public Criteria andCreationdateEqualTo(Date value) {
			addCriterion("ww.creationDate =", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateNotEqualTo(Date value) {
			addCriterion("ww.creationDate <>", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateGreaterThan(Date value) {
			addCriterion("ww.creationDate >", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateGreaterThanOrEqualTo(Date value) {
			addCriterion("ww.creationDate >=", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateLessThan(Date value) {
			addCriterion("ww.creationDate <", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateLessThanOrEqualTo(Date value) {
			addCriterion("ww.creationDate <=", value, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateIn(List<Date> values) {
			addCriterion("ww.creationDate in", values, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateNotIn(List<Date> values) {
			addCriterion("ww.creationDate not in", values, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateBetween(Date value1, Date value2) {
			addCriterion("ww.creationDate between", value1, value2, "creationdate");
			return (Criteria) this;
		}

		public Criteria andCreationdateNotBetween(Date value1, Date value2) {
			addCriterion("ww.creationDate not between", value1, value2, "creationdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateIsNull() {
			addCriterion("ww.publishDate is null");
			return (Criteria) this;
		}

		public Criteria andPublishdateIsNotNull() {
			addCriterion("ww.publishDate is not null");
			return (Criteria) this;
		}

		public Criteria andPublishdateEqualTo(Date value) {
			addCriterion("ww.publishDate =", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateNotEqualTo(Date value) {
			addCriterion("ww.publishDate <>", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateGreaterThan(Date value) {
			addCriterion("ww.publishDate >", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateGreaterThanOrEqualTo(Date value) {
			addCriterion("ww.publishDate >=", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateLessThan(Date value) {
			addCriterion("ww.publishDate <", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateLessThanOrEqualTo(Date value) {
			addCriterion("ww.publishDate <=", value, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateIn(List<Date> values) {
			addCriterion("ww.publishDate in", values, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateNotIn(List<Date> values) {
			addCriterion("ww.publishDate not in", values, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateBetween(Date value1, Date value2) {
			addCriterion("ww.publishDate between", value1, value2, "publishdate");
			return (Criteria) this;
		}

		public Criteria andPublishdateNotBetween(Date value1, Date value2) {
			addCriterion("ww.publishDate not between", value1, value2, "publishdate");
			return (Criteria) this;
		}

		public Criteria andIdAuthorIsNull() {
			addCriterion("ww.id_author is null");
			return (Criteria) this;
		}

		public Criteria andIdAuthorIsNotNull() {
			addCriterion("ww.id_author is not null");
			return (Criteria) this;
		}

		public Criteria andIdAuthorEqualTo(Integer value) {
			addCriterion("ww.id_author =", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorNotEqualTo(Integer value) {
			addCriterion("ww.id_author <>", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorGreaterThan(Integer value) {
			addCriterion("ww.id_author >", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorGreaterThanOrEqualTo(Integer value) {
			addCriterion("ww.id_author >=", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorLessThan(Integer value) {
			addCriterion("ww.id_author <", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorLessThanOrEqualTo(Integer value) {
			addCriterion("ww.id_author <=", value, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorIn(List<Integer> values) {
			addCriterion("ww.id_author in", values, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorNotIn(List<Integer> values) {
			addCriterion("ww.id_author not in", values, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorBetween(Integer value1, Integer value2) {
			addCriterion("ww.id_author between", value1, value2, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andIdAuthorNotBetween(Integer value1, Integer value2) {
			addCriterion("ww.id_author not between", value1, value2, "idAuthor");
			return (Criteria) this;
		}

		public Criteria andOriginaltextIsNull() {
			addCriterion("ww.originalText is null");
			return (Criteria) this;
		}

		public Criteria andOriginaltextIsNotNull() {
			addCriterion("ww.originalText is not null");
			return (Criteria) this;
		}

		public Criteria andOriginaltextEqualTo(String value) {
			addCriterion("ww.originalText =", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextNotEqualTo(String value) {
			addCriterion("ww.originalText <>", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextGreaterThan(String value) {
			addCriterion("ww.originalText >", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextGreaterThanOrEqualTo(String value) {
			addCriterion("ww.originalText >=", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextLessThan(String value) {
			addCriterion("ww.originalText <", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextLessThanOrEqualTo(String value) {
			addCriterion("ww.originalText <=", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextLike(String value) {
			addCriterion("ww.originalText like", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextNotLike(String value) {
			addCriterion("ww.originalText not like", value, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextIn(List<String> values) {
			addCriterion("ww.originalText in", values, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextNotIn(List<String> values) {
			addCriterion("ww.originalText not in", values, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextBetween(String value1, String value2) {
			addCriterion("ww.originalText between", value1, value2, "originaltext");
			return (Criteria) this;
		}

		public Criteria andOriginaltextNotBetween(String value1, String value2) {
			addCriterion("ww.originalText not between", value1, value2, "originaltext");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("ww.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("ww.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("ww.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("ww.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("ww.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("ww.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("ww.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("ww.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("ww.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("ww.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("ww.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("ww.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andIdWallIsNull() {
			addCriterion("ww.id_wall is null");
			return (Criteria) this;
		}

		public Criteria andIdWallIsNotNull() {
			addCriterion("ww.id_wall is not null");
			return (Criteria) this;
		}

		public Criteria andIdWallEqualTo(Integer value) {
			addCriterion("ww.id_wall =", value, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallNotEqualTo(Integer value) {
			addCriterion("ww.id_wall <>", value, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallGreaterThan(Integer value) {
			addCriterion("ww.id_wall >", value, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallGreaterThanOrEqualTo(Integer value) {
			addCriterion("ww.id_wall >=", value, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallLessThan(Integer value) {
			addCriterion("ww.id_wall <", value, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallLessThanOrEqualTo(Integer value) {
			addCriterion("ww.id_wall <=", value, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallIn(List<Integer> values) {
			addCriterion("ww.id_wall in", values, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallNotIn(List<Integer> values) {
			addCriterion("ww.id_wall not in", values, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallBetween(Integer value1, Integer value2) {
			addCriterion("ww.id_wall between", value1, value2, "idWall");
			return (Criteria) this;
		}

		public Criteria andIdWallNotBetween(Integer value1, Integer value2) {
			addCriterion("ww.id_wall not between", value1, value2, "idWall");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("ww.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("ww.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("ww.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("ww.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("ww.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("ww.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("ww.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("ww.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("ww.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("ww.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("ww.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("ww.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table WALL_WRITTING
     *
     * @mbggenerated do_not_delete_during_merge Mon Jun 18 23:18:25 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}