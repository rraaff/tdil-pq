package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public class SellExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public SellExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	protected SellExample(SellExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SELL
	 * @mbggenerated  Sun Aug 26 20:37:47 ART 2012
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
			addCriterion("sell.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("sell.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("sell.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("sell.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("sell.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("sell.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("sell.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("sell.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("sell.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("sell.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("sell.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("sell.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNull() {
			addCriterion("sell.id_profesional is null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIsNotNull() {
			addCriterion("sell.id_profesional is not null");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalEqualTo(Integer value) {
			addCriterion("sell.id_profesional =", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotEqualTo(Integer value) {
			addCriterion("sell.id_profesional <>", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThan(Integer value) {
			addCriterion("sell.id_profesional >", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalGreaterThanOrEqualTo(Integer value) {
			addCriterion("sell.id_profesional >=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThan(Integer value) {
			addCriterion("sell.id_profesional <", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalLessThanOrEqualTo(Integer value) {
			addCriterion("sell.id_profesional <=", value, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalIn(List<Integer> values) {
			addCriterion("sell.id_profesional in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotIn(List<Integer> values) {
			addCriterion("sell.id_profesional not in", values, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalBetween(Integer value1, Integer value2) {
			addCriterion("sell.id_profesional between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andIdProfesionalNotBetween(Integer value1, Integer value2) {
			addCriterion("sell.id_profesional not between", value1, value2, "idProfesional");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("sell.type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("sell.type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("sell.type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("sell.type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("sell.type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("sell.type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("sell.type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("sell.type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("sell.type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("sell.type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("sell.type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("sell.type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andIdProdServIsNull() {
			addCriterion("sell.id_prod_serv is null");
			return (Criteria) this;
		}

		public Criteria andIdProdServIsNotNull() {
			addCriterion("sell.id_prod_serv is not null");
			return (Criteria) this;
		}

		public Criteria andIdProdServEqualTo(Integer value) {
			addCriterion("sell.id_prod_serv =", value, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServNotEqualTo(Integer value) {
			addCriterion("sell.id_prod_serv <>", value, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServGreaterThan(Integer value) {
			addCriterion("sell.id_prod_serv >", value, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServGreaterThanOrEqualTo(Integer value) {
			addCriterion("sell.id_prod_serv >=", value, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServLessThan(Integer value) {
			addCriterion("sell.id_prod_serv <", value, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServLessThanOrEqualTo(Integer value) {
			addCriterion("sell.id_prod_serv <=", value, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServIn(List<Integer> values) {
			addCriterion("sell.id_prod_serv in", values, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServNotIn(List<Integer> values) {
			addCriterion("sell.id_prod_serv not in", values, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServBetween(Integer value1, Integer value2) {
			addCriterion("sell.id_prod_serv between", value1, value2, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andIdProdServNotBetween(Integer value1, Integer value2) {
			addCriterion("sell.id_prod_serv not between", value1, value2, "idProdServ");
			return (Criteria) this;
		}

		public Criteria andItemIsNull() {
			addCriterion("sell.item is null");
			return (Criteria) this;
		}

		public Criteria andItemIsNotNull() {
			addCriterion("sell.item is not null");
			return (Criteria) this;
		}

		public Criteria andItemEqualTo(String value) {
			addCriterion("sell.item =", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemNotEqualTo(String value) {
			addCriterion("sell.item <>", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemGreaterThan(String value) {
			addCriterion("sell.item >", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemGreaterThanOrEqualTo(String value) {
			addCriterion("sell.item >=", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemLessThan(String value) {
			addCriterion("sell.item <", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemLessThanOrEqualTo(String value) {
			addCriterion("sell.item <=", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemLike(String value) {
			addCriterion("sell.item like", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemNotLike(String value) {
			addCriterion("sell.item not like", value, "item");
			return (Criteria) this;
		}

		public Criteria andItemIn(List<String> values) {
			addCriterion("sell.item in", values, "item");
			return (Criteria) this;
		}

		public Criteria andItemNotIn(List<String> values) {
			addCriterion("sell.item not in", values, "item");
			return (Criteria) this;
		}

		public Criteria andItemBetween(String value1, String value2) {
			addCriterion("sell.item between", value1, value2, "item");
			return (Criteria) this;
		}

		public Criteria andItemNotBetween(String value1, String value2) {
			addCriterion("sell.item not between", value1, value2, "item");
			return (Criteria) this;
		}

		public Criteria andReferencepriceIsNull() {
			addCriterion("sell.referenceprice is null");
			return (Criteria) this;
		}

		public Criteria andReferencepriceIsNotNull() {
			addCriterion("sell.referenceprice is not null");
			return (Criteria) this;
		}

		public Criteria andReferencepriceEqualTo(BigDecimal value) {
			addCriterion("sell.referenceprice =", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceNotEqualTo(BigDecimal value) {
			addCriterion("sell.referenceprice <>", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceGreaterThan(BigDecimal value) {
			addCriterion("sell.referenceprice >", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("sell.referenceprice >=", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceLessThan(BigDecimal value) {
			addCriterion("sell.referenceprice <", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("sell.referenceprice <=", value, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceIn(List<BigDecimal> values) {
			addCriterion("sell.referenceprice in", values, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceNotIn(List<BigDecimal> values) {
			addCriterion("sell.referenceprice not in", values, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sell.referenceprice between", value1, value2, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andReferencepriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sell.referenceprice not between", value1, value2, "referenceprice");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNull() {
			addCriterion("sell.approved is null");
			return (Criteria) this;
		}

		public Criteria andApprovedIsNotNull() {
			addCriterion("sell.approved is not null");
			return (Criteria) this;
		}

		public Criteria andApprovedEqualTo(Integer value) {
			addCriterion("sell.approved =", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotEqualTo(Integer value) {
			addCriterion("sell.approved <>", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThan(Integer value) {
			addCriterion("sell.approved >", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
			addCriterion("sell.approved >=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThan(Integer value) {
			addCriterion("sell.approved <", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedLessThanOrEqualTo(Integer value) {
			addCriterion("sell.approved <=", value, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedIn(List<Integer> values) {
			addCriterion("sell.approved in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotIn(List<Integer> values) {
			addCriterion("sell.approved not in", values, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedBetween(Integer value1, Integer value2) {
			addCriterion("sell.approved between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
			addCriterion("sell.approved not between", value1, value2, "approved");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("sell.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("sell.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("sell.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("sell.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("sell.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("sell.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("sell.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("sell.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("sell.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("sell.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("sell.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("sell.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SELL
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 11 00:20:09 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}