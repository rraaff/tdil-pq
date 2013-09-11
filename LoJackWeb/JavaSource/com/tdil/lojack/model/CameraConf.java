package com.tdil.lojack.model;

import com.tdil.ibatis.PersistentObject;

public class CameraConf extends PersistentObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMERA_CONF.url
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMERA_CONF.idWebsiteUser
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    private Integer idwebsiteuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CAMERA_CONF.description
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMERA_CONF.url
     *
     * @return the value of CAMERA_CONF.url
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMERA_CONF.url
     *
     * @param url the value for CAMERA_CONF.url
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMERA_CONF.idWebsiteUser
     *
     * @return the value of CAMERA_CONF.idWebsiteUser
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    public Integer getIdwebsiteuser() {
        return idwebsiteuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMERA_CONF.idWebsiteUser
     *
     * @param idwebsiteuser the value for CAMERA_CONF.idWebsiteUser
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    public void setIdwebsiteuser(Integer idwebsiteuser) {
        this.idwebsiteuser = idwebsiteuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CAMERA_CONF.description
     *
     * @return the value of CAMERA_CONF.description
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CAMERA_CONF.description
     *
     * @param description the value for CAMERA_CONF.description
     *
     * @mbggenerated Tue Sep 10 22:37:56 ART 2013
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}