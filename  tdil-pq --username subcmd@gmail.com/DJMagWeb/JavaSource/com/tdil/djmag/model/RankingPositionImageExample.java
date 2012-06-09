package com.tdil.djmag.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingPositionImageExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public RankingPositionImageExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	protected RankingPositionImageExample(RankingPositionImageExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
		this.distinct = example.distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table RANKING_POS_IMG
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
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

		public Criteria andIdRankingPosIsNull() {
			addCriterion("id_ranking_pos is null");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosIsNotNull() {
			addCriterion("id_ranking_pos is not null");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosEqualTo(Integer value) {
			addCriterion("id_ranking_pos =", value, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosNotEqualTo(Integer value) {
			addCriterion("id_ranking_pos <>", value, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosGreaterThan(Integer value) {
			addCriterion("id_ranking_pos >", value, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosGreaterThanOrEqualTo(Integer value) {
			addCriterion("id_ranking_pos >=", value, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosLessThan(Integer value) {
			addCriterion("id_ranking_pos <", value, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosLessThanOrEqualTo(Integer value) {
			addCriterion("id_ranking_pos <=", value, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosIn(List<Integer> values) {
			addCriterion("id_ranking_pos in", values, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosNotIn(List<Integer> values) {
			addCriterion("id_ranking_pos not in", values, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosBetween(Integer value1, Integer value2) {
			addCriterion("id_ranking_pos between", value1, value2, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andIdRankingPosNotBetween(Integer value1, Integer value2) {
			addCriterion("id_ranking_pos not between", value1, value2, "idRankingPos");
			return (Criteria) this;
		}

		public Criteria andImageextIsNull() {
			addCriterion("imageExt is null");
			return (Criteria) this;
		}

		public Criteria andImageextIsNotNull() {
			addCriterion("imageExt is not null");
			return (Criteria) this;
		}

		public Criteria andImageextEqualTo(String value) {
			addCriterion("imageExt =", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextNotEqualTo(String value) {
			addCriterion("imageExt <>", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextGreaterThan(String value) {
			addCriterion("imageExt >", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextGreaterThanOrEqualTo(String value) {
			addCriterion("imageExt >=", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextLessThan(String value) {
			addCriterion("imageExt <", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextLessThanOrEqualTo(String value) {
			addCriterion("imageExt <=", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextLike(String value) {
			addCriterion("imageExt like", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextNotLike(String value) {
			addCriterion("imageExt not like", value, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextIn(List<String> values) {
			addCriterion("imageExt in", values, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextNotIn(List<String> values) {
			addCriterion("imageExt not in", values, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextBetween(String value1, String value2) {
			addCriterion("imageExt between", value1, value2, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageextNotBetween(String value1, String value2) {
			addCriterion("imageExt not between", value1, value2, "imageext");
			return (Criteria) this;
		}

		public Criteria andImageIdIsNull() {
			addCriterion("image_id is null");
			return (Criteria) this;
		}

		public Criteria andImageIdIsNotNull() {
			addCriterion("image_id is not null");
			return (Criteria) this;
		}

		public Criteria andImageIdEqualTo(Integer value) {
			addCriterion("image_id =", value, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdNotEqualTo(Integer value) {
			addCriterion("image_id <>", value, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdGreaterThan(Integer value) {
			addCriterion("image_id >", value, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("image_id >=", value, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdLessThan(Integer value) {
			addCriterion("image_id <", value, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdLessThanOrEqualTo(Integer value) {
			addCriterion("image_id <=", value, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdIn(List<Integer> values) {
			addCriterion("image_id in", values, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdNotIn(List<Integer> values) {
			addCriterion("image_id not in", values, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdBetween(Integer value1, Integer value2) {
			addCriterion("image_id between", value1, value2, "imageId");
			return (Criteria) this;
		}

		public Criteria andImageIdNotBetween(Integer value1, Integer value2) {
			addCriterion("image_id not between", value1, value2, "imageId");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNull() {
			addCriterion("orderNumber is null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIsNotNull() {
			addCriterion("orderNumber is not null");
			return (Criteria) this;
		}

		public Criteria andOrdernumberEqualTo(Integer value) {
			addCriterion("orderNumber =", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotEqualTo(Integer value) {
			addCriterion("orderNumber <>", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThan(Integer value) {
			addCriterion("orderNumber >", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberGreaterThanOrEqualTo(Integer value) {
			addCriterion("orderNumber >=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThan(Integer value) {
			addCriterion("orderNumber <", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberLessThanOrEqualTo(Integer value) {
			addCriterion("orderNumber <=", value, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberIn(List<Integer> values) {
			addCriterion("orderNumber in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotIn(List<Integer> values) {
			addCriterion("orderNumber not in", values, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberBetween(Integer value1, Integer value2) {
			addCriterion("orderNumber between", value1, value2, "ordernumber");
			return (Criteria) this;
		}

		public Criteria andOrdernumberNotBetween(Integer value1, Integer value2) {
			addCriterion("orderNumber not between", value1, value2, "ordernumber");
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
     * This class corresponds to the database table RANKING_POS_IMG
     *
     * @mbggenerated do_not_delete_during_merge Thu May 17 12:17:02 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}