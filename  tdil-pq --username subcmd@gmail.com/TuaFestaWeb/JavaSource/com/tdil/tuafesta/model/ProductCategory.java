package com.tdil.tuafesta.model;

import com.tdil.ibatis.PersistentObject;

public class ProductCategory extends PersistentObject {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_PROD_CATEGORY.name
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_PROD_CATEGORY.description
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column PROF_PROD_CATEGORY.parent_id
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	private Integer parentId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_PROD_CATEGORY.name
	 * @return  the value of PROF_PROD_CATEGORY.name
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_PROD_CATEGORY.name
	 * @param name  the value for PROF_PROD_CATEGORY.name
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_PROD_CATEGORY.description
	 * @return  the value of PROF_PROD_CATEGORY.description
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_PROD_CATEGORY.description
	 * @param description  the value for PROF_PROD_CATEGORY.description
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column PROF_PROD_CATEGORY.parent_id
	 * @return  the value of PROF_PROD_CATEGORY.parent_id
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column PROF_PROD_CATEGORY.parent_id
	 * @param parentId  the value for PROF_PROD_CATEGORY.parent_id
	 * @mbggenerated  Tue Sep 11 18:19:23 ART 2012
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}