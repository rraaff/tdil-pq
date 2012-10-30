package com.tdil.tuafesta.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.HighlightedCategoryDAO;
import com.tdil.tuafesta.model.HighlightedCategory;
import com.tdil.tuafesta.model.HighlightedCategoryExample;
import com.tdil.tuafesta.model.valueobjects.HighlightedCategoryValueObject;

public class HighlightedCategoryDAOImpl implements HighlightedCategoryDAO {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public HighlightedCategoryDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int countHighlightedCategoryByExample(HighlightedCategoryExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("HIGHLIGHTED_CAT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteHighlightedCategoryByExample(HighlightedCategoryExample example) throws SQLException {
		int rows = sqlMapClient.delete("HIGHLIGHTED_CAT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteHighlightedCategoryByPrimaryKey(Integer id) throws SQLException {
		HighlightedCategory _key = new HighlightedCategory();
		_key.setId(id);
		int rows = sqlMapClient.delete("HIGHLIGHTED_CAT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertHighlightedCategory(HighlightedCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("HIGHLIGHTED_CAT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertHighlightedCategorySelective(HighlightedCategory record) throws SQLException {
		Object newKey = sqlMapClient.insert("HIGHLIGHTED_CAT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<HighlightedCategory> selectHighlightedCategoryByExample(HighlightedCategoryExample example)
			throws SQLException {
		List<HighlightedCategory> list = sqlMapClient.queryForList("HIGHLIGHTED_CAT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public HighlightedCategory selectHighlightedCategoryByPrimaryKey(Integer id) throws SQLException {
		HighlightedCategory _key = new HighlightedCategory();
		_key.setId(id);
		HighlightedCategory record = (HighlightedCategory) sqlMapClient.queryForObject(
				"HIGHLIGHTED_CAT.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateHighlightedCategoryByExampleSelective(HighlightedCategory record,
			HighlightedCategoryExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("HIGHLIGHTED_CAT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateHighlightedCategoryByExample(HighlightedCategory record, HighlightedCategoryExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("HIGHLIGHTED_CAT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateHighlightedCategoryByPrimaryKeySelective(HighlightedCategory record) throws SQLException {
		int rows = sqlMapClient.update("HIGHLIGHTED_CAT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateHighlightedCategoryByPrimaryKey(HighlightedCategory record) throws SQLException {
		int rows = sqlMapClient.update("HIGHLIGHTED_CAT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table HIGHLIGHTED_CAT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected static class UpdateByExampleParms extends HighlightedCategoryExample {
		private Object record;

		public UpdateByExampleParms(Object record, HighlightedCategoryExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
    public List<HighlightedCategoryValueObject> searchHighlightedCategories() throws SQLException {
    	List<HighlightedCategoryValueObject> list = sqlMapClient.queryForList("HIGHLIGHTED_CAT.searchHighlightedCategories");
		return list;
    }
}