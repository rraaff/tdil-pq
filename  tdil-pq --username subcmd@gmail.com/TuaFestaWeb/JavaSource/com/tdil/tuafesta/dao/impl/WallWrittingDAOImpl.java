package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.WallWrittingDAO;
import com.tdil.tuafesta.model.WallWritting;
import com.tdil.tuafesta.model.WallWrittingExample;
import java.sql.SQLException;
import java.util.List;

public class WallWrittingDAOImpl implements WallWrittingDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public WallWrittingDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int countWallWrittingByExample(WallWrittingExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("WALL_WRITTING.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int deleteWallWrittingByExample(WallWrittingExample example) throws SQLException {
		int rows = sqlMapClient.delete("WALL_WRITTING.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int deleteWallWrittingByPrimaryKey(Integer id) throws SQLException {
		WallWritting _key = new WallWritting();
		_key.setId(id);
		int rows = sqlMapClient.delete("WALL_WRITTING.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public Integer insertWallWritting(WallWritting record) throws SQLException {
		Object newKey = sqlMapClient.insert("WALL_WRITTING.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public Integer insertWallWrittingSelective(WallWritting record) throws SQLException {
		Object newKey = sqlMapClient.insert("WALL_WRITTING.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<WallWritting> selectWallWrittingByExample(WallWrittingExample example) throws SQLException {
		List<WallWritting> list = sqlMapClient.queryForList("WALL_WRITTING.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public WallWritting selectWallWrittingByPrimaryKey(Integer id) throws SQLException {
		WallWritting _key = new WallWritting();
		_key.setId(id);
		WallWritting record = (WallWritting) sqlMapClient.queryForObject("WALL_WRITTING.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int updateWallWrittingByExampleSelective(WallWritting record, WallWrittingExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("WALL_WRITTING.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int updateWallWrittingByExample(WallWritting record, WallWrittingExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("WALL_WRITTING.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int updateWallWrittingByPrimaryKeySelective(WallWritting record) throws SQLException {
		int rows = sqlMapClient.update("WALL_WRITTING.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	public int updateWallWrittingByPrimaryKey(WallWritting record) throws SQLException {
		int rows = sqlMapClient.update("WALL_WRITTING.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WALL_WRITTING
	 * @mbggenerated  Tue Sep 18 21:03:17 ART 2012
	 */
	protected static class UpdateByExampleParms extends WallWrittingExample {
		private Object record;

		public UpdateByExampleParms(Object record, WallWrittingExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}