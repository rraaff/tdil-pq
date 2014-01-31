package com.tdil.ljpeugeot.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.VehicleDAO;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.VehicleExample;
import java.sql.SQLException;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public VehicleDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int countVehicleByExample(VehicleExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("dbo_VEHICLE.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int deleteVehicleByExample(VehicleExample example) throws SQLException {
		int rows = sqlMapClient.delete("dbo_VEHICLE.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int deleteVehicleByPrimaryKey(Integer id) throws SQLException {
		Vehicle _key = new Vehicle();
		_key.setId(id);
		int rows = sqlMapClient.delete("dbo_VEHICLE.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public Integer insertVehicle(Vehicle record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_VEHICLE.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public Integer insertVehicleSelective(Vehicle record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_VEHICLE.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<Vehicle> selectVehicleByExample(VehicleExample example) throws SQLException {
		List<Vehicle> list = sqlMapClient.queryForList("dbo_VEHICLE.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public Vehicle selectVehicleByPrimaryKey(Integer id) throws SQLException {
		Vehicle _key = new Vehicle();
		_key.setId(id);
		Vehicle record = (Vehicle) sqlMapClient.queryForObject("dbo_VEHICLE.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int updateVehicleByExampleSelective(Vehicle record, VehicleExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_VEHICLE.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int updateVehicleByExample(Vehicle record, VehicleExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_VEHICLE.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int updateVehicleByPrimaryKeySelective(Vehicle record) throws SQLException {
		int rows = sqlMapClient.update("dbo_VEHICLE.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	public int updateVehicleByPrimaryKey(Vehicle record) throws SQLException {
		int rows = sqlMapClient.update("dbo_VEHICLE.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.VEHICLE
	 * @mbggenerated  Fri Jan 31 01:22:00 ART 2014
	 */
	protected static class UpdateByExampleParms extends VehicleExample {
		private Object record;

		public UpdateByExampleParms(Object record, VehicleExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}