package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.PromotionSellDAO;
import com.tdil.tuafesta.model.PromotionSell;
import com.tdil.tuafesta.model.PromotionSellExample;
import java.sql.SQLException;
import java.util.List;

public class PromotionSellDAOImpl implements PromotionSellDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public PromotionSellDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int countPromotionSellByExample(PromotionSellExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("PROMOTION_SELL.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int deletePromotionSellByExample(PromotionSellExample example) throws SQLException {
		int rows = sqlMapClient.delete("PROMOTION_SELL.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int deletePromotionSellByPrimaryKey(Integer id) throws SQLException {
		PromotionSell _key = new PromotionSell();
		_key.setId(id);
		int rows = sqlMapClient.delete("PROMOTION_SELL.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public Integer insertPromotionSell(PromotionSell record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROMOTION_SELL.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public Integer insertPromotionSellSelective(PromotionSell record) throws SQLException {
		Object newKey = sqlMapClient.insert("PROMOTION_SELL.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<PromotionSell> selectPromotionSellByExample(PromotionSellExample example) throws SQLException {
		List<PromotionSell> list = sqlMapClient.queryForList("PROMOTION_SELL.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public PromotionSell selectPromotionSellByPrimaryKey(Integer id) throws SQLException {
		PromotionSell _key = new PromotionSell();
		_key.setId(id);
		PromotionSell record = (PromotionSell) sqlMapClient.queryForObject("PROMOTION_SELL.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updatePromotionSellByExampleSelective(PromotionSell record, PromotionSellExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROMOTION_SELL.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updatePromotionSellByExample(PromotionSell record, PromotionSellExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("PROMOTION_SELL.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updatePromotionSellByPrimaryKeySelective(PromotionSell record) throws SQLException {
		int rows = sqlMapClient.update("PROMOTION_SELL.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	public int updatePromotionSellByPrimaryKey(PromotionSell record) throws SQLException {
		int rows = sqlMapClient.update("PROMOTION_SELL.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table PROMOTION_SELL
	 * @mbggenerated  Thu Sep 20 22:30:23 ART 2012
	 */
	protected static class UpdateByExampleParms extends PromotionSellExample {
		private Object record;

		public UpdateByExampleParms(Object record, PromotionSellExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}