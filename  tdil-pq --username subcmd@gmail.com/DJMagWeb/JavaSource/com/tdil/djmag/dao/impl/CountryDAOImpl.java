package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import java.sql.SQLException;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public CountryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int countCountryByExample(CountryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("COUNTRY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int deleteCountryByExample(CountryExample example) throws SQLException {
		int rows = sqlMapClient.delete("COUNTRY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int deleteCountryByPrimaryKey(Integer id) throws SQLException {
		Country _key = new Country();
		_key.setId(id);
		int rows = sqlMapClient.delete("COUNTRY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public Integer insertCountry(Country record) throws SQLException {
		Object newKey = sqlMapClient.insert("COUNTRY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public Integer insertCountrySelective(Country record) throws SQLException {
		Object newKey = sqlMapClient.insert("COUNTRY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Country> selectCountryByExample(CountryExample example) throws SQLException {
		List<Country> list = sqlMapClient.queryForList("COUNTRY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public Country selectCountryByPrimaryKey(Integer id) throws SQLException {
		Country _key = new Country();
		_key.setId(id);
		Country record = (Country) sqlMapClient.queryForObject("COUNTRY.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int updateCountryByExampleSelective(Country record, CountryExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("COUNTRY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int updateCountryByExample(Country record, CountryExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("COUNTRY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int updateCountryByPrimaryKeySelective(Country record) throws SQLException {
		int rows = sqlMapClient.update("COUNTRY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	public int updateCountryByPrimaryKey(Country record) throws SQLException {
		int rows = sqlMapClient.update("COUNTRY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table COUNTRY
	 * @mbggenerated  Thu Apr 26 23:46:55 ART 2012
	 */
	protected static class UpdateByExampleParms extends CountryExample {
		private Object record;

		public UpdateByExampleParms(Object record, CountryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}