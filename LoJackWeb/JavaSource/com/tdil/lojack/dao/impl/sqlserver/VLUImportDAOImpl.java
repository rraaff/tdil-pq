package com.tdil.lojack.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.lojack.dao.VLUImportDAO;
import com.tdil.lojack.model.VLUImport;
import com.tdil.lojack.model.VLUImportExample;
import java.sql.SQLException;
import java.util.List;

public class VLUImportDAOImpl implements VLUImportDAO {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public VLUImportDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int countVLUImportByExample(VLUImportExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("dbo_VLU_IMPORT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int deleteVLUImportByExample(VLUImportExample example) throws SQLException {
		int rows = sqlMapClient.delete("dbo_VLU_IMPORT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int deleteVLUImportByPrimaryKey(Integer id) throws SQLException {
		VLUImport _key = new VLUImport();
		_key.setId(id);
		int rows = sqlMapClient.delete("dbo_VLU_IMPORT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public Integer insertVLUImport(VLUImport record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_VLU_IMPORT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public Integer insertVLUImportSelective(VLUImport record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_VLU_IMPORT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<VLUImport> selectVLUImportByExample(VLUImportExample example) throws SQLException {
		List<VLUImport> list = sqlMapClient.queryForList("dbo_VLU_IMPORT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public VLUImport selectVLUImportByPrimaryKey(Integer id) throws SQLException {
		VLUImport _key = new VLUImport();
		_key.setId(id);
		VLUImport record = (VLUImport) sqlMapClient.queryForObject("dbo_VLU_IMPORT.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int updateVLUImportByExampleSelective(VLUImport record, VLUImportExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_VLU_IMPORT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int updateVLUImportByExample(VLUImport record, VLUImportExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_VLU_IMPORT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int updateVLUImportByPrimaryKeySelective(VLUImport record) throws SQLException {
		int rows = sqlMapClient.update("dbo_VLU_IMPORT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	public int updateVLUImportByPrimaryKey(VLUImport record) throws SQLException {
		int rows = sqlMapClient.update("dbo_VLU_IMPORT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.VLU_IMPORT
	 * @mbggenerated  Sun Mar 16 22:33:08 ART 2014
	 */
	protected static class UpdateByExampleParms extends VLUImportExample {
		private Object record;

		public UpdateByExampleParms(Object record, VLUImportExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public int incrementProcessed(int id) throws SQLException {
		int rows = sqlMapClient.update("dbo_VLU_IMPORT.incrementProcessed", id);
		return rows;
	}
}