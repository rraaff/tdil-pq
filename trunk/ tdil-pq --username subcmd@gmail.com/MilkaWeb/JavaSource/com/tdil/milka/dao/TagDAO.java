package com.tdil.milka.dao;

import com.tdil.milka.model.Tag;
import com.tdil.milka.model.TagExample;
import java.sql.SQLException;
import java.util.List;

public interface TagDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int countTagByExample(TagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int deleteTagByExample(TagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int deleteTagByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Integer insertTag(Tag record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Integer insertTagSelective(Tag record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	List<Tag> selectTagByExample(TagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	Tag selectTagByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateTagByExampleSelective(Tag record, TagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateTagByExample(Tag record, TagExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateTagByPrimaryKeySelective(Tag record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	int updateTagByPrimaryKey(Tag record) throws SQLException;
}