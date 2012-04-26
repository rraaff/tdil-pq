package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.FooterDAO;
import com.tdil.djmag.model.Footer;
import com.tdil.djmag.model.FooterExample;
import java.sql.SQLException;
import java.util.List;

public class FooterDAOImpl implements FooterDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public FooterDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int countFooterByExample(FooterExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("FOOTER.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int deleteFooterByExample(FooterExample example) throws SQLException {
		int rows = sqlMapClient.delete("FOOTER.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int deleteFooterByPrimaryKey(Integer id) throws SQLException {
		Footer _key = new Footer();
		_key.setId(id);
		int rows = sqlMapClient.delete("FOOTER.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Integer insertFooter(Footer record) throws SQLException {
		Object newKey = sqlMapClient.insert("FOOTER.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Integer insertFooterSelective(Footer record) throws SQLException {
		Object newKey = sqlMapClient.insert("FOOTER.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Footer> selectFooterByExampleWithBLOBs(FooterExample example) throws SQLException {
		List<Footer> list = sqlMapClient.queryForList("FOOTER.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Footer> selectFooterByExampleWithoutBLOBs(FooterExample example) throws SQLException {
		List<Footer> list = sqlMapClient.queryForList("FOOTER.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public Footer selectFooterByPrimaryKey(Integer id) throws SQLException {
		Footer _key = new Footer();
		_key.setId(id);
		Footer record = (Footer) sqlMapClient.queryForObject("FOOTER.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int updateFooterByExampleSelective(Footer record, FooterExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FOOTER.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int updateFooterByExampleWithBLOBs(Footer record, FooterExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FOOTER.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int updateFooterByExampleWithoutBLOBs(Footer record, FooterExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("FOOTER.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int updateFooterByPrimaryKeySelective(Footer record) throws SQLException {
		int rows = sqlMapClient.update("FOOTER.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int updateFooterByPrimaryKeyWithBLOBs(Footer record) throws SQLException {
		int rows = sqlMapClient.update("FOOTER.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	public int updateFooterByPrimaryKeyWithoutBLOBs(Footer record) throws SQLException {
		int rows = sqlMapClient.update("FOOTER.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table FOOTER
	 * @mbggenerated  Thu Apr 26 15:35:57 ART 2012
	 */
	protected static class UpdateByExampleParms extends FooterExample {
		private Object record;

		public UpdateByExampleParms(Object record, FooterExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}