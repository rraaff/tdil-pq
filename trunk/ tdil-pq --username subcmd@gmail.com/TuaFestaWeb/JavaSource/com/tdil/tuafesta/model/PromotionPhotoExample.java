package com.tdil.tuafesta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionPhotoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public PromotionPhotoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected PromotionPhotoExample(PromotionPhotoExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
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
			addCriterion("promphot.id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("promphot.id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("promphot.id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("promphot.id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("promphot.id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("promphot.id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("promphot.id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("promphot.id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("promphot.id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("promphot.id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("promphot.id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("promphot.id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIsNull() {
			addCriterion("promphot.id_promotion is null");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIsNotNull() {
			addCriterion("promphot.id_promotion is not null");
			return (Criteria) this;
		}

		public Criteria andIdPromotionEqualTo(Integer value) {
			addCriterion("promphot.id_promotion =", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotEqualTo(Integer value) {
			addCriterion("promphot.id_promotion <>", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionGreaterThan(Integer value) {
			addCriterion("promphot.id_promotion >", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionGreaterThanOrEqualTo(Integer value) {
			addCriterion("promphot.id_promotion >=", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionLessThan(Integer value) {
			addCriterion("promphot.id_promotion <", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionLessThanOrEqualTo(Integer value) {
			addCriterion("promphot.id_promotion <=", value, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionIn(List<Integer> values) {
			addCriterion("promphot.id_promotion in", values, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotIn(List<Integer> values) {
			addCriterion("promphot.id_promotion not in", values, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionBetween(Integer value1, Integer value2) {
			addCriterion("promphot.id_promotion between", value1, value2, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andIdPromotionNotBetween(Integer value1, Integer value2) {
			addCriterion("promphot.id_promotion not between", value1, value2, "idPromotion");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNull() {
			addCriterion("promphot.orderNumber is null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNotNull() {
			addCriterion("promphot.orderNumber is not null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberEqualTo(Integer value) {
			addCriterion("promphot.orderNumber =", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotEqualTo(Integer value) {
			addCriterion("promphot.orderNumber <>", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThan(Integer value) {
			addCriterion("promphot.orderNumber >", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("promphot.orderNumber >=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThan(Integer value) {
			addCriterion("promphot.orderNumber <", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThanOrEqualTo(Integer value) {
			addCriterion("promphot.orderNumber <=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIn(List<Integer> values) {
			addCriterion("promphot.orderNumber in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotIn(List<Integer> values) {
			addCriterion("promphot.orderNumber not in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberBetween(Integer value1, Integer value2) {
			addCriterion("promphot.orderNumber between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotBetween(Integer value1, Integer value2) {
			addCriterion("promphot.orderNumber not between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIsNull() {
			addCriterion("promphot.id_blob_data is null");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIsNotNull() {
			addCriterion("promphot.id_blob_data is not null");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataEqualTo(Integer value) {
			addCriterion("promphot.id_blob_data =", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotEqualTo(Integer value) {
			addCriterion("promphot.id_blob_data <>", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataGreaterThan(Integer value) {
			addCriterion("promphot.id_blob_data >", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataGreaterThanOrEqualTo(Integer value) {
			addCriterion("promphot.id_blob_data >=", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataLessThan(Integer value) {
			addCriterion("promphot.id_blob_data <", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataLessThanOrEqualTo(Integer value) {
			addCriterion("promphot.id_blob_data <=", value, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataIn(List<Integer> values) {
			addCriterion("promphot.id_blob_data in", values, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotIn(List<Integer> values) {
			addCriterion("promphot.id_blob_data not in", values, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataBetween(Integer value1, Integer value2) {
			addCriterion("promphot.id_blob_data between", value1, value2, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andIdBlobDataNotBetween(Integer value1, Integer value2) {
			addCriterion("promphot.id_blob_data not between", value1, value2, "idBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIsNull() {
			addCriterion("promphot.ext_blob_data is null");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIsNotNull() {
			addCriterion("promphot.ext_blob_data is not null");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataEqualTo(String value) {
			addCriterion("promphot.ext_blob_data =", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotEqualTo(String value) {
			addCriterion("promphot.ext_blob_data <>", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataGreaterThan(String value) {
			addCriterion("promphot.ext_blob_data >", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataGreaterThanOrEqualTo(String value) {
			addCriterion("promphot.ext_blob_data >=", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLessThan(String value) {
			addCriterion("promphot.ext_blob_data <", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLessThanOrEqualTo(String value) {
			addCriterion("promphot.ext_blob_data <=", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataLike(String value) {
			addCriterion("promphot.ext_blob_data like", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotLike(String value) {
			addCriterion("promphot.ext_blob_data not like", value, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataIn(List<String> values) {
			addCriterion("promphot.ext_blob_data in", values, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotIn(List<String> values) {
			addCriterion("promphot.ext_blob_data not in", values, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataBetween(String value1, String value2) {
			addCriterion("promphot.ext_blob_data between", value1, value2, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andExtBlobDataNotBetween(String value1, String value2) {
			addCriterion("promphot.ext_blob_data not between", value1, value2, "extBlobData");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNull() {
			addCriterion("promphot.deleted is null");
			return (Criteria) this;
		}

		public Criteria andDeletedIsNotNull() {
			addCriterion("promphot.deleted is not null");
			return (Criteria) this;
		}

		public Criteria andDeletedEqualTo(Integer value) {
			addCriterion("promphot.deleted =", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotEqualTo(Integer value) {
			addCriterion("promphot.deleted <>", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThan(Integer value) {
			addCriterion("promphot.deleted >", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
			addCriterion("promphot.deleted >=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThan(Integer value) {
			addCriterion("promphot.deleted <", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedLessThanOrEqualTo(Integer value) {
			addCriterion("promphot.deleted <=", value, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedIn(List<Integer> values) {
			addCriterion("promphot.deleted in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotIn(List<Integer> values) {
			addCriterion("promphot.deleted not in", values, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedBetween(Integer value1, Integer value2) {
			addCriterion("promphot.deleted between", value1, value2, "deleted");
			return (Criteria) this;
		}

		public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
			addCriterion("promphot.deleted not between", value1, value2, "deleted");
			return (Criteria) this;
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROMOTION_PHOTO
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 20 20:10:42 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}