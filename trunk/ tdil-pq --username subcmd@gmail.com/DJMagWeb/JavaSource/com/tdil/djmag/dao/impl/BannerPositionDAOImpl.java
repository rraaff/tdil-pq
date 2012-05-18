package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.BannerPositionDAO;
import com.tdil.djmag.model.BannerPosition;
import com.tdil.djmag.model.BannerPositionExample;
import java.sql.SQLException;
import java.util.List;

public class BannerPositionDAOImpl implements BannerPositionDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public BannerPositionDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int countBannerPositionByExample(BannerPositionExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("BANNER_POSITION.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int deleteBannerPositionByExample(BannerPositionExample example) throws SQLException {
		int rows = sqlMapClient.delete("BANNER_POSITION.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int deleteBannerPositionByPrimaryKey(Integer id) throws SQLException {
		BannerPosition _key = new BannerPosition();
		_key.setId(id);
		int rows = sqlMapClient.delete("BANNER_POSITION.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public Integer insertBannerPosition(BannerPosition record) throws SQLException {
		Object newKey = sqlMapClient.insert("BANNER_POSITION.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public Integer insertBannerPositionSelective(BannerPosition record) throws SQLException {
		Object newKey = sqlMapClient.insert("BANNER_POSITION.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<BannerPosition> selectBannerPositionByExample(BannerPositionExample example) throws SQLException {
		List<BannerPosition> list = sqlMapClient.queryForList("BANNER_POSITION.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public BannerPosition selectBannerPositionByPrimaryKey(Integer id) throws SQLException {
		BannerPosition _key = new BannerPosition();
		_key.setId(id);
		BannerPosition record = (BannerPosition) sqlMapClient
				.queryForObject("BANNER_POSITION.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int updateBannerPositionByExampleSelective(BannerPosition record, BannerPositionExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BANNER_POSITION.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int updateBannerPositionByExample(BannerPosition record, BannerPositionExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BANNER_POSITION.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int updateBannerPositionByPrimaryKeySelective(BannerPosition record) throws SQLException {
		int rows = sqlMapClient.update("BANNER_POSITION.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	public int updateBannerPositionByPrimaryKey(BannerPosition record) throws SQLException {
		int rows = sqlMapClient.update("BANNER_POSITION.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table BANNER_POSITION
	 * @mbggenerated  Fri May 18 00:05:45 ART 2012
	 */
	protected static class UpdateByExampleParms extends BannerPositionExample {
		private Object record;

		public UpdateByExampleParms(Object record, BannerPositionExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}