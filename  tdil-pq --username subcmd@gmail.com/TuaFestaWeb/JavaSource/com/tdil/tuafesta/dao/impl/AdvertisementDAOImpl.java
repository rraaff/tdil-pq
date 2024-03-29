package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.AdvertisementDAO;
import com.tdil.tuafesta.model.Advertisement;
import com.tdil.tuafesta.model.AdvertisementExample;
import com.tdil.tuafesta.model.valueobjects.AdvertisementValueObject;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdvertisementDAOImpl implements AdvertisementDAO {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public AdvertisementDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int countAdvertisementByExample(AdvertisementExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("ADVERTISEMENT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteAdvertisementByExample(AdvertisementExample example) throws SQLException {
		int rows = sqlMapClient.delete("ADVERTISEMENT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int deleteAdvertisementByPrimaryKey(Integer id) throws SQLException {
		Advertisement _key = new Advertisement();
		_key.setId(id);
		int rows = sqlMapClient.delete("ADVERTISEMENT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertAdvertisement(Advertisement record) throws SQLException {
		Object newKey = sqlMapClient.insert("ADVERTISEMENT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Integer insertAdvertisementSelective(Advertisement record) throws SQLException {
		Object newKey = sqlMapClient.insert("ADVERTISEMENT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Advertisement> selectAdvertisementByExample(AdvertisementExample example) throws SQLException {
		List<Advertisement> list = sqlMapClient.queryForList("ADVERTISEMENT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public Advertisement selectAdvertisementByPrimaryKey(Integer id) throws SQLException {
		Advertisement _key = new Advertisement();
		_key.setId(id);
		Advertisement record = (Advertisement) sqlMapClient.queryForObject("ADVERTISEMENT.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateAdvertisementByExampleSelective(Advertisement record, AdvertisementExample example)
			throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("ADVERTISEMENT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateAdvertisementByExample(Advertisement record, AdvertisementExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("ADVERTISEMENT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateAdvertisementByPrimaryKeySelective(Advertisement record) throws SQLException {
		int rows = sqlMapClient.update("ADVERTISEMENT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	public int updateAdvertisementByPrimaryKey(Advertisement record) throws SQLException {
		int rows = sqlMapClient.update("ADVERTISEMENT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ADVERTISEMENT
	 * @mbggenerated  Mon Oct 29 16:37:30 ART 2012
	 */
	protected static class UpdateByExampleParms extends AdvertisementExample {
		private Object record;

		public UpdateByExampleParms(Object record, AdvertisementExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
    public List<AdvertisementValueObject> searchByProfAndDates(Map<String, Object> params) throws SQLException {
    	List<AdvertisementValueObject> list = sqlMapClient.queryForList("ADVERTISEMENT.searchByProfAndDates", params);
		return list;
    }
    
    @Override
    public List<AdvertisementValueObject> selectActiveNormalAds() throws SQLException {
    	List<AdvertisementValueObject> list = sqlMapClient.queryForList("ADVERTISEMENT.selectActiveNormalAds");
		return list;
    }
    
   @Override
    public List<AdvertisementValueObject> selectActiveExtraAds() throws SQLException {
	   List<AdvertisementValueObject> list = sqlMapClient.queryForList("ADVERTISEMENT.selectActiveExtraAds");
	   return list;
    } 
   
   @Override
	public List<AdvertisementValueObject> selectActiveExtraAdsByCategory() throws SQLException {
	   List<AdvertisementValueObject> list = sqlMapClient.queryForList("ADVERTISEMENT.selectActiveExtraAdsByCategory");
	   return list;
	}
}