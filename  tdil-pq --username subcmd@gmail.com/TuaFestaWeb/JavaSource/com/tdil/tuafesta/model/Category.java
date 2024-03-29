package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class Category extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.name
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.description
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.parent_id
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer parentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.type
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.showInHome
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer showinhome;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.homeIndex
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer homeindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column CATEGORY.isother
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private Integer isother;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.name
	 * @return  the value of CATEGORY.name
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.name
	 * @param name  the value for CATEGORY.name
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.description
	 * @return  the value of CATEGORY.description
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.description
	 * @param description  the value for CATEGORY.description
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.parent_id
	 * @return  the value of CATEGORY.parent_id
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.parent_id
	 * @param parentId  the value for CATEGORY.parent_id
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.type
	 * @return  the value of CATEGORY.type
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.type
	 * @param type  the value for CATEGORY.type
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.showInHome
	 * @return  the value of CATEGORY.showInHome
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getShowinhome() {
		return showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.showInHome
	 * @param showinhome  the value for CATEGORY.showInHome
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setShowinhome(Integer showinhome) {
		this.showinhome = showinhome;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.homeIndex
	 * @return  the value of CATEGORY.homeIndex
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getHomeindex() {
		return homeindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.homeIndex
	 * @param homeindex  the value for CATEGORY.homeIndex
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setHomeindex(Integer homeindex) {
		this.homeindex = homeindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column CATEGORY.isother
	 * @return  the value of CATEGORY.isother
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer getIsother() {
		return isother;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column CATEGORY.isother
	 * @param isother  the value for CATEGORY.isother
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public void setIsother(Integer isother) {
		this.isother = isother;
	}
}