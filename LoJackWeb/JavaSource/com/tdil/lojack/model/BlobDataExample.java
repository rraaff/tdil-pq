package com.tdil.lojack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlobDataExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public BlobDataExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	protected BlobDataExample(BlobDataExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table BLOB_DATA
	 * @mbggenerated  Thu May 02 23:36:09 ART 2013
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
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition,
				List<? extends Object> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
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
			addCriterion("bd.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("bd.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("bd.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("bd.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("bd.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("bd.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("bd.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("bd.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("bd.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("bd.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("bd.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("bd.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andDatatypeIsNull() {
			addCriterion("bd.dataType is null");
			return (Criteria) this;
		}

		public Criteria andDatatypeIsNotNull() {
			addCriterion("bd.dataType is not null");
			return (Criteria) this;
		}

		public Criteria andDatatypeEqualTo(String value) {
			addCriterion("bd.dataType =", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeNotEqualTo(String value) {
			addCriterion("bd.dataType <>", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeGreaterThan(String value) {
			addCriterion("bd.dataType >", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeGreaterThanOrEqualTo(String value) {
			addCriterion("bd.dataType >=", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeLessThan(String value) {
			addCriterion("bd.dataType <", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeLessThanOrEqualTo(String value) {
			addCriterion("bd.dataType <=", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeLike(String value) {
			addCriterion("bd.dataType like", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeNotLike(String value) {
			addCriterion("bd.dataType not like", value, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeIn(List<String> values) {
			addCriterion("bd.dataType in", values, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeNotIn(List<String> values) {
			addCriterion("bd.dataType not in", values, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeBetween(String value1, String value2) {
			addCriterion("bd.dataType between", value1, value2, "datatype");
			return (Criteria) this;
		}

		public Criteria andDatatypeNotBetween(String value1, String value2) {
			addCriterion("bd.dataType not between", value1, value2, "datatype");
			return (Criteria) this;
		}

		public Criteria andFilenameIsNull() {
			addCriterion("bd.filename is null");
			return (Criteria) this;
		}

		public Criteria andFilenameIsNotNull() {
			addCriterion("bd.filename is not null");
			return (Criteria) this;
		}

		public Criteria andFilenameEqualTo(String value) {
			addCriterion("bd.filename =", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotEqualTo(String value) {
			addCriterion("bd.filename <>", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameGreaterThan(String value) {
			addCriterion("bd.filename >", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameGreaterThanOrEqualTo(String value) {
			addCriterion("bd.filename >=", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameLessThan(String value) {
			addCriterion("bd.filename <", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameLessThanOrEqualTo(String value) {
			addCriterion("bd.filename <=", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameLike(String value) {
			addCriterion("bd.filename like", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotLike(String value) {
			addCriterion("bd.filename not like", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameIn(List<String> values) {
			addCriterion("bd.filename in", values, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotIn(List<String> values) {
			addCriterion("bd.filename not in", values, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameBetween(String value1, String value2) {
			addCriterion("bd.filename between", value1, value2, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotBetween(String value1, String value2) {
			addCriterion("bd.filename not between", value1, value2, "filename");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("bd.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("bd.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("bd.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("bd.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("bd.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("bd.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("bd.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("bd.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("bd.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("bd.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("bd.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("bd.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BLOB_DATA
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 14 22:47:06 ART 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}