package com.tdil.djmag.model;

public class Country {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COUNTRY.id
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COUNTRY.name
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COUNTRY.deleted
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    private Integer deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COUNTRY.id
     *
     * @return the value of COUNTRY.id
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COUNTRY.id
     *
     * @param id the value for COUNTRY.id
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COUNTRY.name
     *
     * @return the value of COUNTRY.name
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COUNTRY.name
     *
     * @param name the value for COUNTRY.name
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COUNTRY.deleted
     *
     * @return the value of COUNTRY.deleted
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COUNTRY.deleted
     *
     * @param deleted the value for COUNTRY.deleted
     *
     * @mbggenerated Mon Apr 09 18:53:36 ART 2012
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}