package com.tdil.tuafesta.dao;

import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.ClientExample;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int countClientByExample(ClientExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteClientByExample(ClientExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int deleteClientByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertClient(Client record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Integer insertClientSelective(Client record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	List<Client> selectClientByExample(ClientExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	Client selectClientByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateClientByExampleSelective(Client record, ClientExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateClientByExample(Client record, ClientExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateClientByPrimaryKeySelective(Client record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 09 12:21:30 ART 2012
	 */
	int updateClientByPrimaryKey(Client record) throws SQLException;
}