package com.tdil.ljpeugeot.dao;

import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.ServiceExample;
import java.sql.SQLException;
import java.util.List;

public interface ServiceDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int countServiceByExample(ServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int deleteServiceByExample(ServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int deleteServiceByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	Integer insertService(Service record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	Integer insertServiceSelective(Service record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	List<Service> selectServiceByExample(ServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	Service selectServiceByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int updateServiceByExampleSelective(Service record, ServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int updateServiceByExample(Service record, ServiceExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int updateServiceByPrimaryKeySelective(Service record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.SERVICE
	 * @mbggenerated  Fri Feb 07 00:40:03 ART 2014
	 */
	int updateServiceByPrimaryKey(Service record) throws SQLException;
}