package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.SellMediaDAO;
import com.tdil.tuafesta.model.SellMedia;
import com.tdil.tuafesta.model.SellMediaExample;
import java.sql.SQLException;
import java.util.List;

public class SellMediaDAOImpl implements SellMediaDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public SellMediaDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int countSellMediaByExample(SellMediaExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SELL_MEDIA.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteSellMediaByExample(SellMediaExample example) throws SQLException {
		int rows = sqlMapClient.delete("SELL_MEDIA.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteSellMediaByPrimaryKey(Integer id) throws SQLException {
		SellMedia _key = new SellMedia();
		_key.setId(id);
		int rows = sqlMapClient.delete("SELL_MEDIA.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertSellMedia(SellMedia record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL_MEDIA.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertSellMediaSelective(SellMedia record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL_MEDIA.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<SellMedia> selectSellMediaByExample(SellMediaExample example) throws SQLException {
		List<SellMedia> list = sqlMapClient.queryForList("SELL_MEDIA.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public SellMedia selectSellMediaByPrimaryKey(Integer id) throws SQLException {
		SellMedia _key = new SellMedia();
		_key.setId(id);
		SellMedia record = (SellMedia) sqlMapClient.queryForObject("SELL_MEDIA.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellMediaByExampleSelective(SellMedia record, SellMediaExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL_MEDIA.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellMediaByExample(SellMedia record, SellMediaExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL_MEDIA.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellMediaByPrimaryKeySelective(SellMedia record) throws SQLException {
		int rows = sqlMapClient.update("SELL_MEDIA.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateSellMediaByPrimaryKey(SellMedia record) throws SQLException {
		int rows = sqlMapClient.update("SELL_MEDIA.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SELL_MEDIA
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected static class UpdateByExampleParms extends SellMediaExample {
		private Object record;

		public UpdateByExampleParms(Object record, SellMediaExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}