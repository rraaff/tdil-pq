package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilePictureExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public ProfilePictureExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected ProfilePictureExample(ProfilePictureExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Mon Sep 10 20:21:29 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROFILE_PICTURE
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
			addCriterion("profpic.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("profpic.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("profpic.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("profpic.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("profpic.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("profpic.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("profpic.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("profpic.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("profpic.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("profpic.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("profpic.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("profpic.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIsNull() {
			addCriterion("profpic.id_blob_data is null");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIsNotNull() {
			addCriterion("profpic.id_blob_data is not null");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataEqualTo(Integer value) {
			addCriterion("profpic.id_blob_data =", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotEqualTo(Integer value) {
			addCriterion("profpic.id_blob_data <>", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataGreaterThan(Integer value) {
			addCriterion("profpic.id_blob_data >", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataGreaterThanOrEqualTo(Integer value) {
			addCriterion("profpic.id_blob_data >=", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataLessThan(Integer value) {
			addCriterion("profpic.id_blob_data <", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataLessThanOrEqualTo(Integer value) {
			addCriterion("profpic.id_blob_data <=", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIn(List<Integer> values) {
			addCriterion("profpic.id_blob_data in", values, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotIn(List<Integer> values) {
			addCriterion("profpic.id_blob_data not in", values, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataBetween(Integer value1, Integer value2) {
			addCriterion("profpic.id_blob_data between", value1, value2, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotBetween(Integer value1, Integer value2) {
			addCriterion("profpic.id_blob_data not between", value1, value2, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIsNull() {
			addCriterion("profpic.ext_blob_data is null");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIsNotNull() {
			addCriterion("profpic.ext_blob_data is not null");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataEqualTo(String value) {
			addCriterion("profpic.ext_blob_data =", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotEqualTo(String value) {
			addCriterion("profpic.ext_blob_data <>", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataGreaterThan(String value) {
			addCriterion("profpic.ext_blob_data >", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataGreaterThanOrEqualTo(String value) {
			addCriterion("profpic.ext_blob_data >=", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLessThan(String value) {
			addCriterion("profpic.ext_blob_data <", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLessThanOrEqualTo(String value) {
			addCriterion("profpic.ext_blob_data <=", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLike(String value) {
			addCriterion("profpic.ext_blob_data like", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotLike(String value) {
			addCriterion("profpic.ext_blob_data not like", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIn(List<String> values) {
			addCriterion("profpic.ext_blob_data in", values, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotIn(List<String> values) {
			addCriterion("profpic.ext_blob_data not in", values, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataBetween(String value1, String value2) {
			addCriterion("profpic.ext_blob_data between", value1, value2, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotBetween(String value1, String value2) {
			addCriterion("profpic.ext_blob_data not between", value1, value2, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("profpic.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("profpic.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("profpic.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("profpic.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("profpic.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("profpic.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("profpic.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("profpic.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("profpic.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("profpic.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("profpic.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("profpic.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROFILE_PICTURE
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 11 00:20:09 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}