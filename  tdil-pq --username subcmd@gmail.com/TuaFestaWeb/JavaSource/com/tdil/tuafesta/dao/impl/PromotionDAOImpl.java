package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.PromotionDAO;
import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionExample;
import com.tdil.tuafesta.model.valueobjects.PromotionValueObject;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

import java.sql.SQLException;
import java.util.List;

public class PromotionDAOImpl implements PromotionDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public PromotionDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int countPromotionByExample(PromotionExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROMOTION.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deletePromotionByExample(PromotionExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROMOTION.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deletePromotionByPrimaryKey(Integer id) throws SQLException {
		Promotion _key = new Promotion();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROMOTION.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertPromotion(Promotion record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROMOTION.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertPromotionSelective(Promotion record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROMOTION.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Promotion> selectPromotionByExample(PromotionExample example) throws SQLException {
		List<Promotion> list = sqlMapClient.queryForList("PROMOTION.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Promotion selectPromotionByPrimaryKey(Integer id) throws SQLException {
		Promotion _key = new Promotion();
		_key.setId(id);
		Promotion record = (Promotion) sqlMapClient.queryForObject("PROMOTION.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updatePromotionByExampleSelective(Promotion record, PromotionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROMOTION.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updatePromotionByExample(Promotion record, PromotionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROMOTION.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updatePromotionByPrimaryKeySelective(Promotion record) throws SQLException {
		int rows = sqlMapClient.update("PROMOTION.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updatePromotionByPrimaryKey(Promotion record) throws SQLException {
		int rows = sqlMapClient.update("PROMOTION.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROMOTION
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected static class UpdateByExampleParms extends PromotionExample {
		private Object record;

		public UpdateByExampleParms(Object record, PromotionExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
	public List<PromotionValueObject> selectActivePromotions() throws SQLException {
		List<PromotionValueObject> list = sqlMapClient.queryForList("PROMOTION.selectActivePromotions");
		return list;
	}
}