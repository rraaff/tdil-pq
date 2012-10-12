package com.tdil.tuafesta.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionSellExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public PromotionSellExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	protected PromotionSellExample(PromotionSellExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Wed Oct 10 22:31:02 ART 2012
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
			addCriterion("promse.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("promse.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("promse.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("promse.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("promse.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("promse.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("promse.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("promse.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("promse.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("promse.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("promse.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("promse.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIsNull() {
			addCriterion("promse.id_promotion is null");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIsNotNull() {
			addCriterion("promse.id_promotion is not null");
			return (Criteria) this;
		}

		public Criteria andIdPromotionEqualTo(Integer value) {
			addCriterion("promse.id_promotion =", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotEqualTo(Integer value) {
			addCriterion("promse.id_promotion <>", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionGreaterThan(Integer value) {
			addCriterion("promse.id_promotion >", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionGreaterThanOrEqualTo(Integer value) {
			addCriterion("promse.id_promotion >=", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionLessThan(Integer value) {
			addCriterion("promse.id_promotion <", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionLessThanOrEqualTo(Integer value) {
			addCriterion("promse.id_promotion <=", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIn(List<Integer> values) {
			addCriterion("promse.id_promotion in", values, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotIn(List<Integer> values) {
			addCriterion("promse.id_promotion not in", values, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionBetween(Integer value1, Integer value2) {
			addCriterion("promse.id_promotion between", value1, value2, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotBetween(Integer value1, Integer value2) {
			addCriterion("promse.id_promotion not between", value1, value2, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andReferencepriceIsNull() {
			addCriterion("promse.referenceprice is null");
			return (Criteria) this;
		}

		public Criteria andReferencepriceIsNotNull() {
			addCriterion("promse.referenceprice is not null");
			return (Criteria) this;
		}

		public Criteria andReferencepriceEqualTo(BigDecimal value) {
			addCriterion("promse.referenceprice =", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceNotEqualTo(BigDecimal value) {
			addCriterion("promse.referenceprice <>", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceGreaterThan(BigDecimal value) {
			addCriterion("promse.referenceprice >", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("promse.referenceprice >=", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceLessThan(BigDecimal value) {
			addCriterion("promse.referenceprice <", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("promse.referenceprice <=", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceIn(List<BigDecimal> values) {
			addCriterion("promse.referenceprice in", values, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceNotIn(List<BigDecimal> values) {
			addCriterion("promse.referenceprice not in", values, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("promse.referenceprice between", value1, value2, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("promse.referenceprice not between", value1, value2, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andIdSellIsNull() {
			addCriterion("promse.id_sell is null");
			return (Criteria) this;
		}

		public Criteria andIdSellIsNotNull() {
			addCriterion("promse.id_sell is not null");
			return (Criteria) this;
		}

		public Criteria andIdSellEqualTo(Integer value) {
			addCriterion("promse.id_sell =", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellNotEqualTo(Integer value) {
			addCriterion("promse.id_sell <>", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellGreaterThan(Integer value) {
			addCriterion("promse.id_sell >", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellGreaterThanOrEqualTo(Integer value) {
			addCriterion("promse.id_sell >=", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellLessThan(Integer value) {
			addCriterion("promse.id_sell <", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellLessThanOrEqualTo(Integer value) {
			addCriterion("promse.id_sell <=", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellIn(List<Integer> values) {
			addCriterion("promse.id_sell in", values, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellNotIn(List<Integer> values) {
			addCriterion("promse.id_sell not in", values, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellBetween(Integer value1, Integer value2) {
			addCriterion("promse.id_sell between", value1, value2, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellNotBetween(Integer value1, Integer value2) {
			addCriterion("promse.id_sell not between", value1, value2, "idSell");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("promse.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("promse.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("promse.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("promse.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("promse.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("promse.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("promse.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("promse.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("promse.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("promse.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("promse.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("promse.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROMOTION_SELL
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 20 20:10:42 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}