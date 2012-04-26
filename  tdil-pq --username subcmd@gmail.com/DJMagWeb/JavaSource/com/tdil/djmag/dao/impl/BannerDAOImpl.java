package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.BannerDAO;
import com.tdil.djmag.model.Banner;
import com.tdil.djmag.model.BannerExample;
import java.sql.SQLException;
import java.util.List;

public class BannerDAOImpl implements BannerDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public BannerDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int countBannerByExample(BannerExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("BANNER.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int deleteBannerByExample(BannerExample example) throws SQLException {
		int rows = sqlMapClient.delete("BANNER.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int deleteBannerByPrimaryKey(Integer id) throws SQLException {
		Banner _key = new Banner();
		_key.setId(id);
		int rows = sqlMapClient.delete("BANNER.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public Integer insertBanner(Banner record) throws SQLException {
		Object newKey = sqlMapClient.insert("BANNER.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public Integer insertBannerSelective(Banner record) throws SQLException {
		Object newKey = sqlMapClient.insert("BANNER.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Banner> selectBannerByExampleWithBLOBs(BannerExample example) throws SQLException {
		List<Banner> list = sqlMapClient.queryForList("BANNER.selectByExampleWithBLOBs", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Banner> selectBannerByExampleWithoutBLOBs(BannerExample example) throws SQLException {
		List<Banner> list = sqlMapClient.queryForList("BANNER.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public Banner selectBannerByPrimaryKey(Integer id) throws SQLException {
		Banner _key = new Banner();
		_key.setId(id);
		Banner record = (Banner) sqlMapClient.queryForObject("BANNER.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int updateBannerByExampleSelective(Banner record, BannerExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BANNER.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int updateBannerByExampleWithBLOBs(Banner record, BannerExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BANNER.updateByExampleWithBLOBs", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int updateBannerByExampleWithoutBLOBs(Banner record, BannerExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("BANNER.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int updateBannerByPrimaryKeySelective(Banner record) throws SQLException {
		int rows = sqlMapClient.update("BANNER.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int updateBannerByPrimaryKeyWithBLOBs(Banner record) throws SQLException {
		int rows = sqlMapClient.update("BANNER.updateByPrimaryKeyWithBLOBs", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	public int updateBannerByPrimaryKeyWithoutBLOBs(Banner record) throws SQLException {
		int rows = sqlMapClient.update("BANNER.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table BANNER
	 * @mbggenerated  Thu Apr 26 13:42:14 ART 2012
	 */
	protected static class UpdateByExampleParms extends BannerExample {
		private Object record;

		public UpdateByExampleParms(Object record, BannerExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}