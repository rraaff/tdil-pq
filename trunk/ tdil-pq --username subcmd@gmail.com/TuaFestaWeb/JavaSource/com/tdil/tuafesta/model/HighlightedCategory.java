package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class HighlightedCategory extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_CAT.type
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_CAT.id_prod_serv_cat
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private Integer idProdServCat;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_CAT.fromDate
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private Date fromdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column HIGHLIGHTED_CAT.toDate
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private Date todate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_CAT.type
	 * @return  the value of HIGHLIGHTED_CAT.type
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_CAT.type
	 * @param type  the value for HIGHLIGHTED_CAT.type
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_CAT.id_prod_serv_cat
	 * @return  the value of HIGHLIGHTED_CAT.id_prod_serv_cat
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer getIdProdServCat() {
		return idProdServCat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_CAT.id_prod_serv_cat
	 * @param idProdServCat  the value for HIGHLIGHTED_CAT.id_prod_serv_cat
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public void setIdProdServCat(Integer idProdServCat) {
		this.idProdServCat = idProdServCat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_CAT.fromDate
	 * @return  the value of HIGHLIGHTED_CAT.fromDate
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Date getFromdate() {
		return fromdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_CAT.fromDate
	 * @param fromdate  the value for HIGHLIGHTED_CAT.fromDate
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column HIGHLIGHTED_CAT.toDate
	 * @return  the value of HIGHLIGHTED_CAT.toDate
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Date getTodate() {
		return todate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column HIGHLIGHTED_CAT.toDate
	 * @param todate  the value for HIGHLIGHTED_CAT.toDate
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public void setTodate(Date todate) {
		this.todate = todate;
	}
}