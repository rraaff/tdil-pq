package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.TagDAO;
import com.tdil.milka.model.Tag;
import com.tdil.milka.model.TagExample;
import java.sql.SQLException;
import java.util.List;

public class TagDAOImpl implements TagDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public TagDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int countTagByExample(TagExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("TAG.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int deleteTagByExample(TagExample example) throws SQLException {
		int rows = sqlMapClient.delete("TAG.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int deleteTagByPrimaryKey(Integer id) throws SQLException {
		Tag _key = new Tag();
		_key.setId(id);
		int rows = sqlMapClient.delete("TAG.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public Integer insertTag(Tag record) throws SQLException {
		Object newKey = sqlMapClient.insert("TAG.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public Integer insertTagSelective(Tag record) throws SQLException {
		Object newKey = sqlMapClient.insert("TAG.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Tag> selectTagByExample(TagExample example) throws SQLException {
		List<Tag> list = sqlMapClient.queryForList("TAG.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public Tag selectTagByPrimaryKey(Integer id) throws SQLException {
		Tag _key = new Tag();
		_key.setId(id);
		Tag record = (Tag) sqlMapClient.queryForObject("TAG.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int updateTagByExampleSelective(Tag record, TagExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("TAG.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int updateTagByExample(Tag record, TagExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("TAG.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int updateTagByPrimaryKeySelective(Tag record) throws SQLException {
		int rows = sqlMapClient.update("TAG.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	public int updateTagByPrimaryKey(Tag record) throws SQLException {
		int rows = sqlMapClient.update("TAG.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table TAG
	 * @mbggenerated  Tue May 22 18:58:00 ART 2012
	 */
	protected static class UpdateByExampleParms extends TagExample {
		private Object record;

		public UpdateByExampleParms(Object record, TagExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}