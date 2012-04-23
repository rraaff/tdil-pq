package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;
import java.util.Date;

public class Note extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.summary
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String summary;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.id_section
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer idSection;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.web_title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String webTitle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.from_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Date fromDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.to_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Date toDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.frontCover
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer frontcover;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.popular
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer popular;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.showInAgenda
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Integer showinagenda;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.agenda_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private Date agendaDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column NOTE.content
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private String content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.title
	 * @return  the value of NOTE.title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.title
	 * @param title  the value for NOTE.title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.summary
	 * @return  the value of NOTE.summary
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.summary
	 * @param summary  the value for NOTE.summary
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.id_section
	 * @return  the value of NOTE.id_section
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getIdSection() {
		return idSection;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.id_section
	 * @param idSection  the value for NOTE.id_section
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setIdSection(Integer idSection) {
		this.idSection = idSection;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.web_title
	 * @return  the value of NOTE.web_title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getWebTitle() {
		return webTitle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.web_title
	 * @param webTitle  the value for NOTE.web_title
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle == null ? null : webTitle.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.from_date
	 * @return  the value of NOTE.from_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.from_date
	 * @param fromDate  the value for NOTE.from_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.to_date
	 * @return  the value of NOTE.to_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.to_date
	 * @param toDate  the value for NOTE.to_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.frontCover
	 * @return  the value of NOTE.frontCover
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getFrontcover() {
		return frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.frontCover
	 * @param frontcover  the value for NOTE.frontCover
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setFrontcover(Integer frontcover) {
		this.frontcover = frontcover;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.popular
	 * @return  the value of NOTE.popular
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getPopular() {
		return popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.popular
	 * @param popular  the value for NOTE.popular
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setPopular(Integer popular) {
		this.popular = popular;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.showInAgenda
	 * @return  the value of NOTE.showInAgenda
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer getShowinagenda() {
		return showinagenda;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.showInAgenda
	 * @param showinagenda  the value for NOTE.showInAgenda
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setShowinagenda(Integer showinagenda) {
		this.showinagenda = showinagenda;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.agenda_date
	 * @return  the value of NOTE.agenda_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Date getAgendaDate() {
		return agendaDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.agenda_date
	 * @param agendaDate  the value for NOTE.agenda_date
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setAgendaDate(Date agendaDate) {
		this.agendaDate = agendaDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column NOTE.content
	 * @return  the value of NOTE.content
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column NOTE.content
	 * @param content  the value for NOTE.content
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}