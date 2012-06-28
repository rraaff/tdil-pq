package com.tdil.milka.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoveHateExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public LoveHateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    protected LoveHateExample(LoveHateExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
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
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
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
            addCriterion("loh.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("loh.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("loh.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("loh.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("loh.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("loh.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("loh.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("loh.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("loh.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("loh.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("loh.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("loh.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreationdateIsNull() {
            addCriterion("loh.creationDate is null");
            return (Criteria) this;
        }

        public Criteria andCreationdateIsNotNull() {
            addCriterion("loh.creationDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreationdateEqualTo(Date value) {
            addCriterion("loh.creationDate =", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotEqualTo(Date value) {
            addCriterion("loh.creationDate <>", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateGreaterThan(Date value) {
            addCriterion("loh.creationDate >", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateGreaterThanOrEqualTo(Date value) {
            addCriterion("loh.creationDate >=", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLessThan(Date value) {
            addCriterion("loh.creationDate <", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLessThanOrEqualTo(Date value) {
            addCriterion("loh.creationDate <=", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateIn(List<Date> values) {
            addCriterion("loh.creationDate in", values, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotIn(List<Date> values) {
            addCriterion("loh.creationDate not in", values, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateBetween(Date value1, Date value2) {
            addCriterion("loh.creationDate between", value1, value2, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotBetween(Date value1, Date value2) {
            addCriterion("loh.creationDate not between", value1, value2, "creationdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateIsNull() {
            addCriterion("loh.publishDate is null");
            return (Criteria) this;
        }

        public Criteria andPublishdateIsNotNull() {
            addCriterion("loh.publishDate is not null");
            return (Criteria) this;
        }

        public Criteria andPublishdateEqualTo(Date value) {
            addCriterion("loh.publishDate =", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateNotEqualTo(Date value) {
            addCriterion("loh.publishDate <>", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateGreaterThan(Date value) {
            addCriterion("loh.publishDate >", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateGreaterThanOrEqualTo(Date value) {
            addCriterion("loh.publishDate >=", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateLessThan(Date value) {
            addCriterion("loh.publishDate <", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateLessThanOrEqualTo(Date value) {
            addCriterion("loh.publishDate <=", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateIn(List<Date> values) {
            addCriterion("loh.publishDate in", values, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateNotIn(List<Date> values) {
            addCriterion("loh.publishDate not in", values, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateBetween(Date value1, Date value2) {
            addCriterion("loh.publishDate between", value1, value2, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateNotBetween(Date value1, Date value2) {
            addCriterion("loh.publishDate not between", value1, value2, "publishdate");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("loh.content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("loh.content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("loh.content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("loh.content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("loh.content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("loh.content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("loh.content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("loh.content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("loh.content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("loh.content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("loh.content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("loh.content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("loh.content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("loh.content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andApprovedIsNull() {
            addCriterion("loh.approved is null");
            return (Criteria) this;
        }

        public Criteria andApprovedIsNotNull() {
            addCriterion("loh.approved is not null");
            return (Criteria) this;
        }

        public Criteria andApprovedEqualTo(Integer value) {
            addCriterion("loh.approved =", value, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedNotEqualTo(Integer value) {
            addCriterion("loh.approved <>", value, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedGreaterThan(Integer value) {
            addCriterion("loh.approved >", value, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedGreaterThanOrEqualTo(Integer value) {
            addCriterion("loh.approved >=", value, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedLessThan(Integer value) {
            addCriterion("loh.approved <", value, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedLessThanOrEqualTo(Integer value) {
            addCriterion("loh.approved <=", value, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedIn(List<Integer> values) {
            addCriterion("loh.approved in", values, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedNotIn(List<Integer> values) {
            addCriterion("loh.approved not in", values, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedBetween(Integer value1, Integer value2) {
            addCriterion("loh.approved between", value1, value2, "approved");
            return (Criteria) this;
        }

        public Criteria andApprovedNotBetween(Integer value1, Integer value2) {
            addCriterion("loh.approved not between", value1, value2, "approved");
            return (Criteria) this;
        }

        public Criteria andLoveIsNull() {
            addCriterion("loh.love is null");
            return (Criteria) this;
        }

        public Criteria andLoveIsNotNull() {
            addCriterion("loh.love is not null");
            return (Criteria) this;
        }

        public Criteria andLoveEqualTo(Integer value) {
            addCriterion("loh.love =", value, "love");
            return (Criteria) this;
        }

        public Criteria andLoveNotEqualTo(Integer value) {
            addCriterion("loh.love <>", value, "love");
            return (Criteria) this;
        }

        public Criteria andLoveGreaterThan(Integer value) {
            addCriterion("loh.love >", value, "love");
            return (Criteria) this;
        }

        public Criteria andLoveGreaterThanOrEqualTo(Integer value) {
            addCriterion("loh.love >=", value, "love");
            return (Criteria) this;
        }

        public Criteria andLoveLessThan(Integer value) {
            addCriterion("loh.love <", value, "love");
            return (Criteria) this;
        }

        public Criteria andLoveLessThanOrEqualTo(Integer value) {
            addCriterion("loh.love <=", value, "love");
            return (Criteria) this;
        }

        public Criteria andLoveIn(List<Integer> values) {
            addCriterion("loh.love in", values, "love");
            return (Criteria) this;
        }

        public Criteria andLoveNotIn(List<Integer> values) {
            addCriterion("loh.love not in", values, "love");
            return (Criteria) this;
        }

        public Criteria andLoveBetween(Integer value1, Integer value2) {
            addCriterion("loh.love between", value1, value2, "love");
            return (Criteria) this;
        }

        public Criteria andLoveNotBetween(Integer value1, Integer value2) {
            addCriterion("loh.love not between", value1, value2, "love");
            return (Criteria) this;
        }

        public Criteria andVotesIsNull() {
            addCriterion("loh.votes is null");
            return (Criteria) this;
        }

        public Criteria andVotesIsNotNull() {
            addCriterion("loh.votes is not null");
            return (Criteria) this;
        }

        public Criteria andVotesEqualTo(Integer value) {
            addCriterion("loh.votes =", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesNotEqualTo(Integer value) {
            addCriterion("loh.votes <>", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesGreaterThan(Integer value) {
            addCriterion("loh.votes >", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesGreaterThanOrEqualTo(Integer value) {
            addCriterion("loh.votes >=", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesLessThan(Integer value) {
            addCriterion("loh.votes <", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesLessThanOrEqualTo(Integer value) {
            addCriterion("loh.votes <=", value, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesIn(List<Integer> values) {
            addCriterion("loh.votes in", values, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesNotIn(List<Integer> values) {
            addCriterion("loh.votes not in", values, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesBetween(Integer value1, Integer value2) {
            addCriterion("loh.votes between", value1, value2, "votes");
            return (Criteria) this;
        }

        public Criteria andVotesNotBetween(Integer value1, Integer value2) {
            addCriterion("loh.votes not between", value1, value2, "votes");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterIsNull() {
            addCriterion("loh.id_click_counter is null");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterIsNotNull() {
            addCriterion("loh.id_click_counter is not null");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterEqualTo(Integer value) {
            addCriterion("loh.id_click_counter =", value, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterNotEqualTo(Integer value) {
            addCriterion("loh.id_click_counter <>", value, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterGreaterThan(Integer value) {
            addCriterion("loh.id_click_counter >", value, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterGreaterThanOrEqualTo(Integer value) {
            addCriterion("loh.id_click_counter >=", value, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterLessThan(Integer value) {
            addCriterion("loh.id_click_counter <", value, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterLessThanOrEqualTo(Integer value) {
            addCriterion("loh.id_click_counter <=", value, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterIn(List<Integer> values) {
            addCriterion("loh.id_click_counter in", values, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterNotIn(List<Integer> values) {
            addCriterion("loh.id_click_counter not in", values, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterBetween(Integer value1, Integer value2) {
            addCriterion("loh.id_click_counter between", value1, value2, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andIdClickCounterNotBetween(Integer value1, Integer value2) {
            addCriterion("loh.id_click_counter not between", value1, value2, "idClickCounter");
            return (Criteria) this;
        }

        public Criteria andUrlLinkIsNull() {
            addCriterion("loh.url_link is null");
            return (Criteria) this;
        }

        public Criteria andUrlLinkIsNotNull() {
            addCriterion("loh.url_link is not null");
            return (Criteria) this;
        }

        public Criteria andUrlLinkEqualTo(String value) {
            addCriterion("loh.url_link =", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotEqualTo(String value) {
            addCriterion("loh.url_link <>", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkGreaterThan(String value) {
            addCriterion("loh.url_link >", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkGreaterThanOrEqualTo(String value) {
            addCriterion("loh.url_link >=", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkLessThan(String value) {
            addCriterion("loh.url_link <", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkLessThanOrEqualTo(String value) {
            addCriterion("loh.url_link <=", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkLike(String value) {
            addCriterion("loh.url_link like", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotLike(String value) {
            addCriterion("loh.url_link not like", value, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkIn(List<String> values) {
            addCriterion("loh.url_link in", values, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotIn(List<String> values) {
            addCriterion("loh.url_link not in", values, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkBetween(String value1, String value2) {
            addCriterion("loh.url_link between", value1, value2, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlLinkNotBetween(String value1, String value2) {
            addCriterion("loh.url_link not between", value1, value2, "urlLink");
            return (Criteria) this;
        }

        public Criteria andUrlTargetIsNull() {
            addCriterion("loh.url_target is null");
            return (Criteria) this;
        }

        public Criteria andUrlTargetIsNotNull() {
            addCriterion("loh.url_target is not null");
            return (Criteria) this;
        }

        public Criteria andUrlTargetEqualTo(String value) {
            addCriterion("loh.url_target =", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetNotEqualTo(String value) {
            addCriterion("loh.url_target <>", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetGreaterThan(String value) {
            addCriterion("loh.url_target >", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetGreaterThanOrEqualTo(String value) {
            addCriterion("loh.url_target >=", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetLessThan(String value) {
            addCriterion("loh.url_target <", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetLessThanOrEqualTo(String value) {
            addCriterion("loh.url_target <=", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetLike(String value) {
            addCriterion("loh.url_target like", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetNotLike(String value) {
            addCriterion("loh.url_target not like", value, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetIn(List<String> values) {
            addCriterion("loh.url_target in", values, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetNotIn(List<String> values) {
            addCriterion("loh.url_target not in", values, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetBetween(String value1, String value2) {
            addCriterion("loh.url_target between", value1, value2, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andUrlTargetNotBetween(String value1, String value2) {
            addCriterion("loh.url_target not between", value1, value2, "urlTarget");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("loh.deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("loh.deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Integer value) {
            addCriterion("loh.deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Integer value) {
            addCriterion("loh.deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Integer value) {
            addCriterion("loh.deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("loh.deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Integer value) {
            addCriterion("loh.deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("loh.deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Integer> values) {
            addCriterion("loh.deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Integer> values) {
            addCriterion("loh.deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Integer value1, Integer value2) {
            addCriterion("loh.deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("loh.deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LOVE_HATE
     *
     * @mbggenerated do_not_delete_during_merge Wed Jun 27 22:57:22 ART 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}