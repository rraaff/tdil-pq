package com.tdil.ljpeugeot.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Advice extends PersistentObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADVICE.id_vechicle
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    private Integer idVechicle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADVICE.km
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    private Integer km;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADVICE.adviseDate
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    private Date advisedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADVICE.adviseNumber
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    private Integer advisenumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADVICE.id_vechicle
     *
     * @return the value of ADVICE.id_vechicle
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public Integer getIdVechicle() {
        return idVechicle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADVICE.id_vechicle
     *
     * @param idVechicle the value for ADVICE.id_vechicle
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public void setIdVechicle(Integer idVechicle) {
        this.idVechicle = idVechicle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADVICE.km
     *
     * @return the value of ADVICE.km
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public Integer getKm() {
        return km;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADVICE.km
     *
     * @param km the value for ADVICE.km
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public void setKm(Integer km) {
        this.km = km;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADVICE.adviseDate
     *
     * @return the value of ADVICE.adviseDate
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public Date getAdvisedate() {
        return advisedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADVICE.adviseDate
     *
     * @param advisedate the value for ADVICE.adviseDate
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public void setAdvisedate(Date advisedate) {
        this.advisedate = advisedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADVICE.adviseNumber
     *
     * @return the value of ADVICE.adviseNumber
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public Integer getAdvisenumber() {
        return advisenumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADVICE.adviseNumber
     *
     * @param advisenumber the value for ADVICE.adviseNumber
     *
     * @mbggenerated Fri Jan 31 08:03:00 ART 2014
     */
    public void setAdvisenumber(Integer advisenumber) {
        this.advisenumber = advisenumber;
    }
}