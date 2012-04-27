package com.tdil.djmag.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerPositionExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public BannerPositionExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	protected BannerPositionExample(BannerPositionExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Thu Apr 26 21:07:37 ART 2012
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

		public Criteria andIdCountryIsNull() {
			addCriterion("id_country is null");
			return (Criteria) this;
		}

		public Criteria andIdCountryIsNotNull() {
			addCriterion("id_country is not null");
			return (Criteria) this;
		}

		public Criteria andIdCountryEqualTo(Integer value) {
			addCriterion("id_country =", value, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryNotEqualTo(Integer value) {
			addCriterion("id_country <>", value, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryGreaterThan(Integer value) {
			addCriterion("id_country >", value, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_country >=", value, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryLessThan(Integer value) {
			addCriterion("id_country <", value, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryLessThanOrEqualTo(Integer value) {
			addCriterion("id_country <=", value, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryIn(List<Integer> values) {
			addCriterion("id_country in", values, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryNotIn(List<Integer> values) {
			addCriterion("id_country not in", values, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryBetween(Integer value1, Integer value2) {
			addCriterion("id_country between", value1, value2, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdCountryNotBetween(Integer value1, Integer value2) {
			addCriterion("id_country not between", value1, value2, "idCountry");
			return (Criteria) this;
		}

		public Criteria andIdBannerIsNull() {
			addCriterion("id_banner is null");
			return (Criteria) this;
		}

		public Criteria andIdBannerIsNotNull() {
			addCriterion("id_banner is not null");
			return (Criteria) this;
		}

		public Criteria andIdBannerEqualTo(Integer value) {
			addCriterion("id_banner =", value, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerNotEqualTo(Integer value) {
			addCriterion("id_banner <>", value, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerGreaterThan(Integer value) {
			addCriterion("id_banner >", value, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_banner >=", value, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerLessThan(Integer value) {
			addCriterion("id_banner <", value, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerLessThanOrEqualTo(Integer value) {
			addCriterion("id_banner <=", value, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerIn(List<Integer> values) {
			addCriterion("id_banner in", values, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerNotIn(List<Integer> values) {
			addCriterion("id_banner not in", values, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerBetween(Integer value1, Integer value2) {
			addCriterion("id_banner between", value1, value2, "idBanner");
			return (Criteria) this;
		}

		public Criteria andIdBannerNotBetween(Integer value1, Integer value2) {
			addCriterion("id_banner not between", value1, value2, "idBanner");
			return (Criteria) this;
		}

		public Criteria andPositionIsNull() {
			addCriterion("position is null");
			return (Criteria) this;
		}

		public Criteria andPositionIsNotNull() {
			addCriterion("position is not null");
			return (Criteria) this;
		}

		public Criteria andPositionEqualTo(String value) {
			addCriterion("position =", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotEqualTo(String value) {
			addCriterion("position <>", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionGreaterThan(String value) {
			addCriterion("position >", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionGreaterThanOrEqualTo(String value) {
			addCriterion("position >=", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionLessThan(String value) {
			addCriterion("position <", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionLessThanOrEqualTo(String value) {
			addCriterion("position <=", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionLike(String value) {
			addCriterion("position like", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotLike(String value) {
			addCriterion("position not like", value, "position");
			return (Criteria) this;
		}

		public Criteria andPositionIn(List<String> values) {
			addCriterion("position in", values, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotIn(List<String> values) {
			addCriterion("position not in", values, "position");
			return (Criteria) this;
		}

		public Criteria andPositionBetween(String value1, String value2) {
			addCriterion("position between", value1, value2, "position");
			return (Criteria) this;
		}

		public Criteria andPositionNotBetween(String value1, String value2) {
			addCriterion("position not between", value1, value2, "position");
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
     * This class corresponds to the database table BANNER_POSITION
     *
     * @mbggenerated do_not_delete_during_merge Wed Apr 25 07:29:57 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}