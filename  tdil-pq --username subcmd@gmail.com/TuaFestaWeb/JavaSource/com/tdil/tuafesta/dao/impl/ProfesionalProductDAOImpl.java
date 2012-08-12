package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ProfesionalProductDAO;
import com.tdil.tuafesta.model.ProfesionalProduct;
import com.tdil.tuafesta.model.ProfesionalProductExample;
import java.sql.SQLException;
import java.util.List;

public class ProfesionalProductDAOImpl implements ProfesionalProductDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public ProfesionalProductDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int countProfesionalProductByExample(ProfesionalProductExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROF_PRODUCT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int deleteProfesionalProductByExample(ProfesionalProductExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROF_PRODUCT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int deleteProfesionalProductByPrimaryKey(Integer id) throws SQLException {
		ProfesionalProduct _key = new ProfesionalProduct();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROF_PRODUCT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public Integer insertProfesionalProduct(ProfesionalProduct record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_PRODUCT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public Integer insertProfesionalProductSelective(ProfesionalProduct record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROF_PRODUCT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ProfesionalProduct> selectProfesionalProductByExample(ProfesionalProductExample example)
			throws SQLException {
		List<ProfesionalProduct> list = sqlMapClient.queryForList("PROF_PRODUCT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public ProfesionalProduct selectProfesionalProductByPrimaryKey(Integer id) throws SQLException {
		ProfesionalProduct _key = new ProfesionalProduct();
		_key.setId(id);
		ProfesionalProduct record = (ProfesionalProduct) sqlMapClient.queryForObject("PROF_PRODUCT.selectByPrimaryKey",
				_key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int updateProfesionalProductByExampleSelective(ProfesionalProduct record, ProfesionalProductExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_PRODUCT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int updateProfesionalProductByExample(ProfesionalProduct record, ProfesionalProductExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROF_PRODUCT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int updateProfesionalProductByPrimaryKeySelective(ProfesionalProduct record) throws SQLException {
		int rows = sqlMapClient.update("PROF_PRODUCT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	public int updateProfesionalProductByPrimaryKey(ProfesionalProduct record) throws SQLException {
		int rows = sqlMapClient.update("PROF_PRODUCT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROF_PRODUCT
	 * @mbggenerated  Sun Aug 12 12:03:37 ART 2012
	 */
	protected static class UpdateByExampleParms extends ProfesionalProductExample {
		private Object record;

		public UpdateByExampleParms(Object record, ProfesionalProductExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}