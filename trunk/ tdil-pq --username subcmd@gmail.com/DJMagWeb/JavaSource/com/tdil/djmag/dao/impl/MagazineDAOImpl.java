package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.MagazineDAO;
import com.tdil.djmag.model.Magazine;
import com.tdil.djmag.model.MagazineExample;
import java.sql.SQLException;
import java.util.List;

public class MagazineDAOImpl implements MagazineDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public MagazineDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int countMagazineByExample(MagazineExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("MAGAZINE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int deleteMagazineByExample(MagazineExample example) throws SQLException {
		int rows = sqlMapClient.delete("MAGAZINE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int deleteMagazineByPrimaryKey(Integer id) throws SQLException {
		Magazine _key = new Magazine();
		_key.setId(id);
		int rows = sqlMapClient.delete("MAGAZINE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer insertMagazine(Magazine record) throws SQLException {
		Object newKey = sqlMapClient.insert("MAGAZINE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Integer insertMagazineSelective(Magazine record) throws SQLException {
		Object newKey = sqlMapClient.insert("MAGAZINE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Magazine> selectMagazineByExample(MagazineExample example) throws SQLException {
		List<Magazine> list = sqlMapClient.queryForList("MAGAZINE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public Magazine selectMagazineByPrimaryKey(Integer id) throws SQLException {
		Magazine _key = new Magazine();
		_key.setId(id);
		Magazine record = (Magazine) sqlMapClient.queryForObject("MAGAZINE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int updateMagazineByExampleSelective(Magazine record, MagazineExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MAGAZINE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int updateMagazineByExample(Magazine record, MagazineExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MAGAZINE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int updateMagazineByPrimaryKeySelective(Magazine record) throws SQLException {
		int rows = sqlMapClient.update("MAGAZINE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	public int updateMagazineByPrimaryKey(Magazine record) throws SQLException {
		int rows = sqlMapClient.update("MAGAZINE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MAGAZINE
	 * @mbggenerated  Sat Jun 09 01:03:43 ART 2012
	 */
	protected static class UpdateByExampleParms extends MagazineExample {
		private Object record;

		public UpdateByExampleParms(Object record, MagazineExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}