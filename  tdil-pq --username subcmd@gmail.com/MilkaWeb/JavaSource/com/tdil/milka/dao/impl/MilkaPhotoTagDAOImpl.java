package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.MilkaPhotoTagDAO;
import com.tdil.milka.model.MilkaPhotoTag;
import com.tdil.milka.model.MilkaPhotoTagExample;
import java.sql.SQLException;
import java.util.List;

public class MilkaPhotoTagDAOImpl implements MilkaPhotoTagDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public MilkaPhotoTagDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int countMilkaPhotoTagByExample(MilkaPhotoTagExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("MILKA_PHOTO_TAG.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int deleteMilkaPhotoTagByExample(MilkaPhotoTagExample example) throws SQLException {
		int rows = sqlMapClient.delete("MILKA_PHOTO_TAG.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int deleteMilkaPhotoTagByPrimaryKey(Integer id) throws SQLException {
		MilkaPhotoTag _key = new MilkaPhotoTag();
		_key.setId(id);
		int rows = sqlMapClient.delete("MILKA_PHOTO_TAG.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public Integer insertMilkaPhotoTag(MilkaPhotoTag record) throws SQLException {
		Object newKey = sqlMapClient.insert("MILKA_PHOTO_TAG.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public Integer insertMilkaPhotoTagSelective(MilkaPhotoTag record) throws SQLException {
		Object newKey = sqlMapClient.insert("MILKA_PHOTO_TAG.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<MilkaPhotoTag> selectMilkaPhotoTagByExample(MilkaPhotoTagExample example) throws SQLException {
		List<MilkaPhotoTag> list = sqlMapClient.queryForList("MILKA_PHOTO_TAG.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public MilkaPhotoTag selectMilkaPhotoTagByPrimaryKey(Integer id) throws SQLException {
		MilkaPhotoTag _key = new MilkaPhotoTag();
		_key.setId(id);
		MilkaPhotoTag record = (MilkaPhotoTag) sqlMapClient.queryForObject("MILKA_PHOTO_TAG.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateMilkaPhotoTagByExampleSelective(MilkaPhotoTag record, MilkaPhotoTagExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MILKA_PHOTO_TAG.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateMilkaPhotoTagByExample(MilkaPhotoTag record, MilkaPhotoTagExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MILKA_PHOTO_TAG.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateMilkaPhotoTagByPrimaryKeySelective(MilkaPhotoTag record) throws SQLException {
		int rows = sqlMapClient.update("MILKA_PHOTO_TAG.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	public int updateMilkaPhotoTagByPrimaryKey(MilkaPhotoTag record) throws SQLException {
		int rows = sqlMapClient.update("MILKA_PHOTO_TAG.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MILKA_PHOTO_TAG
	 * @mbggenerated  Thu May 31 22:35:48 ART 2012
	 */
	protected static class UpdateByExampleParms extends MilkaPhotoTagExample {
		private Object record;

		public UpdateByExampleParms(Object record, MilkaPhotoTagExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}