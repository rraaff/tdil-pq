package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfileVideoDAO;
import com.tdil.tuafesta.model.ProfileVideo;
import com.tdil.tuafesta.model.ProfileVideoExample;
import java.sql.SQLException;
import java.util.List;

public class ProfileVideoDAOImpl implements ProfileVideoDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public ProfileVideoDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int countProfileVideoByExample(ProfileVideoExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROFILE_VIDEO.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int deleteProfileVideoByExample(ProfileVideoExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROFILE_VIDEO.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int deleteProfileVideoByPrimaryKey(Integer id) throws SQLException {
		ProfileVideo _key = new ProfileVideo();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROFILE_VIDEO.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer insertProfileVideo(ProfileVideo record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFILE_VIDEO.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public Integer insertProfileVideoSelective(ProfileVideo record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROFILE_VIDEO.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProfileVideo> selectProfileVideoByExample(ProfileVideoExample example) throws SQLException {
		List<ProfileVideo> list = sqlMapClient.queryForList("PROFILE_VIDEO.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public ProfileVideo selectProfileVideoByPrimaryKey(Integer id) throws SQLException {
		ProfileVideo _key = new ProfileVideo();
		_key.setId(id);
		ProfileVideo record = (ProfileVideo) sqlMapClient.queryForObject("PROFILE_VIDEO.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateProfileVideoByExampleSelective(ProfileVideo record, ProfileVideoExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFILE_VIDEO.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateProfileVideoByExample(ProfileVideo record, ProfileVideoExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROFILE_VIDEO.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateProfileVideoByPrimaryKeySelective(ProfileVideo record) throws SQLException {
		int rows = sqlMapClient.update("PROFILE_VIDEO.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	public int updateProfileVideoByPrimaryKey(ProfileVideo record) throws SQLException {
		int rows = sqlMapClient.update("PROFILE_VIDEO.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROFILE_VIDEO
	 * @mbggenerated  Wed Sep 19 23:16:35 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfileVideoExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfileVideoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}