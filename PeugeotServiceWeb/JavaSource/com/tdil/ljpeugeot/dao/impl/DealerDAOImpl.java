package com.tdil.ljpeugeot.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.DealerDAO;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.DealerExample;
import java.sql.SQLException;
import java.util.List;

public class DealerDAOImpl implements DealerDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public DealerDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int countDealerByExample(DealerExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("DEALER.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int deleteDealerByExample(DealerExample example) throws SQLException {
		int rows = sqlMapClient.delete("DEALER.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int deleteDealerByPrimaryKey(Integer id) throws SQLException {
		Dealer _key = new Dealer();
		_key.setId(id);
		int rows = sqlMapClient.delete("DEALER.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public Integer insertDealer(Dealer record) throws SQLException {
		Object newKey = sqlMapClient.insert("DEALER.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public Integer insertDealerSelective(Dealer record) throws SQLException {
		Object newKey = sqlMapClient.insert("DEALER.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<Dealer> selectDealerByExample(DealerExample example) throws SQLException {
		List<Dealer> list = sqlMapClient.queryForList("DEALER.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public Dealer selectDealerByPrimaryKey(Integer id) throws SQLException {
		Dealer _key = new Dealer();
		_key.setId(id);
		Dealer record = (Dealer) sqlMapClient.queryForObject("DEALER.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int updateDealerByExampleSelective(Dealer record, DealerExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("DEALER.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int updateDealerByExample(Dealer record, DealerExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("DEALER.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int updateDealerByPrimaryKeySelective(Dealer record) throws SQLException {
		int rows = sqlMapClient.update("DEALER.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	public int updateDealerByPrimaryKey(Dealer record) throws SQLException {
		int rows = sqlMapClient.update("DEALER.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table DEALER
	 * @mbggenerated  Sun Feb 02 17:25:48 ART 2014
	 */
	protected static class UpdateByExampleParms extends DealerExample {
		private Object record;

		public UpdateByExampleParms(Object record, DealerExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}