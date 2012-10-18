package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.ServiceArea;
import com.tdil.tuafesta.model.ServiceAreaExample;
import java.sql.SQLException;
import java.util.List;

public interface ServiceAreaDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int countServiceAreaByExample(ServiceAreaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int deleteServiceAreaByExample(ServiceAreaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int deleteServiceAreaByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	Integer insertServiceArea(ServiceArea record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	Integer insertServiceAreaSelective(ServiceArea record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	List<ServiceArea> selectServiceAreaByExample(ServiceAreaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	ServiceArea selectServiceAreaByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateServiceAreaByExampleSelective(ServiceArea record, ServiceAreaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateServiceAreaByExample(ServiceArea record, ServiceAreaExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateServiceAreaByPrimaryKeySelective(ServiceArea record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table SERVICE_AREA
	 * @mbggenerated  Mon Oct 15 18:05:42 ART 2012
	 */
	int updateServiceAreaByPrimaryKey(ServiceArea record) throws SQLException;
}