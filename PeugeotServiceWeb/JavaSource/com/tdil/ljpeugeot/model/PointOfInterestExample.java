package com.tdil.ljpeugeot.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointOfInterestExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public PointOfInterestExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected PointOfInterestExample(PointOfInterestExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table POI
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
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
			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0 || criteriaWithListValue.size() > 0
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
			addCriterion("poi.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("poi.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("poi.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("poi.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("poi.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("poi.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("poi.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("poi.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("poi.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("poi.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("poi.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("poi.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("poi.type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("poi.type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("poi.type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("poi.type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("poi.type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("poi.type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("poi.type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("poi.type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("poi.type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("poi.type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("poi.type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("poi.type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andSubtypeIsNull() {
			addCriterion("poi.subtype is null");
			return (Criteria) this;
		}

		public Criteria andSubtypeIsNotNull() {
			addCriterion("poi.subtype is not null");
			return (Criteria) this;
		}

		public Criteria andSubtypeEqualTo(Integer value) {
			addCriterion("poi.subtype =", value, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeNotEqualTo(Integer value) {
			addCriterion("poi.subtype <>", value, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeGreaterThan(Integer value) {
			addCriterion("poi.subtype >", value, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("poi.subtype >=", value, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeLessThan(Integer value) {
			addCriterion("poi.subtype <", value, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeLessThanOrEqualTo(Integer value) {
			addCriterion("poi.subtype <=", value, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeIn(List<Integer> values) {
			addCriterion("poi.subtype in", values, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeNotIn(List<Integer> values) {
			addCriterion("poi.subtype not in", values, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeBetween(Integer value1, Integer value2) {
			addCriterion("poi.subtype between", value1, value2, "subtype");
			return (Criteria) this;
		}

		public Criteria andSubtypeNotBetween(Integer value1, Integer value2) {
			addCriterion("poi.subtype not between", value1, value2, "subtype");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("poi.name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("poi.name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("poi.name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("poi.name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("poi.name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("poi.name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("poi.name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("poi.name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("poi.name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("poi.name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("poi.name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("poi.name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("poi.name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("poi.name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("poi.description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("poi.description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("poi.description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("poi.description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("poi.description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("poi.description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("poi.description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("poi.description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("poi.description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("poi.description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("poi.description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("poi.description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("poi.description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("poi.description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andLatIsNull() {
			addCriterion("poi.lat is null");
			return (Criteria) this;
		}

		public Criteria andLatIsNotNull() {
			addCriterion("poi.lat is not null");
			return (Criteria) this;
		}

		public Criteria andLatEqualTo(BigDecimal value) {
			addCriterion("poi.lat =", value, "lat");
			return (Criteria) this;
		}

		public Criteria andLatNotEqualTo(BigDecimal value) {
			addCriterion("poi.lat <>", value, "lat");
			return (Criteria) this;
		}

		public Criteria andLatGreaterThan(BigDecimal value) {
			addCriterion("poi.lat >", value, "lat");
			return (Criteria) this;
		}

		public Criteria andLatGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("poi.lat >=", value, "lat");
			return (Criteria) this;
		}

		public Criteria andLatLessThan(BigDecimal value) {
			addCriterion("poi.lat <", value, "lat");
			return (Criteria) this;
		}

		public Criteria andLatLessThanOrEqualTo(BigDecimal value) {
			addCriterion("poi.lat <=", value, "lat");
			return (Criteria) this;
		}

		public Criteria andLatIn(List<BigDecimal> values) {
			addCriterion("poi.lat in", values, "lat");
			return (Criteria) this;
		}

		public Criteria andLatNotIn(List<BigDecimal> values) {
			addCriterion("poi.lat not in", values, "lat");
			return (Criteria) this;
		}

		public Criteria andLatBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("poi.lat between", value1, value2, "lat");
			return (Criteria) this;
		}

		public Criteria andLatNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("poi.lat not between", value1, value2, "lat");
			return (Criteria) this;
		}

		public Criteria andLonIsNull() {
			addCriterion("poi.lon is null");
			return (Criteria) this;
		}

		public Criteria andLonIsNotNull() {
			addCriterion("poi.lon is not null");
			return (Criteria) this;
		}

		public Criteria andLonEqualTo(BigDecimal value) {
			addCriterion("poi.lon =", value, "lon");
			return (Criteria) this;
		}

		public Criteria andLonNotEqualTo(BigDecimal value) {
			addCriterion("poi.lon <>", value, "lon");
			return (Criteria) this;
		}

		public Criteria andLonGreaterThan(BigDecimal value) {
			addCriterion("poi.lon >", value, "lon");
			return (Criteria) this;
		}

		public Criteria andLonGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("poi.lon >=", value, "lon");
			return (Criteria) this;
		}

		public Criteria andLonLessThan(BigDecimal value) {
			addCriterion("poi.lon <", value, "lon");
			return (Criteria) this;
		}

		public Criteria andLonLessThanOrEqualTo(BigDecimal value) {
			addCriterion("poi.lon <=", value, "lon");
			return (Criteria) this;
		}

		public Criteria andLonIn(List<BigDecimal> values) {
			addCriterion("poi.lon in", values, "lon");
			return (Criteria) this;
		}

		public Criteria andLonNotIn(List<BigDecimal> values) {
			addCriterion("poi.lon not in", values, "lon");
			return (Criteria) this;
		}

		public Criteria andLonBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("poi.lon between", value1, value2, "lon");
			return (Criteria) this;
		}

		public Criteria andLonNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("poi.lon not between", value1, value2, "lon");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table POI
     *
     * @mbggenerated do_not_delete_during_merge Thu Jan 30 23:53:15 ART 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}