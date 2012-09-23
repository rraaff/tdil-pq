package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.ClientDAO;
import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.ClientExample;
import java.sql.SQLException;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public ClientDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int countClientByExample(ClientExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("CLIENT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int deleteClientByExample(ClientExample example) throws SQLException {
		int rows = sqlMapClient.delete("CLIENT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int deleteClientByPrimaryKey(Integer id) throws SQLException {
		Client _key = new Client();
		_key.setId(id);
		int rows = sqlMapClient.delete("CLIENT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Integer insertClient(Client record) throws SQLException {
		Object newKey = sqlMapClient.insert("CLIENT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Integer insertClientSelective(Client record) throws SQLException {
		Object newKey = sqlMapClient.insert("CLIENT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<Client> selectClientByExample(ClientExample example) throws SQLException {
		List<Client> list = sqlMapClient.queryForList("CLIENT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public Client selectClientByPrimaryKey(Integer id) throws SQLException {
		Client _key = new Client();
		_key.setId(id);
		Client record = (Client) sqlMapClient.queryForObject("CLIENT.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateClientByExampleSelective(Client record, ClientExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("CLIENT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateClientByExample(Client record, ClientExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("CLIENT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateClientByPrimaryKeySelective(Client record) throws SQLException {
		int rows = sqlMapClient.update("CLIENT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	public int updateClientByPrimaryKey(Client record) throws SQLException {
		int rows = sqlMapClient.update("CLIENT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table CLIENT
	 * @mbggenerated  Sun Sep 23 12:43:26 ART 2012
	 */
	protected static class UpdateByExampleParms extends ClientExample {
		private Object record;

		public UpdateByExampleParms(Object record, ClientExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}