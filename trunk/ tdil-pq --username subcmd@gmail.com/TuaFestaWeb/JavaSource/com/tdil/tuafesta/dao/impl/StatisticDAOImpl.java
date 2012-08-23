package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.StatisticDAO;
import com.tdil.tuafesta.model.Statistic;
import com.tdil.tuafesta.model.StatisticExample;
import java.sql.SQLException;
import java.util.List;

public class StatisticDAOImpl implements StatisticDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public StatisticDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int countStatisticByExample(StatisticExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("STATISTIC.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int deleteStatisticByExample(StatisticExample example) throws SQLException {
		int rows = sqlMapClient.delete("STATISTIC.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int deleteStatisticByPrimaryKey(Integer id) throws SQLException {
		Statistic _key = new Statistic();
		_key.setId(id);
		int rows = sqlMapClient.delete("STATISTIC.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public Integer insertStatistic(Statistic record) throws SQLException {
		Object newKey = sqlMapClient.insert("STATISTIC.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public Integer insertStatisticSelective(Statistic record) throws SQLException {
		Object newKey = sqlMapClient.insert("STATISTIC.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Statistic> selectStatisticByExample(StatisticExample example) throws SQLException {
		List<Statistic> list = sqlMapClient.queryForList("STATISTIC.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public Statistic selectStatisticByPrimaryKey(Integer id) throws SQLException {
		Statistic _key = new Statistic();
		_key.setId(id);
		Statistic record = (Statistic) sqlMapClient.queryForObject("STATISTIC.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int updateStatisticByExampleSelective(Statistic record, StatisticExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("STATISTIC.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int updateStatisticByExample(Statistic record, StatisticExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("STATISTIC.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int updateStatisticByPrimaryKeySelective(Statistic record) throws SQLException {
		int rows = sqlMapClient.update("STATISTIC.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	public int updateStatisticByPrimaryKey(Statistic record) throws SQLException {
		int rows = sqlMapClient.update("STATISTIC.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table STATISTIC
	 * @mbggenerated  Wed Aug 22 22:18:59 ART 2012
	 */
	protected static class UpdateByExampleParms extends StatisticExample {
		private Object record;

		public UpdateByExampleParms(Object record, StatisticExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}