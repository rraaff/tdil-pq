package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.NoteDAO;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteExample;
import com.tdil.djmag.model.Section;

import java.sql.SQLException;
import java.util.List;

public class NoteDAOImpl implements NoteDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public NoteDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int countNoteByExample(NoteExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("NOTE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int deleteNoteByExample(NoteExample example) throws SQLException {
		int rows = sqlMapClient.delete("NOTE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int deleteNoteByPrimaryKey(Integer id) throws SQLException {
		Note _key = new Note();
		_key.setId(id);
		int rows = sqlMapClient.delete("NOTE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer insertNote(Note record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Integer insertNoteSelective(Note record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Note> selectNoteByExampleWithBLOBs(NoteExample example) throws SQLException {
		List<Note> list = sqlMapClient.queryForList("NOTE.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Note> selectNoteByExampleWithoutBLOBs(NoteExample example) throws SQLException {
		List<Note> list = sqlMapClient.queryForList("NOTE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public Note selectNoteByPrimaryKey(Integer id) throws SQLException {
		Note _key = new Note();
		_key.setId(id);
		Note record = (Note) sqlMapClient.queryForObject("NOTE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int updateNoteByExampleSelective(Note record, NoteExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int updateNoteByExampleWithBLOBs(Note record, NoteExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int updateNoteByExampleWithoutBLOBs(Note record, NoteExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int updateNoteByPrimaryKeySelective(Note record) throws SQLException {
		int rows = sqlMapClient.update("NOTE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int updateNoteByPrimaryKeyWithBLOBs(Note record) throws SQLException {
		int rows = sqlMapClient.update("NOTE.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	public int updateNoteByPrimaryKeyWithoutBLOBs(Note record) throws SQLException {
		int rows = sqlMapClient.update("NOTE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NOTE
	 * @mbggenerated  Mon Apr 23 13:38:00 ART 2012
	 */
	protected static class UpdateByExampleParms extends NoteExample {
		private Object record;

		public UpdateByExampleParms(Object record, NoteExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
	
	/** Custom queries */
	public List<Note> selectActiveFrontCoversNotesForCountry(Country country) throws SQLException {
		List<Note> list = sqlMapClient.queryForList("NOTE.selectActiveFrontCoversNotesForCountry", country);
		return list;
	}
	
	public List<Note> selectActiveAgendaNotesForCountry(Country country) throws SQLException {
		List<Note> list = sqlMapClient.queryForList("NOTE.selectActiveAgendaNotesForCountry", country);
		return list;
	}
}