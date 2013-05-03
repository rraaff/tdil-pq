package com.tdil.lojack.model;

import com.tdil.ibatis.PersistentObject;

public class LightConf extends PersistentObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIGHT_CONF.idEntidad
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    private Integer identidad;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIGHT_CONF.idLuz
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    private Integer idluz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIGHT_CONF.idWebsiteUser
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    private Integer idwebsiteuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIGHT_CONF.description
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LIGHT_CONF.emailNotification
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    private Integer emailnotification;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIGHT_CONF.idEntidad
     *
     * @return the value of LIGHT_CONF.idEntidad
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public Integer getIdentidad() {
        return identidad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIGHT_CONF.idEntidad
     *
     * @param identidad the value for LIGHT_CONF.idEntidad
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setIdentidad(Integer identidad) {
        this.identidad = identidad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIGHT_CONF.idLuz
     *
     * @return the value of LIGHT_CONF.idLuz
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public Integer getIdluz() {
        return idluz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIGHT_CONF.idLuz
     *
     * @param idluz the value for LIGHT_CONF.idLuz
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setIdluz(Integer idluz) {
        this.idluz = idluz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIGHT_CONF.idWebsiteUser
     *
     * @return the value of LIGHT_CONF.idWebsiteUser
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public Integer getIdwebsiteuser() {
        return idwebsiteuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIGHT_CONF.idWebsiteUser
     *
     * @param idwebsiteuser the value for LIGHT_CONF.idWebsiteUser
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setIdwebsiteuser(Integer idwebsiteuser) {
        this.idwebsiteuser = idwebsiteuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIGHT_CONF.description
     *
     * @return the value of LIGHT_CONF.description
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIGHT_CONF.description
     *
     * @param description the value for LIGHT_CONF.description
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LIGHT_CONF.emailNotification
     *
     * @return the value of LIGHT_CONF.emailNotification
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public Integer getEmailnotification() {
        return emailnotification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LIGHT_CONF.emailNotification
     *
     * @param emailnotification the value for LIGHT_CONF.emailNotification
     *
     * @mbggenerated Thu May 02 23:36:09 ART 2013
     */
    public void setEmailnotification(Integer emailnotification) {
        this.emailnotification = emailnotification;
    }
}