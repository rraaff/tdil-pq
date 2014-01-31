package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.VehicleExample;
import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int countVehicleByExample(VehicleExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int deleteVehicleByExample(VehicleExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int deleteVehicleByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	Integer insertVehicle(Vehicle record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	Integer insertVehicleSelective(Vehicle record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	List<Vehicle> selectVehicleByExample(VehicleExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	Vehicle selectVehicleByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updateVehicleByExampleSelective(Vehicle record, VehicleExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updateVehicleByExample(Vehicle record, VehicleExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updateVehicleByPrimaryKeySelective(Vehicle record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table VEHICLE
	 * @mbggenerated  Fri Jan 31 08:03:00 ART 2014
	 */
	int updateVehicleByPrimaryKey(Vehicle record) throws SQLException;
}