package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.PromotionPhotoDAO;
import com.tdil.tuafesta.model.PromotionPhoto;
import com.tdil.tuafesta.model.PromotionPhotoExample;
import java.sql.SQLException;
import java.util.List;

public class PromotionPhotoDAOImpl implements PromotionPhotoDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public PromotionPhotoDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int countPromotionPhotoByExample(PromotionPhotoExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROMOTION_PHOTO.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int deletePromotionPhotoByExample(PromotionPhotoExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROMOTION_PHOTO.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int deletePromotionPhotoByPrimaryKey(Integer id) throws SQLException {
		PromotionPhoto _key = new PromotionPhoto();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROMOTION_PHOTO.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Integer insertPromotionPhoto(PromotionPhoto record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROMOTION_PHOTO.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public Integer insertPromotionPhotoSelective(PromotionPhoto record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROMOTION_PHOTO.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<PromotionPhoto> selectPromotionPhotoByExample(PromotionPhotoExample example) throws SQLException {
		List<PromotionPhoto> list = sqlMapClient.queryForList("PROMOTION_PHOTO.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public PromotionPhoto selectPromotionPhotoByPrimaryKey(Integer id) throws SQLException {
		PromotionPhoto _key = new PromotionPhoto();
		_key.setId(id);
		PromotionPhoto record = (PromotionPhoto) sqlMapClient
				.queryForObject("PROMOTION_PHOTO.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int updatePromotionPhotoByExampleSelective(PromotionPhoto record, PromotionPhotoExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROMOTION_PHOTO.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int updatePromotionPhotoByExample(PromotionPhoto record, PromotionPhotoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROMOTION_PHOTO.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int updatePromotionPhotoByPrimaryKeySelective(PromotionPhoto record) throws SQLException {
		int rows = sqlMapClient.update("PROMOTION_PHOTO.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	public int updatePromotionPhotoByPrimaryKey(PromotionPhoto record) throws SQLException {
		int rows = sqlMapClient.update("PROMOTION_PHOTO.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROMOTION_PHOTO
	 * @mbggenerated  Thu Sep 27 23:40:58 ART 2012
	 */
	protected static class UpdateByExampleParms extends PromotionPhotoExample {
		private Object record;

		public UpdateByExampleParms(Object record, PromotionPhotoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}