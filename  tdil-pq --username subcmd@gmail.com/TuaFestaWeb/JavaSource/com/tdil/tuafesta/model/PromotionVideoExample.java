package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionVideoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public PromotionVideoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	protected PromotionVideoExample(PromotionVideoExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROMOTION_VIDEO
	 * @mbggenerated  Sat Sep 08 11:05:56 ART 2012
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
			addCriterion("promvi.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("promvi.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("promvi.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("promvi.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("promvi.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("promvi.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("promvi.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("promvi.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("promvi.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("promvi.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("promvi.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("promvi.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIsNull() {
			addCriterion("promvi.id_promotion is null");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIsNotNull() {
			addCriterion("promvi.id_promotion is not null");
			return (Criteria) this;
		}

		public Criteria andIdPromotionEqualTo(Integer value) {
			addCriterion("promvi.id_promotion =", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotEqualTo(Integer value) {
			addCriterion("promvi.id_promotion <>", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionGreaterThan(Integer value) {
			addCriterion("promvi.id_promotion >", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionGreaterThanOrEqualTo(Integer value) {
			addCriterion("promvi.id_promotion >=", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionLessThan(Integer value) {
			addCriterion("promvi.id_promotion <", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionLessThanOrEqualTo(Integer value) {
			addCriterion("promvi.id_promotion <=", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIn(List<Integer> values) {
			addCriterion("promvi.id_promotion in", values, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotIn(List<Integer> values) {
			addCriterion("promvi.id_promotion not in", values, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionBetween(Integer value1, Integer value2) {
			addCriterion("promvi.id_promotion between", value1, value2, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotBetween(Integer value1, Integer value2) {
			addCriterion("promvi.id_promotion not between", value1, value2, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNull() {
			addCriterion("promvi.orderNumber is null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNotNull() {
			addCriterion("promvi.orderNumber is not null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberEqualTo(Integer value) {
			addCriterion("promvi.orderNumber =", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotEqualTo(Integer value) {
			addCriterion("promvi.orderNumber <>", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThan(Integer value) {
			addCriterion("promvi.orderNumber >", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("promvi.orderNumber >=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThan(Integer value) {
			addCriterion("promvi.orderNumber <", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThanOrEqualTo(Integer value) {
			addCriterion("promvi.orderNumber <=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIn(List<Integer> values) {
			addCriterion("promvi.orderNumber in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotIn(List<Integer> values) {
			addCriterion("promvi.orderNumber not in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberBetween(Integer value1, Integer value2) {
			addCriterion("promvi.orderNumber between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotBetween(Integer value1, Integer value2) {
			addCriterion("promvi.orderNumber not between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("promvi.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("promvi.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("promvi.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("promvi.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("promvi.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("promvi.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("promvi.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("promvi.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("promvi.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("promvi.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("promvi.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("promvi.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andUrlIsNull() {
			addCriterion("promvi.url is null");
			return (Criteria) this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("promvi.url is not null");
			return (Criteria) this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("promvi.url =", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("promvi.url <>", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("promvi.url >", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("promvi.url >=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("promvi.url <", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("promvi.url <=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("promvi.url like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("promvi.url not like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("promvi.url in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("promvi.url not in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("promvi.url between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("promvi.url not between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("promvi.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("promvi.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("promvi.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("promvi.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("promvi.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("promvi.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("promvi.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("promvi.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("promvi.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("promvi.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("promvi.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("promvi.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 20 20:10:42 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}