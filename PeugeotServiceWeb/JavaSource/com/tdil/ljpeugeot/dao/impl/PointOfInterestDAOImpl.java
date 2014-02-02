package com.tdil.ljpeugeot.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.PointOfInterestDAO;
import com.tdil.ljpeugeot.model.PointOfInterest;
import com.tdil.ljpeugeot.model.PointOfInterestExample;
import java.sql.SQLException;
import java.util.List;

public class PointOfInterestDAOImpl implements PointOfInterestDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public PointOfInterestDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int countPointOfInterestByExample(PointOfInterestExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("POI.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int deletePointOfInterestByExample(PointOfInterestExample example) throws SQLException {
		int rows = sqlMapClient.delete("POI.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int deletePointOfInterestByPrimaryKey(Integer id) throws SQLException {
		PointOfInterest _key = new PointOfInterest();
		_key.setId(id);
		int rows = sqlMapClient.delete("POI.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public Integer insertPointOfInterest(PointOfInterest record) throws SQLException {
		Object newKey = sqlMapClient.insert("POI.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public Integer insertPointOfInterestSelective(PointOfInterest record) throws SQLException {
		Object newKey = sqlMapClient.insert("POI.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<PointOfInterest> selectPointOfInterestByExample(PointOfInterestExample example) throws SQLException {
		List<PointOfInterest> list = sqlMapClient.queryForList("POI.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public PointOfInterest selectPointOfInterestByPrimaryKey(Integer id) throws SQLException {
		PointOfInterest _key = new PointOfInterest();
		_key.setId(id);
		PointOfInterest record = (PointOfInterest) sqlMapClient.queryForObject("POI.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int updatePointOfInterestByExampleSelective(PointOfInterest record, PointOfInterestExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("POI.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int updatePointOfInterestByExample(PointOfInterest record, PointOfInterestExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("POI.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int updatePointOfInterestByPrimaryKeySelective(PointOfInterest record) throws SQLException {
		int rows = sqlMapClient.update("POI.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	public int updatePointOfInterestByPrimaryKey(PointOfInterest record) throws SQLException {
		int rows = sqlMapClient.update("POI.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table POI
	 * @mbggenerated  Sun Feb 02 11:05:09 ART 2014
	 */
	protected static class UpdateByExampleParms extends PointOfInterestExample {
		private Object record;

		public UpdateByExampleParms(Object record, PointOfInterestExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}