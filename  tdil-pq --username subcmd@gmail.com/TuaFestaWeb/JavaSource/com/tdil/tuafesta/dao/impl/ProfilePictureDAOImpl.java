package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfilePictureDAO;
import com.tdil.tuafesta.model.ProfilePicture;
import com.tdil.tuafesta.model.ProfilePictureExample;
import java.sql.SQLException;
import java.util.List;

public class ProfilePictureDAOImpl implements ProfilePictureDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public ProfilePictureDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int countProfilePictureByExample(ProfilePictureExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROFILE_PICTURE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int deleteProfilePictureByExample(ProfilePictureExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROFILE_PICTURE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int deleteProfilePictureByPrimaryKey(Integer id) throws SQLException {
		ProfilePicture _key = new ProfilePicture();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROFILE_PICTURE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public Integer insertProfilePicture(ProfilePicture record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFILE_PICTURE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public Integer insertProfilePictureSelective(ProfilePicture record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFILE_PICTURE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProfilePicture> selectProfilePictureByExample(ProfilePictureExample example) throws SQLException {
		List<ProfilePicture> list = sqlMapClient.queryForList("PROFILE_PICTURE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public ProfilePicture selectProfilePictureByPrimaryKey(Integer id) throws SQLException {
		ProfilePicture _key = new ProfilePicture();
		_key.setId(id);
		ProfilePicture record = (ProfilePicture) sqlMapClient
				.queryForObject("PROFILE_PICTURE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateProfilePictureByExampleSelective(ProfilePicture record, ProfilePictureExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFILE_PICTURE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateProfilePictureByExample(ProfilePicture record, ProfilePictureExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFILE_PICTURE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateProfilePictureByPrimaryKeySelective(ProfilePicture record) throws SQLException {
		int rows = sqlMapClient.update("PROFILE_PICTURE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	public int updateProfilePictureByPrimaryKey(ProfilePicture record) throws SQLException {
		int rows = sqlMapClient.update("PROFILE_PICTURE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROFILE_PICTURE
	 * @mbggenerated  Fri Aug 17 19:00:12 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfilePictureExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfilePictureExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}