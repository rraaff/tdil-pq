package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.math.BigDecimal;
import java.util.Date;

public class HighlightedProfesional extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_PROF.id_profesional
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	private Integer idProfesional;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_PROF.fromDate
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	private Date fromdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_PROF.toDate
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	private Date todate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_PROF.payment
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	private BigDecimal payment;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_PROF.id_profesional
	 * @return  the value of HIGHLIGHTED_PROF.id_profesional
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public Integer getIdProfesional() {
		return idProfesional;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_PROF.id_profesional
	 * @param idProfesional  the value for HIGHLIGHTED_PROF.id_profesional
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public void setIdProfesional(Integer idProfesional) {
		this.idProfesional = idProfesional;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_PROF.fromDate
	 * @return  the value of HIGHLIGHTED_PROF.fromDate
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public Date getFromdate() {
		return fromdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_PROF.fromDate
	 * @param fromdate  the value for HIGHLIGHTED_PROF.fromDate
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_PROF.toDate
	 * @return  the value of HIGHLIGHTED_PROF.toDate
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public Date getTodate() {
		return todate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_PROF.toDate
	 * @param todate  the value for HIGHLIGHTED_PROF.toDate
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public void setTodate(Date todate) {
		this.todate = todate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_PROF.payment
	 * @return  the value of HIGHLIGHTED_PROF.payment
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public BigDecimal getPayment() {
		return payment;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_PROF.payment
	 * @param payment  the value for HIGHLIGHTED_PROF.payment
	 * @mbggenerated  Tue Aug 28 18:16:51 ART 2012
	 */
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
}