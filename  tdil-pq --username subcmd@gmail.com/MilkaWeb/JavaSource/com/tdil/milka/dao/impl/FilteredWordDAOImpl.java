package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.FilteredWordDAO;
import com.tdil.milka.model.FilteredWord;
import com.tdil.milka.model.FilteredWordExample;
import java.sql.SQLException;
import java.util.List;

public class FilteredWordDAOImpl implements FilteredWordDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public FilteredWordDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int countFilteredWordByExample(FilteredWordExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("FILTERED_WORD.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteFilteredWordByExample(FilteredWordExample example) throws SQLException {
		int rows = sqlMapClient.delete("FILTERED_WORD.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteFilteredWordByPrimaryKey(Integer id) throws SQLException {
		FilteredWord _key = new FilteredWord();
		_key.setId(id);
		int rows = sqlMapClient.delete("FILTERED_WORD.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertFilteredWord(FilteredWord record) throws SQLException {
		Object newKey = sqlMapClient.insert("FILTERED_WORD.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertFilteredWordSelective(FilteredWord record) throws SQLException {
		Object newKey = sqlMapClient.insert("FILTERED_WORD.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<FilteredWord> selectFilteredWordByExample(FilteredWordExample example) throws SQLException {
		List<FilteredWord> list = sqlMapClient.queryForList("FILTERED_WORD.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public FilteredWord selectFilteredWordByPrimaryKey(Integer id) throws SQLException {
		FilteredWord _key = new FilteredWord();
		_key.setId(id);
		FilteredWord record = (FilteredWord) sqlMapClient.queryForObject("FILTERED_WORD.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateFilteredWordByExampleSelective(FilteredWord record, FilteredWordExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FILTERED_WORD.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateFilteredWordByExample(FilteredWord record, FilteredWordExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FILTERED_WORD.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateFilteredWordByPrimaryKeySelective(FilteredWord record) throws SQLException {
		int rows = sqlMapClient.update("FILTERED_WORD.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateFilteredWordByPrimaryKey(FilteredWord record) throws SQLException {
		int rows = sqlMapClient.update("FILTERED_WORD.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FILTERED_WORD
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected static class UpdateByExampleParms extends FilteredWordExample {
		private Object record;

		public UpdateByExampleParms(Object record, FilteredWordExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}