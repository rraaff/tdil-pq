package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.SellVideoDAO;
import com.tdil.tuafesta.model.SellVideo;
import com.tdil.tuafesta.model.SellVideoExample;
import java.sql.SQLException;
import java.util.List;

public class SellVideoDAOImpl implements SellVideoDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public SellVideoDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int countSellVideoByExample(SellVideoExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SELL_VIDEO.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int deleteSellVideoByExample(SellVideoExample example) throws SQLException {
		int rows = sqlMapClient.delete("SELL_VIDEO.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int deleteSellVideoByPrimaryKey(Integer id) throws SQLException {
		SellVideo _key = new SellVideo();
		_key.setId(id);
		int rows = sqlMapClient.delete("SELL_VIDEO.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public Integer insertSellVideo(SellVideo record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL_VIDEO.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public Integer insertSellVideoSelective(SellVideo record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL_VIDEO.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<SellVideo> selectSellVideoByExample(SellVideoExample example) throws SQLException {
		List<SellVideo> list = sqlMapClient.queryForList("SELL_VIDEO.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public SellVideo selectSellVideoByPrimaryKey(Integer id) throws SQLException {
		SellVideo _key = new SellVideo();
		_key.setId(id);
		SellVideo record = (SellVideo) sqlMapClient.queryForObject("SELL_VIDEO.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int updateSellVideoByExampleSelective(SellVideo record, SellVideoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL_VIDEO.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int updateSellVideoByExample(SellVideo record, SellVideoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL_VIDEO.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int updateSellVideoByPrimaryKeySelective(SellVideo record) throws SQLException {
		int rows = sqlMapClient.update("SELL_VIDEO.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	public int updateSellVideoByPrimaryKey(SellVideo record) throws SQLException {
		int rows = sqlMapClient.update("SELL_VIDEO.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SELL_VIDEO
	 * @mbggenerated  Fri Aug 17 00:37:31 ART 2012
	 */
	protected static class UpdateByExampleParms extends SellVideoExample {
		private Object record;

		public UpdateByExampleParms(Object record, SellVideoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}