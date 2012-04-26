package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.NoteCountryDAO;
import com.tdil.djmag.model.NoteCountry;
import com.tdil.djmag.model.NoteCountryExample;
import java.sql.SQLException;
import java.util.List;

public class NoteCountryDAOImpl implements NoteCountryDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public NoteCountryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int countNoteCountryByExample(NoteCountryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("NOTE_COUNTRY.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int deleteNoteCountryByExample(NoteCountryExample example) throws SQLException {
		int rows = sqlMapClient.delete("NOTE_COUNTRY.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int deleteNoteCountryByPrimaryKey(Integer id) throws SQLException {
		NoteCountry _key = new NoteCountry();
		_key.setId(id);
		int rows = sqlMapClient.delete("NOTE_COUNTRY.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Integer insertNoteCountry(NoteCountry record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTE_COUNTRY.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public Integer insertNoteCountrySelective(NoteCountry record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTE_COUNTRY.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<NoteCountry> selectNoteCountryByExample(NoteCountryExample example) throws SQLException {
		List<NoteCountry> list = sqlMapClient.queryForList("NOTE_COUNTRY.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public NoteCountry selectNoteCountryByPrimaryKey(Integer id) throws SQLException {
		NoteCountry _key = new NoteCountry();
		_key.setId(id);
		NoteCountry record = (NoteCountry) sqlMapClient.queryForObject("NOTE_COUNTRY.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateNoteCountryByExampleSelective(NoteCountry record, NoteCountryExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE_COUNTRY.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateNoteCountryByExample(NoteCountry record, NoteCountryExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE_COUNTRY.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateNoteCountryByPrimaryKeySelective(NoteCountry record) throws SQLException {
		int rows = sqlMapClient.update("NOTE_COUNTRY.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	public int updateNoteCountryByPrimaryKey(NoteCountry record) throws SQLException {
		int rows = sqlMapClient.update("NOTE_COUNTRY.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NOTE_COUNTRY
	 * @mbggenerated  Wed Apr 25 21:07:54 ART 2012
	 */
	protected static class UpdateByExampleParms extends NoteCountryExample {
		private Object record;

		public UpdateByExampleParms(Object record, NoteCountryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}