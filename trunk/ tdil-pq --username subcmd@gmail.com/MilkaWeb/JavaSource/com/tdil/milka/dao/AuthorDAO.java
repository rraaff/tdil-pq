package com.tdil.milka.dao;

import com.tdil.milka.model.Author;
import com.tdil.milka.model.AuthorExample;
import java.sql.SQLException;
import java.util.List;

public interface AuthorDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int countAuthorByExample(AuthorExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int deleteAuthorByExample(AuthorExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int deleteAuthorByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	Integer insertAuthor(Author record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	Integer insertAuthorSelective(Author record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	List<Author> selectAuthorByExample(AuthorExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	Author selectAuthorByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int updateAuthorByExampleSelective(Author record, AuthorExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int updateAuthorByExample(Author record, AuthorExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int updateAuthorByPrimaryKeySelective(Author record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed May 23 18:56:02 ART 2012
	 */
	int updateAuthorByPrimaryKey(Author record) throws SQLException;
}