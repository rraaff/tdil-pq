package com.tdil.lojack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightConfExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public LightConfExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    protected LightConfExample(LightConfExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
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
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
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
            addCriterion("lc.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("lc.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("lc.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("lc.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("lc.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lc.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("lc.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("lc.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("lc.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("lc.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("lc.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lc.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdentidadIsNull() {
            addCriterion("lc.idEntidad is null");
            return (Criteria) this;
        }

        public Criteria andIdentidadIsNotNull() {
            addCriterion("lc.idEntidad is not null");
            return (Criteria) this;
        }

        public Criteria andIdentidadEqualTo(Integer value) {
            addCriterion("lc.idEntidad =", value, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadNotEqualTo(Integer value) {
            addCriterion("lc.idEntidad <>", value, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadGreaterThan(Integer value) {
            addCriterion("lc.idEntidad >", value, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadGreaterThanOrEqualTo(Integer value) {
            addCriterion("lc.idEntidad >=", value, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadLessThan(Integer value) {
            addCriterion("lc.idEntidad <", value, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadLessThanOrEqualTo(Integer value) {
            addCriterion("lc.idEntidad <=", value, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadIn(List<Integer> values) {
            addCriterion("lc.idEntidad in", values, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadNotIn(List<Integer> values) {
            addCriterion("lc.idEntidad not in", values, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadBetween(Integer value1, Integer value2) {
            addCriterion("lc.idEntidad between", value1, value2, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdentidadNotBetween(Integer value1, Integer value2) {
            addCriterion("lc.idEntidad not between", value1, value2, "identidad");
            return (Criteria) this;
        }

        public Criteria andIdluzIsNull() {
            addCriterion("lc.idLuz is null");
            return (Criteria) this;
        }

        public Criteria andIdluzIsNotNull() {
            addCriterion("lc.idLuz is not null");
            return (Criteria) this;
        }

        public Criteria andIdluzEqualTo(Integer value) {
            addCriterion("lc.idLuz =", value, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzNotEqualTo(Integer value) {
            addCriterion("lc.idLuz <>", value, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzGreaterThan(Integer value) {
            addCriterion("lc.idLuz >", value, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzGreaterThanOrEqualTo(Integer value) {
            addCriterion("lc.idLuz >=", value, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzLessThan(Integer value) {
            addCriterion("lc.idLuz <", value, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzLessThanOrEqualTo(Integer value) {
            addCriterion("lc.idLuz <=", value, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzIn(List<Integer> values) {
            addCriterion("lc.idLuz in", values, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzNotIn(List<Integer> values) {
            addCriterion("lc.idLuz not in", values, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzBetween(Integer value1, Integer value2) {
            addCriterion("lc.idLuz between", value1, value2, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdluzNotBetween(Integer value1, Integer value2) {
            addCriterion("lc.idLuz not between", value1, value2, "idluz");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserIsNull() {
            addCriterion("lc.idWebsiteUser is null");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserIsNotNull() {
            addCriterion("lc.idWebsiteUser is not null");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserEqualTo(Integer value) {
            addCriterion("lc.idWebsiteUser =", value, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserNotEqualTo(Integer value) {
            addCriterion("lc.idWebsiteUser <>", value, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserGreaterThan(Integer value) {
            addCriterion("lc.idWebsiteUser >", value, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserGreaterThanOrEqualTo(Integer value) {
            addCriterion("lc.idWebsiteUser >=", value, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserLessThan(Integer value) {
            addCriterion("lc.idWebsiteUser <", value, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserLessThanOrEqualTo(Integer value) {
            addCriterion("lc.idWebsiteUser <=", value, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserIn(List<Integer> values) {
            addCriterion("lc.idWebsiteUser in", values, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserNotIn(List<Integer> values) {
            addCriterion("lc.idWebsiteUser not in", values, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserBetween(Integer value1, Integer value2) {
            addCriterion("lc.idWebsiteUser between", value1, value2, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andIdwebsiteuserNotBetween(Integer value1, Integer value2) {
            addCriterion("lc.idWebsiteUser not between", value1, value2, "idwebsiteuser");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("lc.description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("lc.description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("lc.description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("lc.description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("lc.description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("lc.description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("lc.description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("lc.description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("lc.description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("lc.description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("lc.description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("lc.description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("lc.description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("lc.description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationIsNull() {
            addCriterion("lc.emailNotification is null");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationIsNotNull() {
            addCriterion("lc.emailNotification is not null");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationEqualTo(Integer value) {
            addCriterion("lc.emailNotification =", value, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationNotEqualTo(Integer value) {
            addCriterion("lc.emailNotification <>", value, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationGreaterThan(Integer value) {
            addCriterion("lc.emailNotification >", value, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationGreaterThanOrEqualTo(Integer value) {
            addCriterion("lc.emailNotification >=", value, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationLessThan(Integer value) {
            addCriterion("lc.emailNotification <", value, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationLessThanOrEqualTo(Integer value) {
            addCriterion("lc.emailNotification <=", value, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationIn(List<Integer> values) {
            addCriterion("lc.emailNotification in", values, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationNotIn(List<Integer> values) {
            addCriterion("lc.emailNotification not in", values, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationBetween(Integer value1, Integer value2) {
            addCriterion("lc.emailNotification between", value1, value2, "emailnotification");
            return (Criteria) this;
        }

        public Criteria andEmailnotificationNotBetween(Integer value1, Integer value2) {
            addCriterion("lc.emailNotification not between", value1, value2, "emailnotification");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LIGHT_CONF
     *
     * @mbggenerated do_not_delete_during_merge Thu May 02 23:36:09 ART 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}