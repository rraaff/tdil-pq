package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.MilkaPhotoDAO;
import com.tdil.milka.model.MilkaPhoto;
import com.tdil.milka.model.MilkaPhotoExample;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;

import java.sql.SQLException;
import java.util.List;

public class MilkaPhotoDAOImpl implements MilkaPhotoDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	private SqlMapClient sqlMapClient;
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public MilkaPhotoDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int countMilkaPhotoByExample(MilkaPhotoExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("MILKA_PHOTO.countByExample", example);
		return count;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int deleteMilkaPhotoByExample(MilkaPhotoExample example) throws SQLException {
		int rows = sqlMapClient.delete("MILKA_PHOTO.deleteByExample", example);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int deleteMilkaPhotoByPrimaryKey(Integer id) throws SQLException {
		MilkaPhoto _key = new MilkaPhoto();
		_key.setId(id);
		int rows = sqlMapClient.delete("MILKA_PHOTO.deleteByPrimaryKey", _key);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public Integer insertMilkaPhoto(MilkaPhoto record) throws SQLException {
		Object newKey = sqlMapClient.insert("MILKA_PHOTO.insert", record);
		return (Integer) newKey;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public Integer insertMilkaPhotoSelective(MilkaPhoto record) throws SQLException {
		Object newKey = sqlMapClient.insert("MILKA_PHOTO.insertSelective", record);
		return (Integer) newKey;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<MilkaPhoto> selectMilkaPhotoByExample(MilkaPhotoExample example) throws SQLException {
		List<MilkaPhoto> list = sqlMapClient.queryForList("MILKA_PHOTO.selectByExample", example);
		return list;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public MilkaPhoto selectMilkaPhotoByPrimaryKey(Integer id) throws SQLException {
		MilkaPhoto _key = new MilkaPhoto();
		_key.setId(id);
		MilkaPhoto record = (MilkaPhoto) sqlMapClient.queryForObject("MILKA_PHOTO.selectByPrimaryKey", _key);
		return record;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int updateMilkaPhotoByExampleSelective(MilkaPhoto record, MilkaPhotoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MILKA_PHOTO.updateByExampleSelective", parms);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int updateMilkaPhotoByExample(MilkaPhoto record, MilkaPhotoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MILKA_PHOTO.updateByExample", parms);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int updateMilkaPhotoByPrimaryKeySelective(MilkaPhoto record) throws SQLException {
		int rows = sqlMapClient.update("MILKA_PHOTO.updateByPrimaryKeySelective", record);
		return rows;
	}
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	public int updateMilkaPhotoByPrimaryKey(MilkaPhoto record) throws SQLException {
		int rows = sqlMapClient.update("MILKA_PHOTO.updateByPrimaryKey", record);
		return rows;
	}
	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MILKA_PHOTO
	 * @mbggenerated  Mon May 21 18:14:12 ART 2012
	 */
	protected static class UpdateByExampleParms extends MilkaPhotoExample {
		private Object record;

		public UpdateByExampleParms(Object record, MilkaPhotoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	/** Custom queries */
	public List<MilkaPhotoValueObject> selectMilkaPhotoToApproveWithAuthor() throws SQLException {
		List<MilkaPhotoValueObject> list = sqlMapClient.queryForList("MILKA_PHOTO.selectPhotosWithAuthorsToApprove");
		return list;
	}
	public List<MilkaPhotoValueObject> selectMilkaPhotoToReviewWithAuthor() throws SQLException {
		List<MilkaPhotoValueObject> list = sqlMapClient.queryForList("MILKA_PHOTO.selectPhotosWithAuthorsToReview");
		return list;
	}
}