package com.tdil.ljpeugeot.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.AdviceDAO;
import com.tdil.ljpeugeot.model.Advice;
import com.tdil.ljpeugeot.model.AdviceExample;
import java.sql.SQLException;
import java.util.List;

public class AdviceDAOImpl implements AdviceDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public AdviceDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int countAdviceByExample(AdviceExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("ADVICE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int deleteAdviceByExample(AdviceExample example) throws SQLException {
		int rows = sqlMapClient.delete("ADVICE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int deleteAdviceByPrimaryKey(Integer id) throws SQLException {
		Advice _key = new Advice();
		_key.setId(id);
		int rows = sqlMapClient.delete("ADVICE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer insertAdvice(Advice record) throws SQLException {
		Object newKey = sqlMapClient.insert("ADVICE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Integer insertAdviceSelective(Advice record) throws SQLException {
		Object newKey = sqlMapClient.insert("ADVICE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<Advice> selectAdviceByExample(AdviceExample example) throws SQLException {
		List<Advice> list = sqlMapClient.queryForList("ADVICE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public Advice selectAdviceByPrimaryKey(Integer id) throws SQLException {
		Advice _key = new Advice();
		_key.setId(id);
		Advice record = (Advice) sqlMapClient.queryForObject("ADVICE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int updateAdviceByExampleSelective(Advice record, AdviceExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("ADVICE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int updateAdviceByExample(Advice record, AdviceExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("ADVICE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int updateAdviceByPrimaryKeySelective(Advice record) throws SQLException {
		int rows = sqlMapClient.update("ADVICE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	public int updateAdviceByPrimaryKey(Advice record) throws SQLException {
		int rows = sqlMapClient.update("ADVICE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ADVICE
	 * @mbggenerated  Tue Feb 04 00:37:46 ART 2014
	 */
	protected static class UpdateByExampleParms extends AdviceExample {
		private Object record;

		public UpdateByExampleParms(Object record, AdviceExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}