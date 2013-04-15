package com.tdil.lojack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemUserExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public SystemUserExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	protected SystemUserExample(SystemUserExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WEBSITEUSER
	 * @mbggenerated  Sun Apr 14 22:47:25 ART 2013
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
			addCriterion("su.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("su.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("su.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("su.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("su.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("su.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("su.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("su.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("su.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("su.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("su.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("su.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andGuidIsNull() {
			addCriterion("su.guid is null");
			return (Criteria) this;
		}

		public Criteria andGuidIsNotNull() {
			addCriterion("su.guid is not null");
			return (Criteria) this;
		}

		public Criteria andGuidEqualTo(String value) {
			addCriterion("su.guid =", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotEqualTo(String value) {
			addCriterion("su.guid <>", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidGreaterThan(String value) {
			addCriterion("su.guid >", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidGreaterThanOrEqualTo(String value) {
			addCriterion("su.guid >=", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidLessThan(String value) {
			addCriterion("su.guid <", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidLessThanOrEqualTo(String value) {
			addCriterion("su.guid <=", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidLike(String value) {
			addCriterion("su.guid like", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotLike(String value) {
			addCriterion("su.guid not like", value, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidIn(List<String> values) {
			addCriterion("su.guid in", values, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotIn(List<String> values) {
			addCriterion("su.guid not in", values, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidBetween(String value1, String value2) {
			addCriterion("su.guid between", value1, value2, "guid");
			return (Criteria) this;
		}

		public Criteria andGuidNotBetween(String value1, String value2) {
			addCriterion("su.guid not between", value1, value2, "guid");
			return (Criteria) this;
		}

		public Criteria andIdAvatarIsNull() {
			addCriterion("su.id_avatar is null");
			return (Criteria) this;
		}

		public Criteria andIdAvatarIsNotNull() {
			addCriterion("su.id_avatar is not null");
			return (Criteria) this;
		}

		public Criteria andIdAvatarEqualTo(Integer value) {
			addCriterion("su.id_avatar =", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarNotEqualTo(Integer value) {
			addCriterion("su.id_avatar <>", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarGreaterThan(Integer value) {
			addCriterion("su.id_avatar >", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarGreaterThanOrEqualTo(Integer value) {
			addCriterion("su.id_avatar >=", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarLessThan(Integer value) {
			addCriterion("su.id_avatar <", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarLessThanOrEqualTo(Integer value) {
			addCriterion("su.id_avatar <=", value, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarIn(List<Integer> values) {
			addCriterion("su.id_avatar in", values, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarNotIn(List<Integer> values) {
			addCriterion("su.id_avatar not in", values, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarBetween(Integer value1, Integer value2) {
			addCriterion("su.id_avatar between", value1, value2, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andIdAvatarNotBetween(Integer value1, Integer value2) {
			addCriterion("su.id_avatar not between", value1, value2, "idAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarIsNull() {
			addCriterion("su.ext_avatar is null");
			return (Criteria) this;
		}

		public Criteria andExtAvatarIsNotNull() {
			addCriterion("su.ext_avatar is not null");
			return (Criteria) this;
		}

		public Criteria andExtAvatarEqualTo(String value) {
			addCriterion("su.ext_avatar =", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotEqualTo(String value) {
			addCriterion("su.ext_avatar <>", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarGreaterThan(String value) {
			addCriterion("su.ext_avatar >", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarGreaterThanOrEqualTo(String value) {
			addCriterion("su.ext_avatar >=", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarLessThan(String value) {
			addCriterion("su.ext_avatar <", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarLessThanOrEqualTo(String value) {
			addCriterion("su.ext_avatar <=", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarLike(String value) {
			addCriterion("su.ext_avatar like", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotLike(String value) {
			addCriterion("su.ext_avatar not like", value, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarIn(List<String> values) {
			addCriterion("su.ext_avatar in", values, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotIn(List<String> values) {
			addCriterion("su.ext_avatar not in", values, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarBetween(String value1, String value2) {
			addCriterion("su.ext_avatar between", value1, value2, "extAvatar");
			return (Criteria) this;
		}

		public Criteria andExtAvatarNotBetween(String value1, String value2) {
			addCriterion("su.ext_avatar not between", value1, value2,
					"extAvatar");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table WEBSITEUSER
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 14 22:47:06 ART 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}