package com.tdil.ljpeugeot.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.CityDAO;
import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.CityExample;
import java.sql.SQLException;
import java.util.List;

public class CityDAOImpl implements CityDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public CityDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int countCityByExample(CityExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("CITY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int deleteCityByExample(CityExample example) throws SQLException {
		int rows = sqlMapClient.delete("CITY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int deleteCityByPrimaryKey(Integer id) throws SQLException {
		City _key = new City();
		_key.setId(id);
		int rows = sqlMapClient.delete("CITY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer insertCity(City record) throws SQLException {
		Object newKey = sqlMapClient.insert("CITY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public Integer insertCitySelective(City record) throws SQLException {
		Object newKey = sqlMapClient.insert("CITY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<City> selectCityByExample(CityExample example) throws SQLException {
		List<City> list = sqlMapClient.queryForList("CITY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public City selectCityByPrimaryKey(Integer id) throws SQLException {
		City _key = new City();
		_key.setId(id);
		City record = (City) sqlMapClient.queryForObject("CITY.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int updateCityByExampleSelective(City record, CityExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("CITY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int updateCityByExample(City record, CityExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("CITY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int updateCityByPrimaryKeySelective(City record) throws SQLException {
		int rows = sqlMapClient.update("CITY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	public int updateCityByPrimaryKey(City record) throws SQLException {
		int rows = sqlMapClient.update("CITY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table CITY
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	protected static class UpdateByExampleParms extends CityExample {
		private Object record;

		public UpdateByExampleParms(Object record, CityExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}