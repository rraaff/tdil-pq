package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.RankingNoteCountryDAO;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.RankingNoteCountryExample;
import java.sql.SQLException;
import java.util.List;

public class RankingNoteCountryDAOImpl implements RankingNoteCountryDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public RankingNoteCountryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int countRankingNoteCountryByExample(RankingNoteCountryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("RANKING_NOTE_COUNTRY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int deleteRankingNoteCountryByExample(RankingNoteCountryExample example) throws SQLException {
		int rows = sqlMapClient.delete("RANKING_NOTE_COUNTRY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int deleteRankingNoteCountryByPrimaryKey(Integer id) throws SQLException {
		RankingNoteCountry _key = new RankingNoteCountry();
		_key.setId(id);
		int rows = sqlMapClient.delete("RANKING_NOTE_COUNTRY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public Integer insertRankingNoteCountry(RankingNoteCountry record) throws SQLException {
		Object newKey = sqlMapClient.insert("RANKING_NOTE_COUNTRY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public Integer insertRankingNoteCountrySelective(RankingNoteCountry record) throws SQLException {
		Object newKey = sqlMapClient.insert("RANKING_NOTE_COUNTRY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<RankingNoteCountry> selectRankingNoteCountryByExample(RankingNoteCountryExample example)
			throws SQLException {
		List<RankingNoteCountry> list = sqlMapClient.queryForList("RANKING_NOTE_COUNTRY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public RankingNoteCountry selectRankingNoteCountryByPrimaryKey(Integer id) throws SQLException {
		RankingNoteCountry _key = new RankingNoteCountry();
		_key.setId(id);
		RankingNoteCountry record = (RankingNoteCountry) sqlMapClient.queryForObject(
				"RANKING_NOTE_COUNTRY.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateRankingNoteCountryByExampleSelective(RankingNoteCountry record, RankingNoteCountryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("RANKING_NOTE_COUNTRY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateRankingNoteCountryByExample(RankingNoteCountry record, RankingNoteCountryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("RANKING_NOTE_COUNTRY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateRankingNoteCountryByPrimaryKeySelective(RankingNoteCountry record) throws SQLException {
		int rows = sqlMapClient.update("RANKING_NOTE_COUNTRY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	public int updateRankingNoteCountryByPrimaryKey(RankingNoteCountry record) throws SQLException {
		int rows = sqlMapClient.update("RANKING_NOTE_COUNTRY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table RANKING_NOTE_COUNTRY
	 * @mbggenerated  Mon Apr 16 18:40:01 ART 2012
	 */
	protected static class UpdateByExampleParms extends RankingNoteCountryExample {
		private Object record;

		public UpdateByExampleParms(Object record, RankingNoteCountryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}