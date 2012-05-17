package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import java.sql.SQLException;
import java.util.List;

public class SectionDAOImpl implements SectionDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public SectionDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int countSectionByExample(SectionExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SECTION.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int deleteSectionByExample(SectionExample example) throws SQLException {
		int rows = sqlMapClient.delete("SECTION.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int deleteSectionByPrimaryKey(Integer id) throws SQLException {
		Section _key = new Section();
		_key.setId(id);
		int rows = sqlMapClient.delete("SECTION.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public Integer insertSection(Section record) throws SQLException {
		Object newKey = sqlMapClient.insert("SECTION.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public Integer insertSectionSelective(Section record) throws SQLException {
		Object newKey = sqlMapClient.insert("SECTION.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Section> selectSectionByExample(SectionExample example) throws SQLException {
		List<Section> list = sqlMapClient.queryForList("SECTION.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public Section selectSectionByPrimaryKey(Integer id) throws SQLException {
		Section _key = new Section();
		_key.setId(id);
		Section record = (Section) sqlMapClient.queryForObject("SECTION.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int updateSectionByExampleSelective(Section record, SectionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SECTION.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int updateSectionByExample(Section record, SectionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SECTION.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int updateSectionByPrimaryKeySelective(Section record) throws SQLException {
		int rows = sqlMapClient.update("SECTION.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	public int updateSectionByPrimaryKey(Section record) throws SQLException {
		int rows = sqlMapClient.update("SECTION.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SECTION
	 * @mbggenerated  Thu May 17 08:30:59 ART 2012
	 */
	protected static class UpdateByExampleParms extends SectionExample {
		private Object record;

		public UpdateByExampleParms(Object record, SectionExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	/** Custom queries */
	public List<Section> selectActiveSectionsForCountry(Country country) throws SQLException {
		List<Section> list = sqlMapClient.queryForList("SECTION.selectActiveSectionsForCountry", country);
		return list;
	}
}