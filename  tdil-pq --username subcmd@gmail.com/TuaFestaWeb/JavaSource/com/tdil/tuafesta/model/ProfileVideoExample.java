package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileVideoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public ProfileVideoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected ProfileVideoExample(ProfileVideoExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
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
			addCriterion("pv.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("pv.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("pv.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("pv.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("pv.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("pv.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("pv.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("pv.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("pv.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("pv.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("pv.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("pv.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNull() {
			addCriterion("pv.id_profesional is null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNotNull() {
			addCriterion("pv.id_profesional is not null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalEqualTo(Integer value) {
			addCriterion("pv.id_profesional =", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotEqualTo(Integer value) {
			addCriterion("pv.id_profesional <>", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThan(Integer value) {
			addCriterion("pv.id_profesional >", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThanOrEqualTo(Integer value) {
			addCriterion("pv.id_profesional >=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThan(Integer value) {
			addCriterion("pv.id_profesional <", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThanOrEqualTo(Integer value) {
			addCriterion("pv.id_profesional <=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIn(List<Integer> values) {
			addCriterion("pv.id_profesional in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotIn(List<Integer> values) {
			addCriterion("pv.id_profesional not in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalBetween(Integer value1, Integer value2) {
			addCriterion("pv.id_profesional between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotBetween(Integer value1, Integer value2) {
			addCriterion("pv.id_profesional not between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNull() {
			addCriterion("pv.orderNumber is null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNotNull() {
			addCriterion("pv.orderNumber is not null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberEqualTo(Integer value) {
			addCriterion("pv.orderNumber =", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotEqualTo(Integer value) {
			addCriterion("pv.orderNumber <>", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThan(Integer value) {
			addCriterion("pv.orderNumber >", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("pv.orderNumber >=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThan(Integer value) {
			addCriterion("pv.orderNumber <", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThanOrEqualTo(Integer value) {
			addCriterion("pv.orderNumber <=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIn(List<Integer> values) {
			addCriterion("pv.orderNumber in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotIn(List<Integer> values) {
			addCriterion("pv.orderNumber not in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberBetween(Integer value1, Integer value2) {
			addCriterion("pv.orderNumber between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotBetween(Integer value1, Integer value2) {
			addCriterion("pv.orderNumber not between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("pv.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("pv.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("pv.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("pv.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("pv.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("pv.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("pv.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("pv.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("pv.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("pv.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("pv.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("pv.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andUrlIsNull() {
			addCriterion("pv.url is null");
			return (Criteria) this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("pv.url is not null");
			return (Criteria) this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("pv.url =", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("pv.url <>", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("pv.url >", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("pv.url >=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("pv.url <", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("pv.url <=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("pv.url like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("pv.url not like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("pv.url in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("pv.url not in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("pv.url between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("pv.url not between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("pv.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("pv.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("pv.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("pv.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("pv.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("pv.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("pv.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("pv.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("pv.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("pv.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("pv.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("pv.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROFILE_VIDEO
     *
     * @mbggenerated do_not_delete_during_merge Sun Sep 09 12:21:30 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}