package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.NoteImageDAO;
import com.tdil.djmag.model.NoteImage;
import com.tdil.djmag.model.NoteImageExample;
import java.sql.SQLException;
import java.util.List;

public class NoteImageDAOImpl implements NoteImageDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public NoteImageDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int countNoteImageByExample(NoteImageExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("NOTE_IMAGE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int deleteNoteImageByExample(NoteImageExample example) throws SQLException {
		int rows = sqlMapClient.delete("NOTE_IMAGE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int deleteNoteImageByPrimaryKey(Integer id) throws SQLException {
		NoteImage _key = new NoteImage();
		_key.setId(id);
		int rows = sqlMapClient.delete("NOTE_IMAGE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public Integer insertNoteImage(NoteImage record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTE_IMAGE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public Integer insertNoteImageSelective(NoteImage record) throws SQLException {
		Object newKey = sqlMapClient.insert("NOTE_IMAGE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<NoteImage> selectNoteImageByExampleWithBLOBs(NoteImageExample example) throws SQLException {
		List<NoteImage> list = sqlMapClient.queryForList("NOTE_IMAGE.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<NoteImage> selectNoteImageByExampleWithoutBLOBs(NoteImageExample example) throws SQLException {
		List<NoteImage> list = sqlMapClient.queryForList("NOTE_IMAGE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public NoteImage selectNoteImageByPrimaryKey(Integer id) throws SQLException {
		NoteImage _key = new NoteImage();
		_key.setId(id);
		NoteImage record = (NoteImage) sqlMapClient.queryForObject("NOTE_IMAGE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int updateNoteImageByExampleSelective(NoteImage record, NoteImageExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE_IMAGE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int updateNoteImageByExampleWithBLOBs(NoteImage record, NoteImageExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE_IMAGE.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int updateNoteImageByExampleWithoutBLOBs(NoteImage record, NoteImageExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("NOTE_IMAGE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int updateNoteImageByPrimaryKeySelective(NoteImage record) throws SQLException {
		int rows = sqlMapClient.update("NOTE_IMAGE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int updateNoteImageByPrimaryKeyWithBLOBs(NoteImage record) throws SQLException {
		int rows = sqlMapClient.update("NOTE_IMAGE.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	public int updateNoteImageByPrimaryKeyWithoutBLOBs(NoteImage record) throws SQLException {
		int rows = sqlMapClient.update("NOTE_IMAGE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table NOTE_IMAGE
	 * @mbggenerated  Wed Apr 25 18:51:42 ART 2012
	 */
	protected static class UpdateByExampleParms extends NoteImageExample {
		private Object record;

		public UpdateByExampleParms(Object record, NoteImageExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}