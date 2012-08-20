package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.WallDAO;
import com.tdil.tuafesta.model.Wall;
import com.tdil.tuafesta.model.WallExample;
import java.sql.SQLException;
import java.util.List;

public class WallDAOImpl implements WallDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public WallDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int countWallByExample(WallExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("WALL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int deleteWallByExample(WallExample example) throws SQLException {
		int rows = sqlMapClient.delete("WALL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int deleteWallByPrimaryKey(Integer id) throws SQLException {
		Wall _key = new Wall();
		_key.setId(id);
		int rows = sqlMapClient.delete("WALL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public Integer insertWall(Wall record) throws SQLException {
		Object newKey = sqlMapClient.insert("WALL.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public Integer insertWallSelective(Wall record) throws SQLException {
		Object newKey = sqlMapClient.insert("WALL.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Wall> selectWallByExample(WallExample example) throws SQLException {
		List<Wall> list = sqlMapClient.queryForList("WALL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public Wall selectWallByPrimaryKey(Integer id) throws SQLException {
		Wall _key = new Wall();
		_key.setId(id);
		Wall record = (Wall) sqlMapClient.queryForObject("WALL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateWallByExampleSelective(Wall record, WallExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("WALL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateWallByExample(Wall record, WallExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("WALL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateWallByPrimaryKeySelective(Wall record) throws SQLException {
		int rows = sqlMapClient.update("WALL.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateWallByPrimaryKey(Wall record) throws SQLException {
		int rows = sqlMapClient.update("WALL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table WALL
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	protected static class UpdateByExampleParms extends WallExample {
		private Object record;

		public UpdateByExampleParms(Object record, WallExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}