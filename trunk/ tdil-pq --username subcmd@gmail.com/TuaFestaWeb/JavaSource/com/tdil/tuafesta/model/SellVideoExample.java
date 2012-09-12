package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellVideoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public SellVideoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected SellVideoExample(SellVideoExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SELL_VIDEO
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
			addCriterion("sellv.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("sellv.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("sellv.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("sellv.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("sellv.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("sellv.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("sellv.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("sellv.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("sellv.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("sellv.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("sellv.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("sellv.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdSellIsNull() {
			addCriterion("sellv.id_sell is null");
			return (Criteria) this;
		}

		public Criteria andIdSellIsNotNull() {
			addCriterion("sellv.id_sell is not null");
			return (Criteria) this;
		}

		public Criteria andIdSellEqualTo(Integer value) {
			addCriterion("sellv.id_sell =", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellNotEqualTo(Integer value) {
			addCriterion("sellv.id_sell <>", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellGreaterThan(Integer value) {
			addCriterion("sellv.id_sell >", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellGreaterThanOrEqualTo(Integer value) {
			addCriterion("sellv.id_sell >=", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellLessThan(Integer value) {
			addCriterion("sellv.id_sell <", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellLessThanOrEqualTo(Integer value) {
			addCriterion("sellv.id_sell <=", value, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellIn(List<Integer> values) {
			addCriterion("sellv.id_sell in", values, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellNotIn(List<Integer> values) {
			addCriterion("sellv.id_sell not in", values, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellBetween(Integer value1, Integer value2) {
			addCriterion("sellv.id_sell between", value1, value2, "idSell");
			return (Criteria) this;
		}

		public Criteria andIdSellNotBetween(Integer value1, Integer value2) {
			addCriterion("sellv.id_sell not between", value1, value2, "idSell");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNull() {
			addCriterion("sellv.orderNumber is null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNotNull() {
			addCriterion("sellv.orderNumber is not null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberEqualTo(Integer value) {
			addCriterion("sellv.orderNumber =", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotEqualTo(Integer value) {
			addCriterion("sellv.orderNumber <>", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThan(Integer value) {
			addCriterion("sellv.orderNumber >", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("sellv.orderNumber >=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThan(Integer value) {
			addCriterion("sellv.orderNumber <", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThanOrEqualTo(Integer value) {
			addCriterion("sellv.orderNumber <=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIn(List<Integer> values) {
			addCriterion("sellv.orderNumber in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotIn(List<Integer> values) {
			addCriterion("sellv.orderNumber not in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberBetween(Integer value1, Integer value2) {
			addCriterion("sellv.orderNumber between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotBetween(Integer value1, Integer value2) {
			addCriterion("sellv.orderNumber not between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("sellv.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("sellv.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("sellv.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("sellv.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("sellv.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("sellv.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("sellv.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("sellv.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("sellv.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("sellv.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("sellv.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("sellv.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andUrlIsNull() {
			addCriterion("sellv.url is null");
			return (Criteria) this;
		}

		public Criteria andUrlIsNotNull() {
			addCriterion("sellv.url is not null");
			return (Criteria) this;
		}

		public Criteria andUrlEqualTo(String value) {
			addCriterion("sellv.url =", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotEqualTo(String value) {
			addCriterion("sellv.url <>", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThan(String value) {
			addCriterion("sellv.url >", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlGreaterThanOrEqualTo(String value) {
			addCriterion("sellv.url >=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThan(String value) {
			addCriterion("sellv.url <", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLessThanOrEqualTo(String value) {
			addCriterion("sellv.url <=", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlLike(String value) {
			addCriterion("sellv.url like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotLike(String value) {
			addCriterion("sellv.url not like", value, "url");
			return (Criteria) this;
		}

		public Criteria andUrlIn(List<String> values) {
			addCriterion("sellv.url in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotIn(List<String> values) {
			addCriterion("sellv.url not in", values, "url");
			return (Criteria) this;
		}

		public Criteria andUrlBetween(String value1, String value2) {
			addCriterion("sellv.url between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andUrlNotBetween(String value1, String value2) {
			addCriterion("sellv.url not between", value1, value2, "url");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("sellv.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("sellv.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("sellv.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("sellv.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("sellv.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("sellv.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("sellv.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("sellv.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("sellv.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("sellv.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("sellv.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("sellv.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SELL_VIDEO
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 11 00:25:13 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}