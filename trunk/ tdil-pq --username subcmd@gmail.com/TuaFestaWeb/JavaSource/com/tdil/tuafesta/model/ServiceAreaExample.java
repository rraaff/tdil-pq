package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceAreaExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public ServiceAreaExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	protected ServiceAreaExample(ServiceAreaExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 16 11:16:49 ART 2012
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
			addCriterion("sa.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("sa.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("sa.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("sa.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("sa.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("sa.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("sa.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("sa.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("sa.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("sa.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("sa.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("sa.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNull() {
			addCriterion("sa.id_profesional is null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNotNull() {
			addCriterion("sa.id_profesional is not null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalEqualTo(Integer value) {
			addCriterion("sa.id_profesional =", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotEqualTo(Integer value) {
			addCriterion("sa.id_profesional <>", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThan(Integer value) {
			addCriterion("sa.id_profesional >", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThanOrEqualTo(Integer value) {
			addCriterion("sa.id_profesional >=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThan(Integer value) {
			addCriterion("sa.id_profesional <", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThanOrEqualTo(Integer value) {
			addCriterion("sa.id_profesional <=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIn(List<Integer> values) {
			addCriterion("sa.id_profesional in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotIn(List<Integer> values) {
			addCriterion("sa.id_profesional not in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalBetween(Integer value1, Integer value2) {
			addCriterion("sa.id_profesional between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotBetween(Integer value1, Integer value2) {
			addCriterion("sa.id_profesional not between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("sa.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("sa.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("sa.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("sa.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("sa.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("sa.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("sa.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("sa.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("sa.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("sa.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("sa.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("sa.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andLevelIsNull() {
			addCriterion("sa.level is null");
			return (Criteria) this;
		}

		public Criteria andLevelIsNotNull() {
			addCriterion("sa.level is not null");
			return (Criteria) this;
		}

		public Criteria andLevelEqualTo(Integer value) {
			addCriterion("sa.level =", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotEqualTo(Integer value) {
			addCriterion("sa.level <>", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThan(Integer value) {
			addCriterion("sa.level >", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("sa.level >=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThan(Integer value) {
			addCriterion("sa.level <", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThanOrEqualTo(Integer value) {
			addCriterion("sa.level <=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelIn(List<Integer> values) {
			addCriterion("sa.level in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotIn(List<Integer> values) {
			addCriterion("sa.level not in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelBetween(Integer value1, Integer value2) {
			addCriterion("sa.level between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotBetween(Integer value1, Integer value2) {
			addCriterion("sa.level not between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelIsNull() {
			addCriterion("sa.id_geolevel is null");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelIsNotNull() {
			addCriterion("sa.id_geolevel is not null");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelEqualTo(Integer value) {
			addCriterion("sa.id_geolevel =", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelNotEqualTo(Integer value) {
			addCriterion("sa.id_geolevel <>", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelGreaterThan(Integer value) {
			addCriterion("sa.id_geolevel >", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("sa.id_geolevel >=", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelLessThan(Integer value) {
			addCriterion("sa.id_geolevel <", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelLessThanOrEqualTo(Integer value) {
			addCriterion("sa.id_geolevel <=", value, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelIn(List<Integer> values) {
			addCriterion("sa.id_geolevel in", values, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelNotIn(List<Integer> values) {
			addCriterion("sa.id_geolevel not in", values, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelBetween(Integer value1, Integer value2) {
			addCriterion("sa.id_geolevel between", value1, value2, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andIdGeolevelNotBetween(Integer value1, Integer value2) {
			addCriterion("sa.id_geolevel not between", value1, value2, "idGeolevel");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("sa.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("sa.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("sa.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("sa.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("sa.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("sa.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("sa.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("sa.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("sa.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("sa.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("sa.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("sa.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SERVICE_AREA
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 11 00:20:09 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}