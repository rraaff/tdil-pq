package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.SellPhotoDAO;
import com.tdil.tuafesta.model.SellPhoto;
import com.tdil.tuafesta.model.SellPhotoExample;
import java.sql.SQLException;
import java.util.List;

public class SellPhotoDAOImpl implements SellPhotoDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public SellPhotoDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int countSellPhotoByExample(SellPhotoExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SELL_PHOTO.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int deleteSellPhotoByExample(SellPhotoExample example) throws SQLException {
		int rows = sqlMapClient.delete("SELL_PHOTO.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int deleteSellPhotoByPrimaryKey(Integer id) throws SQLException {
		SellPhoto _key = new SellPhoto();
		_key.setId(id);
		int rows = sqlMapClient.delete("SELL_PHOTO.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public Integer insertSellPhoto(SellPhoto record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL_PHOTO.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public Integer insertSellPhotoSelective(SellPhoto record) throws SQLException {
		Object newKey = sqlMapClient.insert("SELL_PHOTO.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<SellPhoto> selectSellPhotoByExample(SellPhotoExample example) throws SQLException {
		List<SellPhoto> list = sqlMapClient.queryForList("SELL_PHOTO.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public SellPhoto selectSellPhotoByPrimaryKey(Integer id) throws SQLException {
		SellPhoto _key = new SellPhoto();
		_key.setId(id);
		SellPhoto record = (SellPhoto) sqlMapClient.queryForObject("SELL_PHOTO.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int updateSellPhotoByExampleSelective(SellPhoto record, SellPhotoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL_PHOTO.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int updateSellPhotoByExample(SellPhoto record, SellPhotoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SELL_PHOTO.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int updateSellPhotoByPrimaryKeySelective(SellPhoto record) throws SQLException {
		int rows = sqlMapClient.update("SELL_PHOTO.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	public int updateSellPhotoByPrimaryKey(SellPhoto record) throws SQLException {
		int rows = sqlMapClient.update("SELL_PHOTO.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SELL_PHOTO
	 * @mbggenerated  Mon Aug 13 19:20:03 ART 2012
	 */
	protected static class UpdateByExampleParms extends SellPhotoExample {
		private Object record;

		public UpdateByExampleParms(Object record, SellPhotoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}