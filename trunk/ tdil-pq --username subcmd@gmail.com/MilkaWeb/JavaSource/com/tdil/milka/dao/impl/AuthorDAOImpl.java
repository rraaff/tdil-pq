package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.AuthorDAO;
import com.tdil.milka.model.Author;
import com.tdil.milka.model.AuthorExample;
import java.sql.SQLException;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public AuthorDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int countAuthorByExample(AuthorExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("AUTHOR.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteAuthorByExample(AuthorExample example) throws SQLException {
		int rows = sqlMapClient.delete("AUTHOR.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteAuthorByPrimaryKey(Integer id) throws SQLException {
		Author _key = new Author();
		_key.setId(id);
		int rows = sqlMapClient.delete("AUTHOR.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertAuthor(Author record) throws SQLException {
		Object newKey = sqlMapClient.insert("AUTHOR.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertAuthorSelective(Author record) throws SQLException {
		Object newKey = sqlMapClient.insert("AUTHOR.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Author> selectAuthorByExample(AuthorExample example) throws SQLException {
		List<Author> list = sqlMapClient.queryForList("AUTHOR.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Author selectAuthorByPrimaryKey(Integer id) throws SQLException {
		Author _key = new Author();
		_key.setId(id);
		Author record = (Author) sqlMapClient.queryForObject("AUTHOR.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateAuthorByExampleSelective(Author record, AuthorExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("AUTHOR.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateAuthorByExample(Author record, AuthorExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("AUTHOR.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateAuthorByPrimaryKeySelective(Author record) throws SQLException {
		int rows = sqlMapClient.update("AUTHOR.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateAuthorByPrimaryKey(Author record) throws SQLException {
		int rows = sqlMapClient.update("AUTHOR.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table AUTHOR
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected static class UpdateByExampleParms extends AuthorExample {
		private Object record;

		public UpdateByExampleParms(Object record, AuthorExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}