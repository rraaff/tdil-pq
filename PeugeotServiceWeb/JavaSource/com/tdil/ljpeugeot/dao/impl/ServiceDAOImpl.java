package com.tdil.ljpeugeot.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.ServiceDAO;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.ServiceExample;
import java.sql.SQLException;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public ServiceDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int countServiceByExample(ServiceExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("SERVICE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int deleteServiceByExample(ServiceExample example) throws SQLException {
		int rows = sqlMapClient.delete("SERVICE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int deleteServiceByPrimaryKey(Integer id) throws SQLException {
		Service _key = new Service();
		_key.setId(id);
		int rows = sqlMapClient.delete("SERVICE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Integer insertService(Service record) throws SQLException {
		Object newKey = sqlMapClient.insert("SERVICE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Integer insertServiceSelective(Service record) throws SQLException {
		Object newKey = sqlMapClient.insert("SERVICE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<Service> selectServiceByExample(ServiceExample example) throws SQLException {
		List<Service> list = sqlMapClient.queryForList("SERVICE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public Service selectServiceByPrimaryKey(Integer id) throws SQLException {
		Service _key = new Service();
		_key.setId(id);
		Service record = (Service) sqlMapClient.queryForObject("SERVICE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int updateServiceByExampleSelective(Service record, ServiceExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SERVICE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int updateServiceByExample(Service record, ServiceExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("SERVICE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int updateServiceByPrimaryKeySelective(Service record) throws SQLException {
		int rows = sqlMapClient.update("SERVICE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	public int updateServiceByPrimaryKey(Service record) throws SQLException {
		int rows = sqlMapClient.update("SERVICE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table SERVICE
	 * @mbggenerated  Mon Feb 03 23:13:00 ART 2014
	 */
	protected static class UpdateByExampleParms extends ServiceExample {
		private Object record;

		public UpdateByExampleParms(Object record, ServiceExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}