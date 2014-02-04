package com.tdil.ljpeugeot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AdviceExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public AdviceExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	protected AdviceExample(AdviceExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ADVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
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

		protected void addCriterionForJDBCDate(String condition, Date value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()), property);
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property + " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("adv.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("adv.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("adv.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("adv.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("adv.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("adv.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("adv.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("adv.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("adv.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("adv.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("adv.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("adv.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdVechicleIsNull() {
			addCriterion("adv.id_vechicle is null");
			return (Criteria) this;
		}

		public Criteria andIdVechicleIsNotNull() {
			addCriterion("adv.id_vechicle is not null");
			return (Criteria) this;
		}

		public Criteria andIdVechicleEqualTo(Integer value) {
			addCriterion("adv.id_vechicle =", value, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleNotEqualTo(Integer value) {
			addCriterion("adv.id_vechicle <>", value, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleGreaterThan(Integer value) {
			addCriterion("adv.id_vechicle >", value, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleGreaterThanOrEqualTo(Integer value) {
			addCriterion("adv.id_vechicle >=", value, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleLessThan(Integer value) {
			addCriterion("adv.id_vechicle <", value, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleLessThanOrEqualTo(Integer value) {
			addCriterion("adv.id_vechicle <=", value, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleIn(List<Integer> values) {
			addCriterion("adv.id_vechicle in", values, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleNotIn(List<Integer> values) {
			addCriterion("adv.id_vechicle not in", values, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleBetween(Integer value1, Integer value2) {
			addCriterion("adv.id_vechicle between", value1, value2, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andIdVechicleNotBetween(Integer value1, Integer value2) {
			addCriterion("adv.id_vechicle not between", value1, value2, "idVechicle");
			return (Criteria) this;
		}

		public Criteria andKmIsNull() {
			addCriterion("adv.km is null");
			return (Criteria) this;
		}

		public Criteria andKmIsNotNull() {
			addCriterion("adv.km is not null");
			return (Criteria) this;
		}

		public Criteria andKmEqualTo(Integer value) {
			addCriterion("adv.km =", value, "km");
			return (Criteria) this;
		}

		public Criteria andKmNotEqualTo(Integer value) {
			addCriterion("adv.km <>", value, "km");
			return (Criteria) this;
		}

		public Criteria andKmGreaterThan(Integer value) {
			addCriterion("adv.km >", value, "km");
			return (Criteria) this;
		}

		public Criteria andKmGreaterThanOrEqualTo(Integer value) {
			addCriterion("adv.km >=", value, "km");
			return (Criteria) this;
		}

		public Criteria andKmLessThan(Integer value) {
			addCriterion("adv.km <", value, "km");
			return (Criteria) this;
		}

		public Criteria andKmLessThanOrEqualTo(Integer value) {
			addCriterion("adv.km <=", value, "km");
			return (Criteria) this;
		}

		public Criteria andKmIn(List<Integer> values) {
			addCriterion("adv.km in", values, "km");
			return (Criteria) this;
		}

		public Criteria andKmNotIn(List<Integer> values) {
			addCriterion("adv.km not in", values, "km");
			return (Criteria) this;
		}

		public Criteria andKmBetween(Integer value1, Integer value2) {
			addCriterion("adv.km between", value1, value2, "km");
			return (Criteria) this;
		}

		public Criteria andKmNotBetween(Integer value1, Integer value2) {
			addCriterion("adv.km not between", value1, value2, "km");
			return (Criteria) this;
		}

		public Criteria andAdvisedateIsNull() {
			addCriterion("adv.adviseDate is null");
			return (Criteria) this;
		}

		public Criteria andAdvisedateIsNotNull() {
			addCriterion("adv.adviseDate is not null");
			return (Criteria) this;
		}

		public Criteria andAdvisedateEqualTo(Date value) {
			addCriterionForJDBCDate("adv.adviseDate =", value, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateNotEqualTo(Date value) {
			addCriterionForJDBCDate("adv.adviseDate <>", value, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateGreaterThan(Date value) {
			addCriterionForJDBCDate("adv.adviseDate >", value, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("adv.adviseDate >=", value, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateLessThan(Date value) {
			addCriterionForJDBCDate("adv.adviseDate <", value, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("adv.adviseDate <=", value, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateIn(List<Date> values) {
			addCriterionForJDBCDate("adv.adviseDate in", values, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateNotIn(List<Date> values) {
			addCriterionForJDBCDate("adv.adviseDate not in", values, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("adv.adviseDate between", value1, value2, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisedateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("adv.adviseDate not between", value1, value2, "advisedate");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberIsNull() {
			addCriterion("adv.adviseNumber is null");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberIsNotNull() {
			addCriterion("adv.adviseNumber is not null");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberEqualTo(Integer value) {
			addCriterion("adv.adviseNumber =", value, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberNotEqualTo(Integer value) {
			addCriterion("adv.adviseNumber <>", value, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberGreaterThan(Integer value) {
			addCriterion("adv.adviseNumber >", value, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("adv.adviseNumber >=", value, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberLessThan(Integer value) {
			addCriterion("adv.adviseNumber <", value, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberLessThanOrEqualTo(Integer value) {
			addCriterion("adv.adviseNumber <=", value, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberIn(List<Integer> values) {
			addCriterion("adv.adviseNumber in", values, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberNotIn(List<Integer> values) {
			addCriterion("adv.adviseNumber not in", values, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberBetween(Integer value1, Integer value2) {
			addCriterion("adv.adviseNumber between", value1, value2, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andAdvisenumberNotBetween(Integer value1, Integer value2) {
			addCriterion("adv.adviseNumber not between", value1, value2, "advisenumber");
			return (Criteria) this;
		}

		public Criteria andIsreadIsNull() {
			addCriterion("adv.isread is null");
			return (Criteria) this;
		}

		public Criteria andIsreadIsNotNull() {
			addCriterion("adv.isread is not null");
			return (Criteria) this;
		}

		public Criteria andIsreadEqualTo(Integer value) {
			addCriterion("adv.isread =", value, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadNotEqualTo(Integer value) {
			addCriterion("adv.isread <>", value, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadGreaterThan(Integer value) {
			addCriterion("adv.isread >", value, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadGreaterThanOrEqualTo(Integer value) {
			addCriterion("adv.isread >=", value, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadLessThan(Integer value) {
			addCriterion("adv.isread <", value, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadLessThanOrEqualTo(Integer value) {
			addCriterion("adv.isread <=", value, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadIn(List<Integer> values) {
			addCriterion("adv.isread in", values, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadNotIn(List<Integer> values) {
			addCriterion("adv.isread not in", values, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadBetween(Integer value1, Integer value2) {
			addCriterion("adv.isread between", value1, value2, "isread");
			return (Criteria) this;
		}

		public Criteria andIsreadNotBetween(Integer value1, Integer value2) {
			addCriterion("adv.isread not between", value1, value2, "isread");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("adv.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("adv.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("adv.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("adv.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("adv.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("adv.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("adv.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("adv.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("adv.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("adv.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("adv.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("adv.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ADVICE
     *
     * @mbggenerated do_not_delete_during_merge Fri Jan 31 08:03:00 ART 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}