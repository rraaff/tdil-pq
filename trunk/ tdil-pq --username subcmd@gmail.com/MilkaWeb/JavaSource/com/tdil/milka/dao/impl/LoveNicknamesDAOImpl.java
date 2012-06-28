package com.tdil.milka.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.LoveNicknamesDAO;
import com.tdil.milka.model.LoveNicknames;
import com.tdil.milka.model.LoveNicknamesExample;
import com.tdil.milka.model.valueobjects.ExperienceValueObject;

public class LoveNicknamesDAOImpl implements LoveNicknamesDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public LoveNicknamesDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int countLoveNicknamesByExample(LoveNicknamesExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("LOVE_NICKNAMES.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteLoveNicknamesByExample(LoveNicknamesExample example) throws SQLException {
		int rows = sqlMapClient.delete("LOVE_NICKNAMES.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int deleteLoveNicknamesByPrimaryKey(Integer id) throws SQLException {
		LoveNicknames _key = new LoveNicknames();
		_key.setId(id);
		int rows = sqlMapClient.delete("LOVE_NICKNAMES.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertLoveNicknames(LoveNicknames record) throws SQLException {
		Object newKey = sqlMapClient.insert("LOVE_NICKNAMES.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public Integer insertLoveNicknamesSelective(LoveNicknames record) throws SQLException {
		Object newKey = sqlMapClient.insert("LOVE_NICKNAMES.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<LoveNicknames> selectLoveNicknamesByExample(LoveNicknamesExample example) throws SQLException {
		List<LoveNicknames> list = sqlMapClient.queryForList("LOVE_NICKNAMES.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public LoveNicknames selectLoveNicknamesByPrimaryKey(Integer id) throws SQLException {
		LoveNicknames _key = new LoveNicknames();
		_key.setId(id);
		LoveNicknames record = (LoveNicknames) sqlMapClient.queryForObject("LOVE_NICKNAMES.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateLoveNicknamesByExampleSelective(LoveNicknames record, LoveNicknamesExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("LOVE_NICKNAMES.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateLoveNicknamesByExample(LoveNicknames record, LoveNicknamesExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("LOVE_NICKNAMES.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateLoveNicknamesByPrimaryKeySelective(LoveNicknames record) throws SQLException {
		int rows = sqlMapClient.update("LOVE_NICKNAMES.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	public int updateLoveNicknamesByPrimaryKey(LoveNicknames record) throws SQLException {
		int rows = sqlMapClient.update("LOVE_NICKNAMES.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table LOVE_NICKNAMES
	 * @mbggenerated  Wed Jun 27 22:57:22 ART 2012
	 */
	protected static class UpdateByExampleParms extends LoveNicknamesExample {
		private Object record;

		public UpdateByExampleParms(Object record, LoveNicknamesExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<LoveNicknames> selectLoveNicknamesToApprove() throws SQLException {
		return sqlMapClient.queryForList("LOVE_NICKNAMES.selectLoveNicknamesToApprove");
	}
	
	public List<LoveNicknames> selectLoveNicknamesToReview() throws SQLException {
		return sqlMapClient.queryForList("LOVE_NICKNAMES.selectLoveNicknamesToReview");
	}
	
	public List<LoveNicknames> selectLoveNicknamesForFlash() throws SQLException {
		return sqlMapClient.queryForList("LOVE_NICKNAMES.selectLoveNicknamesForFlash");
	}
	
	public List<ExperienceValueObject> search() throws SQLException {
		List<ExperienceValueObject> list = sqlMapClient.queryForList("LOVE_NICKNAMES.selectExperience");
		return list;
	}
}