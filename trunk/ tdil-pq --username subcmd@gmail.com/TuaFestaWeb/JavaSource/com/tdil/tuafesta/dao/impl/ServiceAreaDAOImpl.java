package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ServiceAreaDAO;
import com.tdil.tuafesta.model.ServiceArea;
import com.tdil.tuafesta.model.ServiceAreaExample;
import java.sql.SQLException;
import java.util.List;

public class ServiceAreaDAOImpl implements ServiceAreaDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public ServiceAreaDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int countServiceAreaByExample(ServiceAreaExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SERVICE_AREA.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int deleteServiceAreaByExample(ServiceAreaExample example) throws SQLException {
		int rows = sqlMapClient.delete("SERVICE_AREA.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int deleteServiceAreaByPrimaryKey(Integer id) throws SQLException {
		ServiceArea _key = new ServiceArea();
		_key.setId(id);
		int rows = sqlMapClient.delete("SERVICE_AREA.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Integer insertServiceArea(ServiceArea record) throws SQLException {
		Object newKey = sqlMapClient.insert("SERVICE_AREA.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Integer insertServiceAreaSelective(ServiceArea record) throws SQLException {
		Object newKey = sqlMapClient.insert("SERVICE_AREA.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<ServiceArea> selectServiceAreaByExample(ServiceAreaExample example) throws SQLException {
		List<ServiceArea> list = sqlMapClient.queryForList("SERVICE_AREA.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public ServiceArea selectServiceAreaByPrimaryKey(Integer id) throws SQLException {
		ServiceArea _key = new ServiceArea();
		_key.setId(id);
		ServiceArea record = (ServiceArea) sqlMapClient.queryForObject("SERVICE_AREA.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateServiceAreaByExampleSelective(ServiceArea record, ServiceAreaExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SERVICE_AREA.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateServiceAreaByExample(ServiceArea record, ServiceAreaExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SERVICE_AREA.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateServiceAreaByPrimaryKeySelective(ServiceArea record) throws SQLException {
		int rows = sqlMapClient.update("SERVICE_AREA.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateServiceAreaByPrimaryKey(ServiceArea record) throws SQLException {
		int rows = sqlMapClient.update("SERVICE_AREA.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected static class UpdateByExampleParms extends ServiceAreaExample {
		private Object record;

		public UpdateByExampleParms(Object record, ServiceAreaExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}