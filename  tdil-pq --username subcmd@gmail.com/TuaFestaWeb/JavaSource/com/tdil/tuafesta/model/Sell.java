package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.math.BigDecimal;

public class Sell extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL.id_profesional
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer idProfesional;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL.type
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL.id_prod_serv
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer idProdServ;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL.item
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private String item;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL.referenceprice
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private BigDecimal referenceprice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column SELL.approved
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	private Integer approved;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL.id_profesional
	 * @return  the value of SELL.id_profesional
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getIdProfesional() {
		return idProfesional;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL.id_profesional
	 * @param idProfesional  the value for SELL.id_profesional
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setIdProfesional(Integer idProfesional) {
		this.idProfesional = idProfesional;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL.type
	 * @return  the value of SELL.type
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL.type
	 * @param type  the value for SELL.type
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL.id_prod_serv
	 * @return  the value of SELL.id_prod_serv
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getIdProdServ() {
		return idProdServ;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL.id_prod_serv
	 * @param idProdServ  the value for SELL.id_prod_serv
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setIdProdServ(Integer idProdServ) {
		this.idProdServ = idProdServ;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL.item
	 * @return  the value of SELL.item
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public String getItem() {
		return item;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL.item
	 * @param item  the value for SELL.item
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setItem(String item) {
		this.item = item == null ? null : item.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL.referenceprice
	 * @return  the value of SELL.referenceprice
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public BigDecimal getReferenceprice() {
		return referenceprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL.referenceprice
	 * @param referenceprice  the value for SELL.referenceprice
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setReferenceprice(BigDecimal referenceprice) {
		this.referenceprice = referenceprice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column SELL.approved
	 * @return  the value of SELL.approved
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public Integer getApproved() {
		return approved;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column SELL.approved
	 * @param approved  the value for SELL.approved
	 * @mbggenerated  Fri Sep 21 21:05:52 ART 2012
	 */
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
}