package com.tdil.milka.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MilkaPhotoTagExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public MilkaPhotoTagExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected MilkaPhotoTagExample(MilkaPhotoTagExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
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
			addCriterion("mpt.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("mpt.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("mpt.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("mpt.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("mpt.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("mpt.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("mpt.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("mpt.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("mpt.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("mpt.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("mpt.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("mpt.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoIsNull() {
			addCriterion("mpt.id_milka_photo is null");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoIsNotNull() {
			addCriterion("mpt.id_milka_photo is not null");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoEqualTo(Integer value) {
			addCriterion("mpt.id_milka_photo =", value, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoNotEqualTo(Integer value) {
			addCriterion("mpt.id_milka_photo <>", value, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoGreaterThan(Integer value) {
			addCriterion("mpt.id_milka_photo >", value, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoGreaterThanOrEqualTo(Integer value) {
			addCriterion("mpt.id_milka_photo >=", value, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoLessThan(Integer value) {
			addCriterion("mpt.id_milka_photo <", value, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoLessThanOrEqualTo(Integer value) {
			addCriterion("mpt.id_milka_photo <=", value, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoIn(List<Integer> values) {
			addCriterion("mpt.id_milka_photo in", values, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoNotIn(List<Integer> values) {
			addCriterion("mpt.id_milka_photo not in", values, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoBetween(Integer value1, Integer value2) {
			addCriterion("mpt.id_milka_photo between", value1, value2, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdMilkaPhotoNotBetween(Integer value1, Integer value2) {
			addCriterion("mpt.id_milka_photo not between", value1, value2, "idMilkaPhoto");
			return (Criteria) this;
		}

		public Criteria andIdTagIsNull() {
			addCriterion("mpt.id_tag is null");
			return (Criteria) this;
		}

		public Criteria andIdTagIsNotNull() {
			addCriterion("mpt.id_tag is not null");
			return (Criteria) this;
		}

		public Criteria andIdTagEqualTo(Integer value) {
			addCriterion("mpt.id_tag =", value, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagNotEqualTo(Integer value) {
			addCriterion("mpt.id_tag <>", value, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagGreaterThan(Integer value) {
			addCriterion("mpt.id_tag >", value, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagGreaterThanOrEqualTo(Integer value) {
			addCriterion("mpt.id_tag >=", value, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagLessThan(Integer value) {
			addCriterion("mpt.id_tag <", value, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagLessThanOrEqualTo(Integer value) {
			addCriterion("mpt.id_tag <=", value, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagIn(List<Integer> values) {
			addCriterion("mpt.id_tag in", values, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagNotIn(List<Integer> values) {
			addCriterion("mpt.id_tag not in", values, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagBetween(Integer value1, Integer value2) {
			addCriterion("mpt.id_tag between", value1, value2, "idTag");
			return (Criteria) this;
		}

		public Criteria andIdTagNotBetween(Integer value1, Integer value2) {
			addCriterion("mpt.id_tag not between", value1, value2, "idTag");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("mpt.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("mpt.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("mpt.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("mpt.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("mpt.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("mpt.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("mpt.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("mpt.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("mpt.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("mpt.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("mpt.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("mpt.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table MILKA_PHOTO_TAG
     *
     * @mbggenerated do_not_delete_during_merge Fri May 04 09:53:04 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}