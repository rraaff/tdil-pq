package com.tdil.djmag.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteImageExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public NoteImageExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	protected NoteImageExample(NoteImageExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
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

		public Criteria andIdNoteIsNull() {
			addCriterion("id_note is null");
			return (Criteria) this;
		}

		public Criteria andIdNoteIsNotNull() {
			addCriterion("id_note is not null");
			return (Criteria) this;
		}

		public Criteria andIdNoteEqualTo(Integer value) {
			addCriterion("id_note =", value, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteNotEqualTo(Integer value) {
			addCriterion("id_note <>", value, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteGreaterThan(Integer value) {
			addCriterion("id_note >", value, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_note >=", value, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteLessThan(Integer value) {
			addCriterion("id_note <", value, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteLessThanOrEqualTo(Integer value) {
			addCriterion("id_note <=", value, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteIn(List<Integer> values) {
			addCriterion("id_note in", values, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteNotIn(List<Integer> values) {
			addCriterion("id_note not in", values, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteBetween(Integer value1, Integer value2) {
			addCriterion("id_note between", value1, value2, "idNote");
			return (Criteria) this;
		}

		public Criteria andIdNoteNotBetween(Integer value1, Integer value2) {
			addCriterion("id_note not between", value1, value2, "idNote");
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

		public Criteria andFilenameIsNull() {
			addCriterion("fileName is null");
			return (Criteria) this;
		}

		public Criteria andFilenameIsNotNull() {
			addCriterion("fileName is not null");
			return (Criteria) this;
		}

		public Criteria andFilenameEqualTo(String value) {
			addCriterion("fileName =", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotEqualTo(String value) {
			addCriterion("fileName <>", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameGreaterThan(String value) {
			addCriterion("fileName >", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameGreaterThanOrEqualTo(String value) {
			addCriterion("fileName >=", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameLessThan(String value) {
			addCriterion("fileName <", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameLessThanOrEqualTo(String value) {
			addCriterion("fileName <=", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameLike(String value) {
			addCriterion("fileName like", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotLike(String value) {
			addCriterion("fileName not like", value, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameIn(List<String> values) {
			addCriterion("fileName in", values, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotIn(List<String> values) {
			addCriterion("fileName not in", values, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameBetween(String value1, String value2) {
			addCriterion("fileName between", value1, value2, "filename");
			return (Criteria) this;
		}

		public Criteria andFilenameNotBetween(String value1, String value2) {
			addCriterion("fileName not between", value1, value2, "filename");
			return (Criteria) this;
		}

		public Criteria andExtensionIsNull() {
			addCriterion("extension is null");
			return (Criteria) this;
		}

		public Criteria andExtensionIsNotNull() {
			addCriterion("extension is not null");
			return (Criteria) this;
		}

		public Criteria andExtensionEqualTo(String value) {
			addCriterion("extension =", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionNotEqualTo(String value) {
			addCriterion("extension <>", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionGreaterThan(String value) {
			addCriterion("extension >", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionGreaterThanOrEqualTo(String value) {
			addCriterion("extension >=", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionLessThan(String value) {
			addCriterion("extension <", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionLessThanOrEqualTo(String value) {
			addCriterion("extension <=", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionLike(String value) {
			addCriterion("extension like", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionNotLike(String value) {
			addCriterion("extension not like", value, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionIn(List<String> values) {
			addCriterion("extension in", values, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionNotIn(List<String> values) {
			addCriterion("extension not in", values, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionBetween(String value1, String value2) {
			addCriterion("extension between", value1, value2, "extension");
			return (Criteria) this;
		}

		public Criteria andExtensionNotBetween(String value1, String value2) {
			addCriterion("extension not between", value1, value2, "extension");
			return (Criteria) this;
		}

		public Criteria andContenttypeIsNull() {
			addCriterion("contentType is null");
			return (Criteria) this;
		}

		public Criteria andContenttypeIsNotNull() {
			addCriterion("contentType is not null");
			return (Criteria) this;
		}

		public Criteria andContenttypeEqualTo(String value) {
			addCriterion("contentType =", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeNotEqualTo(String value) {
			addCriterion("contentType <>", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeGreaterThan(String value) {
			addCriterion("contentType >", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeGreaterThanOrEqualTo(String value) {
			addCriterion("contentType >=", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeLessThan(String value) {
			addCriterion("contentType <", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeLessThanOrEqualTo(String value) {
			addCriterion("contentType <=", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeLike(String value) {
			addCriterion("contentType like", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeNotLike(String value) {
			addCriterion("contentType not like", value, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeIn(List<String> values) {
			addCriterion("contentType in", values, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeNotIn(List<String> values) {
			addCriterion("contentType not in", values, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeBetween(String value1, String value2) {
			addCriterion("contentType between", value1, value2, "contenttype");
			return (Criteria) this;
		}

		public Criteria andContenttypeNotBetween(String value1, String value2) {
			addCriterion("contentType not between", value1, value2, "contenttype");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNull() {
			addCriterion("orderNumber is null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNotNull() {
			addCriterion("orderNumber is not null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberEqualTo(Integer value) {
			addCriterion("orderNumber =", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotEqualTo(Integer value) {
			addCriterion("orderNumber <>", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThan(Integer value) {
			addCriterion("orderNumber >", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("orderNumber >=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThan(Integer value) {
			addCriterion("orderNumber <", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThanOrEqualTo(Integer value) {
			addCriterion("orderNumber <=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIn(List<Integer> values) {
			addCriterion("orderNumber in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotIn(List<Integer> values) {
			addCriterion("orderNumber not in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberBetween(Integer value1, Integer value2) {
			addCriterion("orderNumber between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotBetween(Integer value1, Integer value2) {
			addCriterion("orderNumber not between", value1, value2, "ordernumber");
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
     * This class corresponds to the database table NOTE_IMAGE
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 16 18:11:37 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}