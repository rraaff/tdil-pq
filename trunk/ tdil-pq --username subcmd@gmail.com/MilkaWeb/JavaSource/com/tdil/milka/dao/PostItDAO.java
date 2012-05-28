package com.tdil.milka.dao;

import com.tdil.milka.model.PostIt;
import com.tdil.milka.model.PostItExample;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;
import com.tdil.milka.model.valueobjects.PostItValueObject;

import java.sql.SQLException;
import java.util.List;

public interface PostItDAO {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int countPostItByExample(PostItExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int deletePostItByExample(PostItExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int deletePostItByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	Integer insertPostIt(PostIt record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	Integer insertPostItSelective(PostIt record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	List<PostIt> selectPostItByExample(PostItExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	PostIt selectPostItByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updatePostItByExampleSelective(PostIt record, PostItExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updatePostItByExample(PostIt record, PostItExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updatePostItByPrimaryKeySelective(PostIt record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Mon May 28 18:13:06 ART 2012
	 */
	int updatePostItByPrimaryKey(PostIt record) throws SQLException;

	/** Resultado con los autores */
	List<PostItValueObject> selectPostItsToApproveWithAuthor() throws SQLException;
	
	List<PostItValueObject> selectPostItsToReviewWithAuthor() throws SQLException;
}